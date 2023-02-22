describe("Making login by api call", () => {
	beforeEach(() => {
		cy.LoginApi("rahulshetty@gmail.com", "Iamking@00");
	});

	it("should login by api and store the jwt token ", () => {
		cy.visit("https://rahulshettyacademy.com/client", {
			onBeforeLoad: () =>
				localStorage.setItem("token", Cypress.env("token")),
		});
	});
});
