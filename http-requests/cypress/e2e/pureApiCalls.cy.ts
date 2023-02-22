describe("send requests", () => {
	it("send an request without ui element manipulation", () => {
		const payload = {
			name: "Cronicas de uma vida",
			isbn: "bcdsss",
			aisle: "22s7",
			author: "john snow",
		};

		// cy.request(method, url, body?)
		cy.request(
			"GET",
			"http://216.10.245.166/Library/Addbook.php",
			JSON.stringify(payload)
		).then((response) => {
			expect(response.isOkStatusCode).to.eq(true);
			expect(response.body).to.have.all.keys("Msg", "ID");
			expect(response.body).to.haveOwnProperty("Msg","successfully added");
		});
	});
});
