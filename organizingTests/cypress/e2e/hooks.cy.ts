import user from "../fixtures/user.json";

describe("template spec", () => {
	beforeEach(() => {
		// one way to use fixture but it doesn't work with arrow function
		// cy.fixture("user").then(data => {
		// 	this.data = data
		// })
		cy.visit("/");
	});

	it("fill in the name and gender fields on the form", () => {
		cy.get("input[name='name']").first().type(user.name);
		cy.get("#exampleFormControlSelect1").select(user.gender);
	});

	it("checks if input name have the correct min length", () => {
		// getting element attribute
		// .first() -> get the first element of a list
		cy.get("input[name='name']").first().should("have.attr", "minLength", 2);
	});

	it("verify if Entrepreneur option is disable in Employment Status", () => {
		cy.get("#inlineRadio3").should("be.disabled");
	});
});
