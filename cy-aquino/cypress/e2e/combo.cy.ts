describe("Select Input", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should select an option on input select", () => {
		// value
		cy.get("[data-test='dataEscolaridade']")
			.select("1graucomp")
			.should("have.value", "1graucomp");

		// text
		cy.get("[data-test='dataEscolaridade']")
			.select("Doutorado")
			.should("contain.text", "Doutorado");

		// index
		cy.get("[data-test='dataEscolaridade']")
			.select(4)
			.should("have.value", "superior");
	});

	it.only("should select multiple option on input select", () => {
		const selectMultiple = "[data-testid='dataEsportes']";

		// value
		cy.get(selectMultiple)
			.select(["natacao", "Corrida"])
			.invoke("val")
			.should("deep.equal", ["natacao", "Corrida"]);

	});
});
