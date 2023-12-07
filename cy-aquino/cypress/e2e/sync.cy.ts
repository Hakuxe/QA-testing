describe("testing waits with cypress", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should wait for an element on page", () => {
		cy.get("#buttonDelay").click();

		cy.get("#novoCampo").should("be.visible");
	});

	it.only("should retry to find an element", () => {
		cy.get("#buttonDelay").click();

		cy.get("#novoCampo", {timeout: 6000}).should("exist");
	});

	it("should use find to search for an element", () => {
		cy.get("#buttonList").click();

		cy.get("#lista > li").find(">span").should("contain.text", "Item 1");
		cy.get("#lista > li > span").should("contain.text", "Item 2");
	});



});
