import user from "../fixtures/user.json";

describe("template spec", () => {
	beforeEach(() => {
		// one way to use fixture but it doesn't work with arrow function
		// cy.fixture("user").then(data => {
		// 	this.data = data
		// })
	});

	it("passes", () => {
		cy.visit("https://www.rahulshettyacademy.com/angularpractice/");

		cy.get("input[name='name']:nth-child(1)").type(user.name);
		cy.get("#exampleFormControlSelect1").select(user.gender);

		// getting element attribute
		cy.get("input[name='name']:nth-child(1)").then((element) => {
			const prop = element.prop("minlength");
			cy.log(prop);
			expect(prop).to.equal(2);
		});

		cy.get("input[name='name']:nth-child(1)").should("have.attr", "minlength", 2);
	});
});
