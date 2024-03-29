import {products} from "../fixtures/example.json";
describe("testing custom commands in cypress", () => {
	/*
    AAA Pattern:
      - Arrange: setup and initialization required for the test (creating data required, setting up mocks)
      - Act:take the actions required for a test case. 
      - Assert: verify that our test case did what was expected from it.

      link - https://medium.com/xebia-engineering/unit-testing-aaa-pattern-ab1c08737d53
   */

	it("testing cypress commands", () => {
		//arrange
		cy.visit(Cypress.env("url") + "/angularpractice/");

		//act
		cy.get("li.nav-item").last().click().debug();

		//assert
		cy.url().should("include", "shop");

		products.map(item =>{
			cy.addProductToCart(item);
		})
	});
});
