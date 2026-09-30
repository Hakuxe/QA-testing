import { test, expect } from "@playwright/test";

test("creating browser with context", async ({ browser }) => {
  const context = await browser.newContext();
  const page = await context.newPage();

  // selectors
  const usernameInput = page.locator("#username");
  const passwordInput = page.locator("#password");
  const cardTitles = page.locator(".card-body .card-title a");

  await page.goto("https://rahulshettyacademy.com/loginpagePractise");
  await usernameInput.fill("hello@thunderbird.com");
  await passwordInput.fill("1234");
  await page.locator("#signInBtn").click();
  const message = await page.locator(".alert").textContent();

  expect(message).toContain("Incorrect");

  // await page.locator("#username").clear()
  // await page.locator("#password").clear()

  await usernameInput.fill("rahulshettyacademy");
  await passwordInput.fill("Learning@830$3mK2");
  
  const allTittles = await cardTitles.allTextContents();
  console.log(allTittles)

});

test.skip("default page playwright test", async ({ page }) => {
  await page.goto("https://www.google.com");

  const title = await page.title();
  expect(title).toEqual("Google");
});
