Cypress.Commands.add("login", (user, password) => {
  cy.get("#username").click().type(user);
  cy.get("#senha").click().type(password);
  cy.contains("button", "Entrar").click();
});
