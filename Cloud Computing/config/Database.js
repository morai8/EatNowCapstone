import { Sequelize } from "sequelize";

const db = new Sequelize({
  database: "auth_db",
  username: "root",
  password: "password",
  host: "/cloudsql/eatnow-project-c23-ps470:asia-southeast2:c23-ps470-eatnow",
  dialect: "mysql",
  dialectOptions: {
    socketPath:
      "/cloudsql/eatnow-project-c23-ps470:asia-southeast2:c23-ps470-eatnow",
  },
});

export default db;
