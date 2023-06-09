import Users from "../models/UserModel.js";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

export const getUsers = async (req, res) => {
  try {
    const accessToken = req.headers.authorization.split(" ")[1];
    if (!accessToken) {
      return res.status(401).json({ error: "Access token is missing" });
    }

    jwt.verify(
      accessToken,
      process.env.ACCESS_TOKEN_SECRET,
      async (err, decoded) => {
        if (err) {
          if (err.name === "TokenExpiredError") {
            return res.status(401).json({ error: "Access token expired" });
          }
          return res.status(401).json({ error: "Invalid access token" });
        }

        const { userId } = decoded;

        if (userId) {
          const user = await Users.findOne({
            where: { id: userId },
            attributes: ["id", "name", "email", "gender", "weight", "height"],
          });

          if (!user) {
            return res.status(404).json({ error: "User not found" });
          }

          res.json(user);
        } else {
          return res.status(401).json({ error: "Invalid access token" });
        }
      }
    );
  } catch (error) {
    console.log(error);
    res
      .status(500)
      .json({ error: "An error occurred while fetching the user" });
  }
};

export const Register = async (req, res) => {
  const { name, email, password, confPassword } = req.body;
  if (password !== confPassword) {
    return res
      .status(400)
      .json({ status: "error", message: "Password and Confirm do not match" });
  }

  try {
    const userCount = await Users.count({ where: { email: email } });
    if (userCount > 0) {
      return res
        .status(400)
        .json({ status: "error", message: "Email already exists" });
    }

    const salt = await bcrypt.genSalt();
    const hashPassword = await bcrypt.hash(password, salt);

    const newUser = await Users.create({
      name: name,
      email: email,
      password: hashPassword,
    });

    res.json({ status: "success", data: [newUser] });
  } catch (error) {
    console.log(error);
    res.status(500).json({
      status: "error",
      message: "An error occurred while registering the user",
    });
  }
};

export const Login = async (req, res) => {
  try {
    const { email, password } = req.body;

    const user = await Users.findOne({
      where: {
        email: email,
      },
    });

    if (!user) {
      return res
        .status(404)
        .json({ status: "error", message: "User not found" });
    }

    const match = await bcrypt.compare(password, user.password);

    if (!match) {
      return res
        .status(400)
        .json({ status: "error", message: "Wrong password" });
    }

    const userId = user.id;
    const name = user.name;

    const accessToken = jwt.sign(
      { userId, name, email: user.email },
      process.env.ACCESS_TOKEN_SECRET,
      {
        expiresIn: "1d",
      }
    );

    const responseData = {
      id: user.id,
      name: user.name,
      email: user.email,
      token: accessToken,
    };

    res.json({ status: "success", data: responseData });
  } catch (error) {
    console.log(error);
    res
      .status(500)
      .json({ status: "error", message: "An error occurred while logging in" });
  }
};

export const Logout = async (req, res) => {
  const refreshToken = req.cookies.refreshToken;
  if (!refreshToken) {
    return res.sendStatus(204);
  }

  const user = await Users.findOne({
    where: {
      refresh_token: refreshToken,
    },
  });

  if (!user) {
    return res.sendStatus(204);
  }

  const userId = user.id;

  await Users.update(
    { refresh_token: null },
    {
      where: {
        id: userId,
      },
    }
  );

  res.clearCookie("refreshToken");
  return res.json({ status: "success" });
};

export const updateProfile = async (req, res) => {
  try {
    const { email } = req;
    const { gender, weight, height } = req.body;

    const user = await Users.findOne({
      where: {
        email: email,
      },
    });

    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }

    user.gender = gender || user.gender;
    user.weight = weight || user.weight;
    user.height = height || user.height;

    await user.save();

    res.json({
      status: "success",
      data: [{ msg: "Profile updated successfully" }],
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({
      status: "error",
      message: "An error occurred while updating the profile",
    });
  }
};

export const changePassword = async (req, res) => {
  try {
    const { currentPassword, newPassword, reenterPassword } = req.body;
    const user = await Users.findOne({ where: { email: req.email } });

    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }

    const isMatch = await bcrypt.compare(currentPassword, user.password);
    if (!isMatch) {
      return res.status(400).json({ error: "Invalid current password" });
    }

    if (newPassword !== reenterPassword) {
      return res
        .status(400)
        .json({ error: "New password and re-entered password do not match" });
    }

    const hashedPassword = await bcrypt.hash(newPassword, 10);
    user.password = hashedPassword;
    await user.save();

    res.json({
      status: "success",
      data: [{ msg: "Password changed successfully" }],
    });
  } catch (error) {
    console.error("An error occurred while changing password:", error);
    res.status(500).json({
      status: "error",
      message: "An error occurred while changing password",
    });
  }
};
