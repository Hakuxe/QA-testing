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
		});
	});
});
