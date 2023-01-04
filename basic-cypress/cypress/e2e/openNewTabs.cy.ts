describe("handling new tabs", () => {
	it("check if browser open a new tab", () => {
		//cypress can't move between multiple tabs so we have to remove the attribute target with jquery
		cy.visit("/");

		cy.get("#opentab")
			.invoke("removeAttr", "target")
			.should("not.have.attr", "target");

		cy.get("#opentab").click();

		cy.url().should("equal", "https://www.rahulshettyacademy.com/");
	});

	it("uses the browser commands to manipulate the page", () => {
		cy.go("back"); // equivalent to clicking back button
		cy.go("forward"); // equivalent to clicking forward button

		cy.url().should("include", "rahulshettyacademy");
	});

	it("take the attribute of an element", () => {
		cy.visit("/");

		//pegar o atributo de um elemento
		cy.get("#opentab")
			.invoke("prop", "href")
			.then((prop) => {
				cy.log(prop);
				// cy.visit(prop)
			});

		//outra forma
		cy.get("#opentab").then((element) => {
			const property = element.prop("href");
			cy.visit(property);
		});

		//cypress não permite que seja mudado o domínio de testes
		// ex: g1.com ir para google.com
	});

	
});
