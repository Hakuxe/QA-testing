import { HomePage } from "../pageObjects/HomePage";
import { ProductsPage } from "../pageObjects/ProductsPage";

import user from "../fixtures/user.json";

describe("testing the page object model for tests", () => {
	it("should fill the homepage form", () => {
		const homePage = new HomePage();

		cy.visit("/");

		homePage.getEditBox().type(user.name);
		homePage.getGender().select(user.gender);
		homePage.getTwoWayDataBinding().should("have.value", user.name);
		homePage.getEditBox().should("have.attr", "minLength", "2");
		homePage.getEntrepreneur().should("be.disabled");

		homePage.getShopTab().click();
	});

	it.only("should add products to the cart", () => {
		const productsPage = new ProductsPage();
		let cartTotal = 0;

		cy.visit("/shop");
		productsPage.selectProducts();

		productsPage.getCheckoutButton().click();

		cy.get("tbody")
			.find("td:nth-child(4)")
			.each((el, index, list) => {
				const elConvertedToNumber = Number(el.text().slice(2));
				const isElNaN = elConvertedToNumber * 1;

				if (!Number.isNaN(isElNaN)) {
					cartTotal += elConvertedToNumber;
				}

				//another way
				// let res = el.text().split(" ");
				// let cleanRes = res[1];

				// cy.log(cleanRes);
				// cartTotal = cartTotal + Number(cleanRes);
			});

		cy.get("h3 > strong").then((element) => {
			const elConvertedToNumber = Number(element.text().slice(2));

			expect(elConvertedToNumber).to.eq(cartTotal);
		});

		cy.contains("Checkout").click();

		cy.get("#country").type("Italy");

		cy.get("div.suggestions", { timeout: 6000 })
			.find("a")
			.should("contain.text", "Italy")
			.click();

		cy.get("input[value='Purchase']").click();

		cy.get("div.alert.alert-success").should("include.text", "Success");
	});
});
