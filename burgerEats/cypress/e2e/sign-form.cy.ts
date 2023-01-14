describe("sign as a driver form", () => {
	beforeEach(() => {
		cy.visit(`${Cypress.config().baseUrl}/deliver`);

		cy.url().should("include", "/deliver");
	});

	it("allow user to create an account", () => {
		let deliverer = {
			name: "Wesley Melo",
			cpf: "52038711755",
			email: "teste@gmail.com",
			phone: "999999999",
			vehicleType: "moto",
			cnhPicture: "images/cnh-digital.jpg", // search for file in fixtures
			address: {
				postalcode: "04534011",
				street: "Joaquim Floriano",
				number: "1000",
				district: "Itaim Bibi",
				cityAndState: "São Paulo/SP",
			},
		};

		cy.get("input[name='fullName']").type(deliverer.name);
		cy.get("input[name='cpf']").type(deliverer.cpf);
		cy.get("input[name='email']").type(deliverer.email);
		cy.get("input[name='whatsapp']").type(deliverer.phone);

		cy.get("input[name='postalcode']").type(deliverer.address.postalcode);
		cy.get("input[type='button'][value='Buscar CEP']").click();
		cy.get("input[name='address-number']").type(deliverer.address.number);

		cy.get("input[name='address'][disabled]").should(
			"contain.value",
			deliverer.address.street
		);
		cy.get("input[name='district'][disabled]").should(
			"have.value",
			deliverer.address.district
		);
		cy.get("input[name='city-uf'][disabled]").should(
			"contain.value",
			deliverer.address.cityAndState
		);

		cy.get("ul.delivery-method > li").contains(deliverer.vehicleType, {
			matchCase: false,
		});

		// cy.get("div[class='dropzone']").attachFile(deliverer.cnhPicture, {subjectType: "drag-n-drop"});

		// ^ => procura qualquer coisa que comece com ""
		// $ => termina com texto ""
		cy.get("input[type='file'][accept^='image']").attachFile(
			deliverer.cnhPicture
		);

		cy.get("button[type='submit']").click();

		// check form submitted successfully
		const successMessage =
			"Recebemos os seus dados. Fique de olho na sua caixa de email, pois e em breve retornamos o contato.";
		cy.get(".swal2-container h2#swal2-title").should(
			"have.text",
			"Aí Sim..."
		);

		cy.get(".swal2-container div#swal2-html-container").should(
			"contain.text",
			successMessage
		);
	});

	it("don't allow register with invalid cpf", () => {
		let deliverer = {
			name: "Wesley Melo",
			cpf: "52038711755$#AAA",
			email: "teste@gmail.com",
			phone: "999999999",
			vehicleType: "moto",
			cnhPicture: "images/cnh-digital.jpg", // search for file in fixtures
			address: {
				postalcode: "04534011",
				street: "Joaquim Floriano",
				number: "1000",
				block: "Itaim Bibi",
				cityAndUf: "São Paulo/SP",
			},
		};

		cy.get("input[name='fullName']").type(deliverer.name);
		cy.get("input[name='cpf']").type(deliverer.cpf);
		cy.get("input[name='email']").type(deliverer.email);
		cy.get("input[name='whatsapp']").type(deliverer.phone);

		cy.get("input[name='postalcode']").type(deliverer.address.postalcode);
		cy.get("input[type='button'][value='Buscar CEP']").click();
		cy.get("input[name='address-number']").type(deliverer.address.number);

		cy.get("input[name='address'][disabled]").should(
			"contain.value",
			deliverer.address.street
		);
		cy.get("input[name='district'][disabled]").should(
			"have.value",
			deliverer.address.block
		);
		cy.get("input[name='city-uf'][disabled]").should(
			"contain.value",
			deliverer.address.cityAndUf
		);

		cy.get("ul.delivery-method > li").contains(deliverer.vehicleType, {
			matchCase: false,
		});

		cy.get("input[type='file'][accept^='image']").attachFile(
			deliverer.cnhPicture
		);

		cy.get("button[type='submit']").click();

		//check input erro: cpf invalid
		cy.get("span[class='alert-error']").should(
			"have.text",
			"Oops! CPF inválido"
		);
	});
});
