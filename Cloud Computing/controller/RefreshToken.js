import Users from "../models/UserModel.js";
import jwt from "jsonwebtoken";

export const refreshToken = async (req, res) => {
  try {
    const refreshToken = req.cookies.refreshToken;
    if (!refreshToken) return res.sendStatus(401);
    const user = await Users.findOne({
      where: {
        refresh_token: refreshToken,
      },
    });
    if (!user) return res.sendStatus(403);
    jwt.verify(
      refreshToken,
      process.env.REFRESH_TOKEN_SECRET,
      (err, decoded) => {
        if (err) return res.sendStatus(403);
        const { id, name, email } = user;
        const accessToken = jwt.sign(
          { userId: id, email, name },
          process.env.ACCESS_TOKEN_SECRET,
          {
            expiresIn: "1d",
          }
        );
        res.json({ accessToken });
      }
    );
  } catch (error) {
    console.log(error);
    res.sendStatus(500);
  }
};