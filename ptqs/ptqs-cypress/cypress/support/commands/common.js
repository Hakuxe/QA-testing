Cypress.Commands.add("validateToastMessage", (expectedMessage) => {
  cy.get(".toast").should("be.visible").should("have.text", expectedMessage);
});

Cypress.Commands.add("selecionarOpcaoNaCombobox", (labelDoCampo, opcao) => {
  cy.get(`label[for="${labelDoCampo}"]`).parent().as(`campo-${labelDoCampo}`);
  cy.get(`@campo-${labelDoCampo}`).click();
  cy.get(`@campo-${labelDoCampo}`).contains(opcao).click();
});
