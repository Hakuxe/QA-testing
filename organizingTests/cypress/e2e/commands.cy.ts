describe("testing custom commands in cypress", () => {
	/*
    AAA Pattern:
      - Arrange: setup and initialization required for the test (creating data required, setting up mocks)
      - Act:take the actions required for a test case. 
      - Assert: verify that our test case did what was expected from it.

      link - https://medium.com/xebia-engineering/unit-testing-aaa-pattern-ab1c08737d53
   */

	it("", () => {
		//arrange
		cy.visit("/");

		//act
		cy.get("li.nav-item").last().click();

		//assert
		cy.url().should("include", "shop");

		cy.get(".card-title").each((el, index, list) => {
			if (el.text().includes("iphone X")) {
				cy.log("founded");
            cy.get("button.btn.btn-info").eq(index);
			}

         //todo finalizar a implementação

			// if(el[index].textContent === "iphone X")
		});
	});
});
