describe("template spec", () => {
	it("it intercept get books request", () => {
		//  interceptar um requisição cy.intercept({request object}, {response object});

		cy.visit("https://rahulshettyacademy.com/angularAppdemo/");
		
		cy.intercept(
			{
				method: "GET",
				url: "https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty",
			},
			{
				statusCode: 200,
				body: [{ book_name: "salva logo ", isbn: "SPY40", aisle: "2529857" }],
			}
		).as("getBook");

		cy.get("[data-target='#exampleModal']").click();

		cy.wait("@getBook");
	});
});
