import { contains } from "cypress/types/jquery";

describe("Making login by api call", () => {
	beforeEach(() => {
		cy.LoginApi("rahulshetty@gmail.com", "Iamking@00");
    cy.visit("https://rahulshettyacademy.com/client");
	});

	it("should add product to cart and checkout the purchase", () => {
		// cy.visit("https://rahulshettyacademy.com/client");
		cy.get(".card-body button:last-of-type").eq(1).click();
		cy.get("[routerlink='/dashboard/cart']").click();
		cy.contains("Checkout").click();
		cy.get("[placeholder*='Country']")
			.type("brazil")
			.then(() => {
				cy.get(".ta-results button").each((element, index, $list) => {
					cy.wrap(element).contains("Brazil").click();
				});
			});
    
      cy.get(".action__submit").click();
      cy.get("tr > button").click();
	});
});
