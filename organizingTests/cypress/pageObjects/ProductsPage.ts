import {products} from '../fixtures/example.json';
export class ProductsPage {
	selectProducts() {
		cy.url().should("include", "shop");

		products.map((item) => {
			cy.addProductToCart(item);
		});
	}

   getCheckoutButton(){
      return cy.get("a.nav-link.btn.btn-primary");
   }
}
