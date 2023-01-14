export class SignUpPage {
	go(url: string) {
		cy.visit(url);
	}

	checkElementHaveText(selector: string, message: string) {
		cy.get(selector).should("have.text", message);
	}

	fillForm(deliverer: IDeliverer) {
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
	}

	submitForm() {
		cy.get("button[type='submit']").click();
	}

	alertMessageShouldBe(alertMessage: string) {
		cy.contains(".alert-error", alertMessage).should("be.visible");
	}
}
