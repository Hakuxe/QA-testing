describe("Newsletter Subscribe Form", () => {
  beforeEach(() => {
    cy.visit(Cypress.config().baseUrl);
  });

  it("the user must be able to subscribe with an valid email", () => {
    cy.getByData("email-input").type("test@email.com");
    cy.getByData("submit-button").click();

    cy.getByData("success-message").should("exist").contains("test@email.com");
  });

  //   invalid tests
  it("does NOT allow an invalid email address ", () => {
    cy.getByData("email-input").type("abccom");
    cy.getByData("submit-button").click();

    cy.getByData("success-message").should("not.exist");
  });

  it("does NOT allow already subscribed email addresses", () => {
    cy.getByData("email-input").type("john@example.com");
    cy.getByData("submit-button").click();

    cy.getByData("server-error-message")
      .should("exist")
      .and(
        "contain.text",
        "already exists. Please use a different email address."
      );
  });
});
