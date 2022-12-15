describe("Basic testing ", () => {
	// some cool links
	// https://rahulshettyacademy.com/AutomationPractice/
	// https://rahulshettyacademy.com/seleniumPractise/#/

	it.only("search input should return at least one element", () => {
		cy.visit("https://rahulshettyacademy.com/seleniumPractise/#/");

		//cypress uses promises on every command

		//check brand text
		cy.get(".brand").should("have.text", "GREENKART");

		cy.get(".brand").then(($element) => {
			console.log($element.text());
		});

		cy.get(".search-keyword").type("ca");

		cy.get(".product").should("be.visible").and("have.length.at.least", 2);

		//another option
		cy.get(".product:visible").should("have.length.at.least", 1);

		cy.get(".products").as("productsLocator");

		// pick second product and add1s to cart
		cy.get("@productsLocator")
			.find(".product")
			.eq(2)
			.contains("add to cart", { matchCase: false })
			.click();

		//find carrots
		cy.get("@productsLocator")
			.find(".product")
			.each(($element, index, $list) => {
				const productName = $element
					.find("h4.product-name")
					.text()
					.toLocaleLowerCase();

				if (productName.includes("carrot")) {
					cy.wrap($element).find("button").click();
				}
			});

		//finish order on page
		cy.get("a.cart-icon").click();

		cy.get(".cart-preview")
			.should("have.class", "active")
			.then(($element) => {
				const checkoutButton = $element.find("button");
				cy.wrap(checkoutButton).click();
			});

		cy.url().should("include", "https://rahulshettyacademy.com/seleniumPractise/#/cart");

		cy.get("button").contains("Place Order").click();
	});
});
