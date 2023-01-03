describe("handling web tables", () => {
	it("should check if selenium price is 25", () => {
		cy.visit("/");

		cy.get("#product")
			.find("td")
			.each((item, index, list) => {
				if (
					item.text() ==
					"Master Selenium Automation in simple Python Language"
				) {
					// cy.next pick the next sibling element
					cy.wrap(item)
						.next()
						.then((price) => {
							expect(price.text()).to.eq("25");
						});
					//expect(cy.get('td').eq(index).next()).to.eq("25");
				}
			});

      // another solution
		cy.get("tr td:nth-child(2)").each(($e1, index, $list) => {
			const text = $e1.text();
			if (text.includes("Python")) {
				cy.get("tr td:nth-child(2)")
					.eq(index)
					.next()
					.then(function (price) {
						const priceText = price.text();
						expect(priceText).to.equal("25");
					});
			}
		});
	});
});
