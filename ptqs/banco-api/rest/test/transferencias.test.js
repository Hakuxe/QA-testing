require("dotenv").config();

const request = require("supertest");
const { expect } = require("chai");
const { getToken } = require("./helpers/auth");

const postTransferencias = require("./fixtures/postTransferencias.json");

describe("Transferências", () => {
  let TOKEN_LOGIN;

  beforeEach(async () => {
    TOKEN_LOGIN = await getToken(process.env.USERNAME_TEST, process.env.PASSWORD_TEST);
  });

  describe("POST /transferencias", () => {
    it("Deve retornar sucesso com status 201 quando o valor da transferência for igual ou acima de R$10,00", async () => {
      const bodyTranferencias = { ...postTransferencias };

      const response = await request(process.env.BASE_URL)
        .post("/transferencias")
        .set("Content-Type", "application/json")
        .set("Authorization", `Bearer ${TOKEN_LOGIN}`)
        .send(bodyTranferencias);

      expect(response.status).to.equal(201);
    });

    it("Deve retornar sucesso com status 422 quando o valor da transferência for abaixo de R$10,00", async () => {
      const bodyTranferencias = { ...postTransferencias, valor: 7 };

      const response = await request(process.env.BASE_URL)
        .post("/transferencias")
        .set("Content-Type", "application/json")
        .set("Authorization", `Bearer ${TOKEN_LOGIN}`)
        .send(bodyTranferencias);

      expect(response.status).to.equal(422);
    });
  });

  describe("GET /transferencias", () => {
    it("Deve validar dados da transferência retornada", async () => {
      const response = await request(process.env.BASE_URL)
        .get("/transferencias/1")
        .set("Authorization", `Bearer ${TOKEN_LOGIN}`);

      expect(response.status).to.equal(200);
      expect(response.body).to.haveOwnProperty("id");
      expect(response.body).to.haveOwnProperty("conta_origem_id");
      expect(response.body).to.haveOwnProperty("conta_destino_id");
      expect(response.body).to.haveOwnProperty("valor");
      expect(response.body).to.haveOwnProperty("data_hora");
      expect(response.body).to.haveOwnProperty("autenticada");

      const { id, conta_origem_id, conta_destino_id, valor, data_hora, autenticada } =
        response.body;

      expect(id).to.be.equal(1);
      expect(conta_origem_id).to.be.equal(1);
      expect(conta_destino_id).to.be.equal(2);
      expect(valor).to.be.equal(10.0);
      expect(data_hora).to.be.equal("2026-03-30T19:45:05.211Z");
      expect(autenticada).to.be.equal(false);
    });

    it("Deve ser retornado 10 elementos de acordo com o limite requisitado", async () => {

      const response = await request(process.env.BASE_URL)
        .get("/transferencias?page=1&limit=10")
        .set("Authorization", `Bearer ${TOKEN_LOGIN}`);

      expect(response.status).to.equal(200);
      expect(response.body.limit).to.equal(10);
      expect(response.body.transferencias).to.be.an("array").that.has.lengthOf(10);



    });
  });
});
