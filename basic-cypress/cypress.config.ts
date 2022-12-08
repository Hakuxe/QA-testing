import { defineConfig } from "cypress";

export default defineConfig({
	e2e: {
		setupNodeEvents(on, config) {
			// implement node event listeners here
		},
		specPattern: [
			"cypress/e2e/**/*.cy.{js,jsx,ts,tsx}",
			"cypress/e2e/**/*.spec.{js,jsx,ts,tsx}", //pode colocar o padrão que desejar
		],

		//baseUrl:"http://localhost:3000",
	},
});
