import { expect, test } from "@playwright/test";

const userData = {
  firstName: "John",
  lastName: "doe",
  phone: "+91 98765 43210",
  email: "john.doe12@example.com",
  password: "Password@123!",
};

function getNextYearDate() {
  const eventDate = new Date();
  eventDate.setFullYear(eventDate.getFullYear() + 1);
  return eventDate.toISOString().substring(0, 16);
}

// https://eventhub.rahulshettyacademy.com/login


// Step 1 — Login
test("should first", async ({ page }) => {
  const BASE_URL = "https://eventhub.rahulshettyacademy.com";

  let eventList;
  const eventTitle = `Test Event${Date.now()}`;
  let seatsBeforeBooking;

  await page.goto("");

  // Step 1 — Login
  await page.getByLabel("Email").fill(userData.email);
  await page.getByLabel("Password").fill(userData.password);
  await page.getByRole("button", { name: "Sign In" }).click();
  await expect(page.getByRole("link", { name: "Browse Events", exact: true })).toBeVisible();

  // Step 2 — Create a new event
  await page.getByRole("button", { name: "Admin" }).click();
  await page.locator("nav a[href='/admin/events']").click();
  await page.getByTestId("event-title-input").fill(eventTitle);
  await page.getByRole("textbox", { name: "Describe the event…" }).fill("This is a test event");
  await page.getByRole("textbox", { name: "City*" }).fill("New York");
  await page.getByRole("textbox", { name: "Venue*" }).fill("Test Venue");
  await page.getByRole("textbox", { name: "Event Date & Time*" }).fill(getNextYearDate());
  await page.getByRole("spinbutton", { name: "Price ($)*" }).fill("100");
  await page.getByRole("spinbutton", { name: "Total Seats*" }).fill("50");
  await page.getByTestId("add-event-btn").click();

  await expect(page.getByText("Event created!")).toBeVisible();
  await expect(page.getByText("Event created!")).toBeEnabled();

  // Step 3 — Find the event card and capture seats

  await page.getByTestId("nav-events").click();
  await page.getByTestId("event-card").first().waitFor({ state: "visible" });

  eventList = await page.getByTestId("event-card").all();
  for (const event of eventList) {
    const cardTitle = (await event.locator("a h3").textContent())?.trim();

    if (cardTitle === eventTitle) {
      await expect(event).toBeVisible();

      const seatsText = await event.getByText("seats available").textContent();
      seatsBeforeBooking = Number(seatsText?.replace(/\D/g, ""));

      await event.getByTestId("book-now-btn").click();
      break;
    }
  }

  //Step 4 and 5 — Fill booking form
  await expect(page.locator("#ticket-count")).toHaveText("1");
  await page.getByRole("textbox", { name: "Full Name*" }).fill("John");
  await page.getByTestId("customer-email").fill("john.doe12@example.com");
  await page.getByRole("textbox", { name: "Phone Number*" }).fill("+91 98765 43210");
  await page.getByRole("button", { name: "Confirm Booking" }).click();

  // Step 6 — Verify booking confirmation
  await expect(page.locator(".booking-ref")).toBeVisible();
  const bookingRef = await page.locator(".booking-ref").textContent();

  // Step 7 — Verify in My Bookings
  await page.locator('[data-testid="nav-bookings"]').click();
  await page.waitForURL("**\/bookings");
  await expect(page.url()).toBe(`${BASE_URL}/bookings`);

  await page.waitForSelector("#booking-card");
  const bookingCards = await page.locator("#booking-card").all();

  for (const card of bookingCards) {
    const cardRef = (await card.locator(".booking-ref").textContent())?.trim();

    if (bookingRef?.trim() === cardRef) {
      await expect(card).toBeVisible();
      await expect(card.locator("h3")).toHaveText(eventTitle);
      break;
    }
  }

  // Step 8 — Verify seat reduction
  await page.getByTestId("nav-events").click();
  await page.getByTestId("event-card").first().waitFor({ state: "visible" });

  eventList = await page.getByTestId("event-card").all();
  for (const event of eventList) {
    const cardTitle = (await event.locator("a h3").textContent())?.trim();

    if (cardTitle === eventTitle) {
      await expect(event).toBeVisible();
    
      await page.waitForTimeout(5000);

      const seatsText = await event.getByText("seats available").textContent();
      const seatsAfterBooking = Number(seatsText?.replace(/\D/g, ""));

  
      await expect(seatsAfterBooking).toBe(seatsBeforeBooking! - 1);   
      break;
    }
  }
});
