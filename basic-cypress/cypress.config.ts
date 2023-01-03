import { defineConfig } from "cypress";

export default defineConfig({
	e2e: {
		setupNodeEvents(on, config) {
			// implement node event listeners here
		},
		viewportWidth: 1280,
		viewportHeight: 720,
		specPattern: [
			"cypress/e2e/**/*.cy.{js,jsx,ts,tsx}",
			"cypress/e2e/**/*.spec.{js,jsx,ts,tsx}", //pode colocar o padrão que desejar
		],

		baseUrl:"https://rahulshettyacademy.com/AutomationPractice/",
	},
});
