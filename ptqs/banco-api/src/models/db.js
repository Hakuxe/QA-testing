const mysql = require("mysql2");
const pg = require("pg");
const { Client, Pool } = require("pg");

require("dotenv").config();

const pool = mysql.createPool({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0,
});

const pgPool = new Pool({
  user: process.env.PG_DB_USER,
  password: process.env.PG_DB_PASSWORD,
  host: process.env.PG_DB_HOST,
  port: Number(process.env.PG_DB_PORT),
  database: process.env.PG_DB_NAME,
});

module.exports =  pgPool;

