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

	it("should check if response and ui are showing the same amount of itens ", () =>{

		cy.visit("https://rahulshettyacademy.com/angularAppdemo/");
		
		cy.intercept(
			{
				method: "GET",
				url: "https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty",
			}).as("getBook");

		cy.get("[data-target='#exampleModal']").click();

		cy.get("tr").as("getRows")
		cy.wait("@getBook").should(({request, response}) => {
			console.log(response);

			cy.get("tr")
			
			// expect(response.body.length).to.eq(1515);

		});

	})
});
