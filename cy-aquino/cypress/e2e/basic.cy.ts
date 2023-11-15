describe("Basic comands", () => {
	it("go to page and check title ", () => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");

		cy.title().should("equal", "Campo de Treinamento");
		cy.title().should("contain", "Campo");
	});

	it.only("click on button and take value", () => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");

		cy.get("input[value='Clique Me!']");
		cy.get("#buttonSimple").click().should("contain.value", "Obrigado!");
	});
});
