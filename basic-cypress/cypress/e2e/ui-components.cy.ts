describe("Manipulating inputs", () => {
	const url = "https://rahulshettyacademy.com/AutomationPractice/";

	context("checkboxes", () => {
		it("checking and unchecking checkboxes", () => {
			cy.visit(url);

			cy.get("input[value='option1']").check().should("be.checked");
			cy.get("input[value='option1']").uncheck().should("not.be.checked");

			//marking multiple checkboxes
			// Check the checkboxes with the values 'option2' and 'option3'
			cy.get("input[type='checkbox']").check(["option2", "option3"]);

			// static dropdown select
			//cy.get("#dropdown-class-example").select("option2"); // value
			//cy.get("#dropdown-class-example").select("Option1"); //text
			//cy.get("#dropdown-class-example").select(3); // index
			cy.get("#dropdown-class-example")
				.select("option2")
				.should("have.value", "option2");

			// dynamic dropdown select (similar to search suggestions)
			const mySearch = "brazil";
			cy.get("#autocomplete").type(mySearch.slice(0,3));

			cy.get("#ui-id-1")
				.find(".ui-menu-item")
				.each((suggestion) => {
					if (suggestion.text().toLocaleLowerCase() === mySearch) {
						cy.wrap(suggestion).click();
					}
				});


			// radio button

			cy.get("input[value='radio2']").check().should("be.checked"); // click also works
		});
	});
});
