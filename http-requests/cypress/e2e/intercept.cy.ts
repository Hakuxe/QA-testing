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
				body: [
					{ book_name: "salva logo ", isbn: "SPY40", aisle: "2529857" },
				],
			}
		).as("getBook");

		cy.get("[data-target='#exampleModal']").click();

		cy.wait("@getBook");
	});

	it("should check if response and ui are showing the same amount of itens ", () => {
		cy.visit("https://rahulshettyacademy.com/angularAppdemo/");

		cy.intercept({
			method: "GET",
			url: "https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty",
		}).as("getBook");

		cy.get("[data-target='#exampleModal']").click();

		cy.get("tr").as("getRows");
		cy.wait("@getBook").then(({ request, response }) => {
			console.log(response);

			if (response) {
				cy.get("tbody > tr").should("have.length", response.body.length);
			}
			// expect(response.body.length).to.eq(1515);
		});
	});

	it.only("it should intercept the book request and mock the response data", () => {
		const apiUrl = `${Cypress.env(
			"apiUrl"
		)}/Library/GetBook.php?AuthorName=shetty`;
		
		cy.visit("/");

		console.log(Cypress.env("apiUrl"));
		cy.intercept(
			{
				method: "GET",
				url: apiUrl, //não aceita template literals
			},

			[
				{
					book_name: "teste intercept",
					isbn: "SPY40",
					aisle: "1234",
				},
			]
		).as("getAllBooks");

		cy.get('[data-target="#exampleModal"]').click();

		// espera a request ser processada antes de seguir
		cy.wait("@getAllBooks");
	});
});
