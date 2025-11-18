describe("test using clock", () => {
  beforeEach(() => {
    cy.visit("https://wcaquino.me/cypress/componentes.html");
  });

 it('should use a past date', () => { 
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("contain", "17/01/2024");

    // cy.clock(); default date of js 31/12/1969
    // cy.get("#buttonNow").click();
    // cy.get("#resultado > span").should("contain", "31/12/1969");

    const date = new Date(2016, 5, 10, 11, 59);
    cy.clock(date.getTime());
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("have.text", "10/06/2016, 11:59:00");
  });

  it.only('should use tick to move time to future', () => { 
    const date = new Date(2016, 5, 10, 11, 59);
    cy.clock(date.getTime());
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("have.text", "10/06/2016, 11:59:00");

    //  avançar 10 segundos no tempo dentro do cypress
    cy.tick(10000) 
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("have.text", "10/06/2016, 11:59:10");

    cy.tick(1000 *600) //10 minutos a mais 
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("have.text", "10/06/2016, 12:09:10");

    cy.tick(1000 *3600) //1 hora a mais 
    cy.get("#buttonNow").click();
    cy.get("#resultado > span").should("have.text", "10/06/2016, 13:09:10");
  })
});
