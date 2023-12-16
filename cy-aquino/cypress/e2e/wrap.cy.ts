describe("using wrap and its", () => {
	it("should use wrap and its", () => {
		const obj = { name: "user", age: 20 };

		expect(obj).to.have.property("name");

		// nesse caso o wrap transforma o obj( objeto js) num objeto do cypress
		cy.wrap(obj).should("have.a.property", "name");

		cy.wrap(obj).its("name").should("equals", "user");
		cy.wrap(obj).its("name").should("contains", "user");
	});
});
