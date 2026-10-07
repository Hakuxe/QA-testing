// https://rahulshettyacademy.com/client/#/auth/login

import { test, expect } from "@playwright/test";

test.only("", async ({ page }) => {

  await page.route('**/*.{webp}', route => route.fulfill({status: 200}));

  await page.goto("https://rahulshettyacademy.com/client/#/auth/login");

  const userData = {
    firstName: "John",
    lastName: "doe",
    email: "john.doe12@example.com",
    phone: "1234567890",
    password: "Password@123!",
  };
 
  await page.locator("#userEmail").fill(userData.email);
  await page.locator("#userPassword").fill(userData.password);
  await page.getByText("Login").click()

  
  const productTitle = await page.getByRole("heading", { level: 5 });

  await expect(productTitle.first()).toHaveText("ADIDAS ORIGINAL", { ignoreCase: true });

});
