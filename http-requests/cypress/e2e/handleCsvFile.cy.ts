import neatCsv from "neat-csv";

describe("Taking data from  csv ", () => {
	const product = {};

	beforeEach(() => {
		cy.LoginApi("rahulshetty@gmail.com", "Iamking@00");
		cy.visit("https://rahulshettyacademy.com/client");
	});

	it("should first", () => {
		cy.intercept(
			"https://rahulshettyacademy.com/api/ecom/user/add-to-cart"
		).as("addToCart");
		/*
			selecionar um produto 
			armazenar os dados dele 
			finalizar a compra 
			baixar o csv comparar os dados do csv com os do produto 
			
		*/

		cy.get(".card-body button:last-of-type").eq(1).click();

		cy.wait("@addToCart").then(({ request, response }) => {
			if (response) {
				expect(response.statusCode).to.eq(200);
				expect(response.body).to.haveOwnProperty(
					"message",
					"Product Added To Cart"
				);
			}
		});

		cy.get('[routerlink="/dashboard/cart"]').click();

		cy.get(".infoWrap h3").then(($el) => {
			const title = $el.text();
			Object.assign(product, { title });
		});

		cy.get(".infoWrap .prodTotal p").then(($el) => {
			const price = $el.text().split(" ")[1];
			Object.assign(product, { price });
		});

		cy.contains("Checkout").click();

		cy.get("[placeholder*='Country']")
			.type("brazil")
			.then(() => {
				cy.get(".ta-results button").each((element, index, $list) => {
					cy.wrap(element).contains("Brazil").click();
				});
			});

		//TODO preencher o resto do form
		cy.get(".action__submit").click();
		cy.get("tr > button").click();

		cy.readFile(
			Cypress.config("downloadsFolder") + "/order-invoice_rahulshetty.csv"
		).then(async (text) => {
			const csv = neatCsv(text);
			
			console.log(await csv)

		});
	});
});
