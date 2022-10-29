describe("Homepage", () => {
  beforeEach(() => {
    cy.visit(Cypress.config().baseUrl);
  });
  context("Courses section", () => {
    it("Course: Testing Your First Next.js Application", () => {
      cy.getByData("course-0")
        .find("a")
        .last()
        .contains("Get started", { matchCase: false })
        .click();

      cy.location("pathname").should("eq", "/testing-your-first-application");
    });

    it("Course: APP INSTALL AND OVERVIEW", () => {
      cy.getByData("course-0").find("a").eq(0).click();

      cy.location("pathname").should(
        "eq",
        "/testing-your-first-application/app-install-and-overview"
      );
    });

    it("Course: Installing Cypress and writing our first test", () => {
      cy.getByData("course-0").find("a").eq(1).click();

      cy.location("pathname").should(
        "eq",
        "/testing-your-first-application/installing-cypress-and-writing-our-first-test"
      );
    });

    it("Course: Setting up Data Before Each Test", () => {
      cy.getByData("course-0").find("a").eq(2).click();

      cy.location("pathname").should(
        "eq",
        "/testing-your-first-application/setting-up-data-before-each-test"
      );
    });
  });
});
