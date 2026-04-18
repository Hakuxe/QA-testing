const request = require("supertest");
const { expect } = require("chai");

describe("Login", () => {
  describe("POST Login", () => {
    it("should retornar 200 com um token do tipo string quando usando credenciais válidas", async () => {
      const baseUrl = "http://localhost:3000";
      const response = await request(baseUrl)
        .post("/login")
        .set("Content-Type", "application/json")
        .send({
          username: "julio.lima",
          senha: "123456",
        });

      expect(response.status).to.equal(200);
      expect(response.body.token).to.be.a("string");
    });
  });
});
