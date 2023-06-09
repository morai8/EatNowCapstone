import Users from "../models/UserModel.js";
import jwt from "jsonwebtoken";

export const refreshToken = async (req, res) => {
  try {
    const { refreshToken } = req.body;
    if (!refreshToken) {
      return res.status(400).json({ error: "Refresh token is missing" });
    }

    jwt.verify(
      refreshToken,
      process.env.REFRESH_TOKEN_SECRET,
      (err, decoded) => {
        if (err) {
          if (err.name === "TokenExpiredError") {
            return res.status(401).json({ error: "Refresh token expired" });
          }
          return res.status(403).json({ error: "Invalid refresh token" });
        }

        const { userId, email } = decoded;

        Users.findOne({
          where: { id: userId, refresh_token: refreshToken },
        })
          .then((user) => {
            if (!user) {
              return res.status(403).json({ error: "Invalid refresh token" });
            }

            const accessToken = jwt.sign(
              { userId, email },
              process.env.ACCESS_TOKEN_SECRET,
              { expiresIn: "1d" }
            );

            res.json({ accessToken });
          })
          .catch((error) => {
            console.log(error);
            res.sendStatus(500);
          });
      }
    );
  } catch (error) {
    console.log(error);
    res.sendStatus(500);
  }
};
