describe("template spec", () => {
	it("passes", () => {
		cy.visit("https://example.cypress.io");
		cy.log("olha meu log");

		cy.get("a").contains("get");

		expect(1).equal(1);
		expect(3).not.equal(2);
		expect([1, 2, 3, 5]).contain(3);
	});

	it("Truthy asserts", () => {
		expect(null).to.be.null;
		expect(true).to.be.true;
		expect(undefined).to.be.undefined;
	});

	it("Object asserts", () => {
		const obj = { a: 1, b: 2 };

		expect(obj).equal(obj);
		expect(obj).eq(obj);
		expect(obj).deep.equal({ a: 1, b: 2 });
		// expect(obj).equal({ a: 1, b: 2 }); //note the same reference
		expect(obj).eql({ a: 1, b: 2 }); // igual o deep equals

		expect(obj).include({ a: 1 });

		expect(obj).to.have.property("b");
		expect(obj).to.have.property("a");
		expect(obj).to.not.be.empty;
	});

	it("Array's asserts", () => {
		const arr = [1, 2, 3, 4, 5, 6];

		expect(arr).to.have.members([1, 2, 3, 4, 5, 6]);
		expect(arr).to.include.members([1, 2]);
		expect(arr).not.be.empty;
		expect([]).to.be.empty;

		expect(arr).to.contain(1);
	});

	it("types", () => {
		expect(23).to.be.a("number");
		expect(true).to.be.a("boolean");
		expect("string").to.be.a("string");
		expect({}).to.be.an("object");
		expect([]).to.be.an("array");
	});
});
