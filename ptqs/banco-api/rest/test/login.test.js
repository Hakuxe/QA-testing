require("dotenv").config();

const request = require("supertest");
const { expect } = require("chai");

const postLogin = require("./fixtures/postLogin.json");

describe("Login", () => {
  describe("POST Login", () => {
    it("should retornar 200 com um token do tipo string quando usando credenciais válidas", async () => {
      const username = process.env.USERNAME_TEST;
      const password = process.env.PASSWORD_TEST;

      const bodyLogin = {...postLogin, username, senha: password};
     
      const response = await request(process.env.BASE_URL)
        .post("/login")
        .set("Content-Type", "application/json")
        .send(bodyLogin);

      expect(response.status).to.equal(200);
      expect(response.body.token).to.be.a("string");
    });
  });
});
