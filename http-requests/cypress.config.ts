import { defineConfig } from "cypress";
export default defineConfig({
	e2e: {
		setupNodeEvents(
			on: Cypress.PluginEvents,
			config: Cypress.PluginConfigOptions
		) {
			// implement node event listeners here
			require("cypress-failed-log/on")(on);
		},

		baseUrl: "https://rahulshettyacademy.com/angularAppdemo/",
	},
	env: {
		apiUrl: "https://rahulshettyacademy.com",
	},
});
