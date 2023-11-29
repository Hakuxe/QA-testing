describe("Basic comands", () => {
	before(() => {
		cy.log("Antes de todos os tests");
	});

	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("go to page and check title ", () => {
		cy.title().should("equal", "Campo de Treinamento");
		cy.title().should("contain", "Campo");
	});

	it("click on button and take value", () => {
		cy.get("input[value='Clique Me!']");
		cy.get("#buttonSimple").click().should("contain.value", "Obrigado!");
	});

	it("should fill text input", () => {
		const name = "Sara";

		cy.get("#formNome").type(name).should("contain.value", name);

		cy.get("#elementosForm\\:sugestoes").type(name);
		cy.get("#elementosForm\\:sugestoes").should("have.value", name);

		cy.get("[data-cy='dataSobrenome']")
			.type("12345{backspace}{backspace}")
			.should("have.value", "123");

		cy.get("#elementosForm\\:sugestoes")
			.clear()
			.type("novo nome", { delay: 100 })
			.should("contain.value", "novo nome");
	});

	it("should select radio button male", () => {
		cy.get("[name='formSexo']").first().check().should("be.checked");
		cy.get("[name='formSexo']")
			.check("F")
			.should("be.checked")
			.and("have.value", "F"); // check by value
	});

	it.only("should select checkbox", () => {
		cy.get("[name='formComidaFavorita']").check(["carne", "frango", "pizza"]);
	});
});
