describe("Access the home ", () => {
	before(() =>
		cy.log("tudo akí é executado uma única vez antes de todos os testes")
	);

	after(() => {
		cy.log("tudo akí é executado uma única vez depois de todos os testes");
	});

	beforeEach(() => cy.log("tudo akí é executado antes de cada teste"));

	afterEach(() => cy.log("tudo akí é executado antes de cada teste"));

	it("should contain a title ", () => {
		cy.visit("https://buger-eats.vercel.app");
		cy.get("h1").should(
			"have.text",
			"Seja um parceiro entregador pela Buger Eats"
		);

		cy.get("a[href='/deliver']").click();
	});
});
