describe("challenge ", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should check required message for name, surname and sex", () => {
		const requiredNameMessage = "Nome eh obrigatorio";
		const requireSurdNameMessage = "Sobrenome eh obrigatorio";
		const requireGenderMessage = "Sexo eh obrigatorio";

		const stub = cy.stub().as("alertMessages");
		const submitBtn = cy.get("#formCadastrar");

		cy.on("window:alert", stub);

		submitBtn.click().then(() => {
			expect(stub.getCall(0)).to.be.calledWith(requiredNameMessage);
		});;

		cy.get("#formNome").type("marco");
		submitBtn.click().then(() => {
			expect(stub.getCall(1)).to.be.calledWith(requireSurdNameMessage);
		});

		cy.get("#formSobrenome").type("calor");
		submitBtn.click().then(() => {
			expect(stub.getCall(2)).to.be.calledWith(requireGenderMessage);
		});


		cy.get("[name='formSexo']").first().check().should("be.checked");
		submitBtn.click();

		cy.get("#resultado span").first().should("contain.text", "Cadastrado!");
	});
});
