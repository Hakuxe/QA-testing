// Login in the e-commerce app https://rahulshettyacademy.com/client/#/auth/login
// select a product, add it to the cart dynamically(find all products and search for the one you want)
// open the cart check the product name and price to make sure it's the right one
// go to checkout
// add fake credit cart information
// check if login email is present in shipping info
// select a country for the shipping information
// apply coupon and check if it was applied
// finish the purchase and check success message
// capture the order id
// go to history page
// check  if your order is there
// open it and check data

import { expect, test } from "@playwright/test";

const userData = {
  firstName: "John",
  lastName: "doe",
  email: "john.doe12@example.com",
  phone: "1234567890",
  password: "Password@123!",
  country: "Brazil",
};

const date = new Date();

const todayDateFormatted = date
  .toLocaleDateString("en-US", {
    weekday: "short",
    month: "short",
    day: "2-digit",
  })
  .replace(",", "");


test("", async ({ page }) => {
  const desiredProduct = "iphone 13 pro";
  const desiredProductPrice = "55000";

  await page.goto("https://rahulshettyacademy.com/client/#/auth/login");

  await page.locator("#userEmail").fill(userData.email);
  await page.locator("#userPassword").fill(userData.password);
  await page.locator("#login").click();

  const cardBody = await page.locator(".card-body").filter({ hasText: desiredProduct });

  await cardBody.getByRole("button", { name: "Add To Cart" }).click();

  const alert = page.getByRole("alert");
  await expect(alert).toBeVisible();
  await expect(alert).toHaveText("Product Added To Cart");

  const price = await cardBody.locator(".text-muted").textContent();
  await expect(price?.split(" ").at(1)).toContain(desiredProductPrice);

  await page.locator('[routerlink="/dashboard/cart"]').click();

  await expect(page.locator(".cart").getByRole("heading", { level: 3 })).toHaveText(desiredProduct);

  // Fill credit car info
  await page.getByRole("button", { name: "Checkout" }).click();
  await page
    .locator("xpath=//*[@class='form__cc']//*[contains(text(),'Credit Card Number')]/../input")
    .fill("6666");
  await page.locator(".form__cc").getByRole("textbox").first().fill("9996 6666 9222 3333");
  await page.locator(".form__cc").getByRole("textbox").nth(1).fill("454");
  await page.locator(".form__cc").getByRole("textbox").nth(2).fill("John Doe");
  await page.locator(".form__cc [name='coupon']").fill("4");
  await page.getByRole("button", { name: "Apply Coupon" }).click();

  // apply cupon
  await page
    .locator("xpath=//*[@class='form__cc']//*[contains(text(),'Apply Coupon')]/../p")
    .waitFor({ state: "visible" });

  const text = await page
    .locator("xpath=//*[@class='form__cc']//*[contains(text(),'Apply Coupon')]/../p")
    .textContent();

  console.log(text);

  // shipping info
  expect(await page.locator(".details__user").getByRole("textbox").first().inputValue()).toEqual(
    userData.email,
  );

  await page
    .locator(".details__user")
    .getByPlaceholder("Select Country")
    .pressSequentially("Brazil");

  await page.getByText("Brazil").click();
  await page.getByText("Place Order").click();

  // order checking

  await expect(page.getByRole("heading", { level: 1 })).toHaveText("Thankyou for the order.");

  const orderNumber = await page.locator("label.ng-star-inserted").textContent();
  const cleanOrderNumber = orderNumber?.replaceAll("|", "").trim();

  await page.getByRole("button", { name: "ORDERS" }).click();

  // Check data from order
  const table = await page.getByRole("table");
  const targetRow = table.locator("tr", { hasText: cleanOrderNumber });

  await expect(targetRow).toContainText(cleanOrderNumber!);
  await expect(targetRow).toContainText(desiredProduct);
  await expect(targetRow).toContainText(desiredProductPrice);
  await expect(targetRow).toContainText(todayDateFormatted);

});
