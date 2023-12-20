describe("testing the use of invoke", () => {
	it("should use invoke method with pure js", () => {
		const returnName = () => "marina";
		const sum = (a: number, b: number) => a + b;

		cy.wrap({ fn: returnName }).invoke("fn").should("equal", "marina");
		cy.wrap({ fn: sum }).invoke("fn", 1, 2).should("equal", 3);
	});

	it("should use invoke on element", () => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");

		// invoke executa uma função do objeto localizado
		cy.get("#formNome")
			.invoke("val", "digitando com invoke")
			.should("have.value", "digitando com invoke");

		// invocando função hide do objeto input
		cy.get("#formNome").invoke("hide").should("not.be.visible");
	});

	it.only("should solve the challenge give by the teacher", () => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");

		cy.title().then(($title) => {
			cy.get("#formNome").type($title);
		});
	});

	let syncTitle;

	it.only("should teacher solution", () => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");

		cy.title().then(($title) => {
			syncTitle = $title;
		});

		cy.get("#formNome").then($el => cy.wrap($el).type(syncTitle));
	});
});
