describe("Given the homepage", () => {
  beforeEach(() => {
    cy.visit(`${Cypress.config().baseUrl}`);
  });

  it("should contain an h1 with next.js text ", () => {
    cy.get("[data-test='hero-heading']").contains(
      "Testing Next.js Applications with Cypress"
    );

    cy.get("[data-test='hero-heading']").contains("next.js", {
      matchCase: false,
    });
  });

  it("test check list has the right content", () => {
    cy.get("dt").eq(0).contains("4 courses", { matchCase: false });
    cy.get("dt").eq(1).contains("25+ Lessons", { matchCase: false });
    cy.get("dt").eq(2).contains("Free and Open Source", { matchCase: false });
  });

  // .only -> run only the selected test
});
