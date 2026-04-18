describe("template spec", () => {
  beforeEach(() => {
    cy.visit("http://localhost:4000/");
  });

  it("Login com dados validos deve permitir entrar no sistema", () => {
    cy.login("julio.lima", "123456");

    cy.contains("Realizar Transferência").should("be.visible");
  });

  it("Login com dados inválidos deve apresentar mensagem de erro", () => {
    cy.login("julio.lima", "123456A");
    cy.validateToastMessage("Erro no login. Tente novamente.");
  });
});
