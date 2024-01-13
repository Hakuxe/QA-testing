// error : Cannot find module '@fixtures/user.json'. Did you mean to set the 'moduleResolution' option to 'NodeNext', or to add aliases to the 'paths' option?

// change the cypress/tsconfig json  to moduleResolution": "NodeNext",
import user from "../fixtures/user.json";

describe("test using fixture", () => {
	beforeEach(() => {
		cy.visit("https://wcaquino.me/cypress/componentes.html");
	});

	it("should fill the form using fixtures", () => {
		cy.fixture<userFixture>("user").then((user) => {
			cy.get("#formNome").type(user.name);
			cy.get("#formSobrenome").type(user.surname);
			cy.get("[name='formSexo']").check(user.gender);
			cy.get("[name='formComidaFavorita']").check(user.favoriteFood);
			cy.get("[data-test='dataEscolaridade']").select(user.educationLevel);
			cy.get("#formEsportes").select(user.sports);

			cy.get("#formCadastrar").click();
			cy.get("#resultado span").first().should("have.text", "Cadastrado!");
		});
	});

	it("using import statement", () => {
		cy.get("#formNome").type(user.name);
		cy.get("#formSobrenome").type(user.surname);
		cy.get("[name='formSexo']").check(user.gender);
		cy.get("[name='formComidaFavorita']").check(user.favoriteFood);
		cy.get("[data-test='dataEscolaridade']").select(user.educationLevel);
		cy.get("#formEsportes").select(user.sports);

		cy.get("#formCadastrar").click();
		cy.get("#resultado span").first().should("have.text", "Cadastrado!");
	});
});
