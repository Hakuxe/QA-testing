describe("Learning how to handle alert messages", () => {
	it("handling alter pop-up", () => {
     
      cy.visit("/");

      //cypress handle popups by default
      cy.get("#alertbtn").click();
      cy.get("input[value='Confirm']").click();

      // listening to events on the browser
      cy.on("window:alert", (stringOfTheAlert) =>{
         expect(stringOfTheAlert).include("Hello , share this practice page and share your knowledge");
      });

      cy.on("window:confirm", (textOfTheConfirmPopup) =>{
         expect(textOfTheConfirmPopup).include("Hello , Are you sure you want to confirm?");
      });
   });
});
