describe("template spec", () => {
  beforeEach(() => {
    cy.visit("http://localhost:4000/");

     cy.login("julio.lima", "123456A");
  });

  it.only("Deve transferir quando informo dados e valor validos", () => {
    cy.realizarTransferencia("João da Silva", "Maria Oliveira", "11");
  
    cy.validateToastMessage("Transferência realizada!");
  });

  it("Deve apresentar erro quando tentar transferir mais que 5 mil sem o token", () => {
    cy.realizarTransferencia("João da Silva", "Maria Oliveira", "5001");
     cy.validateToastMessage("Transferência realizada!")
  });
});

