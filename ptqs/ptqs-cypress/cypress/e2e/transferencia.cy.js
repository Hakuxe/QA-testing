describe("template spec", () => {
  beforeEach(() => {
    cy.visit("http://localhost:4000/");

     cy.login("julio.lima", "123456A");
  });

  it.only("Deve transferir quando informo dados e valor validos", () => {
    cy.get(`label[for="conta-origem"]`).parent().as(`campo-conta-origem`);
    cy.get(`@campo-conta-origem`).click();
    cy.get(`@campo-conta-origem`).contains("João da Silva").click();

    cy.get(`label[for="conta-destino"]`).parent().as(`campo-conta-destino`);
    cy.get(`@campo-conta-destino`).click();
    cy.get(`@campo-conta-destino`).contains("Maria Oliveira").click();

    cy.get("#valor").click().type("11");

    cy.contains("button", "Transferir").click();
    
    cy.get(".toast").should("be.visible").should("have.text", "Transferência realizada!");

    
    cy.validateToastMessage("Transferência realizada!");
  });

  it("Deve apresentar erro quando tentar transferir mais que 5 mil sem o token", () => {});
});
