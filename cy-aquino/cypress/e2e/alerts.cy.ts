describe("using alerts", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should open an alert on the page", () => {
		cy.get("#alert").click();

		cy.on("window:alert", (alert) => {
			expect(alert).equal("Alert Simples");
		});
	});

	it("should using stub to handle alerts", () => {
		const stub = cy.stub().as("alert-test");

		cy.on("window:alert", stub);

		cy.get("#alert")
			.click()
			.then(() => {
				expect(stub.getCall(0)).to.be.calledWith("Alert Simples");
			});
	});

	it("should  confirm an alert", () => {
		cy.on("window:confirm", (text) => {
			expect(text).to.be.equal("Confirm Simples");
		});

		cy.on("window:alert", (message) => {
			expect(message).to.be.equal("Confirmado");
		});

		cy.get("#confirm").click();
	});

	it("should  deny an alert", () => {
		cy.on("window:confirm", (text) => {
			expect(text).to.be.equal("Confirm Simples");
			return false;
		});

		cy.on("window:alert", (message) => {
			expect(message).to.be.equal("Negado");
		});

		cy.get("#confirm").click();
	});

	it("should deny an prompt entry", () => {
		cy.on("window:confirm", (text) => {
			expect(text).to.be.equal("Era null?");
			return false;
		});

		cy.on("window:alert", (message) => {
			expect(message).equal(":(");
		});

		cy.get("#prompt").click();
	});

	it("should type something in the prompt input", () => {
		cy.window().then((win) => {
			cy.stub(win, "prompt").returns("20");
		});

		cy.on("window:confirm", (text) => {
			expect(text).to.be.equal("Era 20?");
		});

		cy.on("window:alert", (message) => {
			expect(message).equal(":D");
		});

		cy.get("#prompt").click();
	});
});
