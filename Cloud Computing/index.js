import express from "express";
import db from "./config/Database.js";
import Users from "./models/UserModel.js";
import router from "./routes/index.js";
import dotenv from "dotenv";
import cookieParser from "cookie-parser";
import cors from "cors";

dotenv.config();
const app = express();

try {
  await db.authenticate();
  console.log("DB Connected");
  await db.sync();
} catch {
  console.error(error);
}

app.use(cors());
// app.use(cors({ credentials: true, origin: "http://localhost:3000" }));
app.use(cookieParser());
app.use(express.json());
app.use(router);

app.get("/", (req, res) => {
  res.send("Welcome to API");
});

app.listen(5000, () =>
  console.log("Server running at port http://localhost:5000")
);
