describe("how to do mouse hover with cypress", () => {
	it("should be able to simulate a hover element effect ", () => {
		cy.visit("/");

		//invoking a jquery command
		cy.get(".mouse-hover-content").invoke("show");
		cy.contains("Top").click();
		cy.url().should("include", "top");
		//other option
      //cy.contains("Top").click(force:true);
   })
});
