const request = require("supertest");
const postLogin = require("../fixtures/postLogin.json");

async function getToken(user, password) {

  const bodyLogin = {...postLogin, username: user, senha: password};

  const responseLogin = await request(process.env.BASE_URL)
    .post("/login")
    .set("Content-Type", "application/json")
    .send(bodyLogin);

  return responseLogin.body.token;
}

module.exports = {
  getToken,
};