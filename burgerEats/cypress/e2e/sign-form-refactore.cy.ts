import { SignUpPage } from "../pages/SighUpPage";

import createRandomDeliverer from "../factories/SignUpFactory";
("../factories/SignUpFactory");

describe("sign as a driver form", () => {
	const signUp = new SignUpPage();

	beforeEach(function () {
		signUp.go(`${Cypress.config().baseUrl}/deliver`);

		cy.url().should("contain", "/deliver");

		cy.fixture("deliverer").then((data) => {
			this.delivererJson = data;
		});
	});

	it("allow user to create an account", function () {
		const signupValid = createRandomDeliverer();

		signUp.fillForm(signupValid);
		signUp.submitForm();

		// check form submitted successfully
		const successMessage =
			"Recebemos os seus dados. Fique de olho na sua caixa de email, pois e em breve retornamos o contato.";

		signUp.checkElementHaveText(
			".swal2-container div#swal2-html-container",
			successMessage
		);
		signUp.checkElementHaveText(
			".swal2-container h2#swal2-title",
			"Aí Sim..."
		);
	});

	it("don't allow register with invalid cpf", function () {
		const cpfInvalid = createRandomDeliverer();
		cpfInvalid.cpf = "$#$#$#%#$%$121AAV";

		signUp.fillForm(cpfInvalid);
		signUp.submitForm();

		//check input erro: cpf invalid
		signUp.checkElementHaveText(
			"span[class='alert-error']",
			"Oops! CPF inválido"
		);
	});

	it("don't allow register with invalid email", function () {
		const emailInvalid = createRandomDeliverer();
		emailInvalid.email = "user.com.br";

		signUp.fillForm(emailInvalid);
		signUp.submitForm();

		signUp.checkElementHaveText(
			"span[class='alert-error']",
			"Oops! Email com formato inválido."
		);
	});

	context("required fields ", function () {
		const messages = [
			{ field: "name", output: "É necessário informar o nome" },
			{ field: "cpf", output: "É necessário informar o CPF" },
			{ field: "email", output: "É necessário informar o email" },
			{ field: "postalcode", output: "É necessário informar o CEP" },
			{
				field: "number",
				output: "É necessário informar o número do endereço",
			},
			{ field: "vehicleType", output: "Selecione o método de entrega" },
			{ field: "cnhPicture", output: "Adicione uma foto da sua CNH" },
		];

		messages.forEach((message) => {
			it(`${message.field} is required`, function () {
				signUp.submitForm();
				signUp.alertMessageShouldBe(message.output);
			});
		});
	});
});
