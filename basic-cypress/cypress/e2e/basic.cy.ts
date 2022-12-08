describe("Basic testing ", ()=>{

   // some cool links
   // https://rahulshettyacademy.com/AutomationPractice/
   // https://rahulshettyacademy.com/seleniumPractise/#/


   it.only("search input should return at least one element",() =>{
      cy.visit("https://rahulshettyacademy.com/seleniumPractise/#/");

      cy.get(".search-keyword").type("ca");

      cy.get(".products").find(".product:visible");



      cy.get(".product").should("be.visible").and("have.length.at.least", 2);

      //another option
      cy.get(".product:visible").should("have.length.at.least", 1);
   })
});