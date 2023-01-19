import { defineConfig } from "cypress";

export default defineConfig({

	env:{
		url:"https://www.rahulshettyacademy.com"
	},
	e2e: {
		setupNodeEvents(on, config) {
			// implement node event listeners here
		},
		baseUrl: "https://www.rahulshettyacademy.com/angularpractice/",
	},
});
