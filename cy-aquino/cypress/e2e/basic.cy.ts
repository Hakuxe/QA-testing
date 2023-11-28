describe("Basic comands", () => {

	before(() => {
		cy.log("Antes de todos os tests")
	})
	
	beforeEach(() =>{
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	})

	it("go to page and check title ", () => {
		cy.title().should("equal", "Campo de Treinamento");
		cy.title().should("contain", "Campo");
	});

	it("click on button and take value", () => {
		cy.get("input[value='Clique Me!']");
		cy.get("#buttonSimple").click().should("contain.value", "Obrigado!");
	});
});
