describe("User Journey", () => {
  it("a user can find a course on the home page and complete the courses lessons", () => {
    cy.visit(Cypress.config().baseUrl);
    cy.getByData("course-0")
      .find("a")
      .last()
      .contains("Get started", { matchCase: false })
      .click();

    cy.location("pathname").should(
      "contain",
      "/testing-your-first-application"
    );

    cy.getByData("next-lesson-button").click();
    cy.location("pathname").should(
      "contain",
      "/testing-your-first-application/app-install-and-overview"
    );

    //first lesson
    cy.getByData("challenge-answer-0").click();
    cy.getByData("next-lesson-button").should("exist").click();
    cy.location("pathname").should("contains", "/testing-your-first-application/installing-cypress-and-writing-our-first-test");

    // second lesson
    cy.getByData("challenge-answer-0").click();
    cy.getByData("next-lesson-button").should("exist").click();
    cy.location("pathname").should("contains", "/testing-your-first-application/setting-up-data-before-each-test");

    // third lesson
    cy.getByData("challenge-answer-0").click();
    cy.getByData("next-lesson-button").should("exist").and("contain.text", "Complete Course").click();
    cy.location("pathname").should("eq", "/");

  });
});
