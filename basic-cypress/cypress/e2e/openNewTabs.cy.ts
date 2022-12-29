describe("handling new tabs", () => {
	const url = "https://rahulshettyacademy.com/AutomationPractice/";

	it("check if browser open a new tab", () => {
		//cypress can't move between multiple tabs so we have to remove the attribute target with jquery

		cy.visit(url);

		cy.get("#opentab")
			.invoke("removeAttr", "target")
			.should("not.have.attr", "target");

		cy.get("#opentab").click();

		cy.url().should("equal", "https://www.rahulshettyacademy.com/");
	});

	it("uses the browser commands to manipulate the page", () => {
		cy.go("back"); // equivalent to clicking back button
		cy.go("forward"); // equivalent to clicking forward button

		cy.url().should("include", "rahulshettyacademy");
	});
});
