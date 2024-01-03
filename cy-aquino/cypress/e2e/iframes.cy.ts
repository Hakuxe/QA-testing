describe("first", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("shouldn't be able to handle an iframe", () => {

		// cypress tem dificuldade para lidar com iframes
		cy.get("#frame1").then((iframe) => {
			const body = iframe.contents().find("body");

			cy.wrap(body)
				.find("#tfield")
				.type("achou o frame")
				.should("have.value", "achou o frame");
		});
	});

	it("workaround to handle an iframe: making it the primary window", () => {
		cy.visit("https://wcaquino.me/cypress/frame.html")
	

		cy.get("#otherButton").click();
		cy.on("window:alert", (message) => {
			expect(message).be.equal("Click OK!")
		})
	});
	
});
