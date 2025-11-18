describe("using links", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should open a link dynamically", () => {
		cy.get("a")
			.contains("Popup2")
			.then(($link) => {
				const href = $link.prop("href");
				cy.visit(href);

				cy.get("#tfield").type("acessando o link");

				cy.on("window:alert", (message) => {
					expect(message).to.be.equal("Click OK!");
				});

				cy.get("#otherButton").click();
			});
	});

	it("should remove target from html link element", () => {
		cy.get("a")
			.contains("Popup2")
			.then(($link) => {
				$link.removeAttr("target");

				cy.wrap($link).click();

				cy.get("#tfield").type("acessando o link");

				cy.on("window:alert", (message) => {
					expect(message).to.be.equal("Click OK!");
				});

				cy.get("#otherButton").click();
			});
	});

	it("should remove target using invoke", () => {
		cy.get("a").contains("Popup2").invoke("removeAttr", "target").click();

		cy.get("#tfield").type("acessando o link");

		cy.on("window:alert", (message) => {
			expect(message).to.be.equal("Click OK!");
		});

		cy.get("#otherButton").click();
	});
});
