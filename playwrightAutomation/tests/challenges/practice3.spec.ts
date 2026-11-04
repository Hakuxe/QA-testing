import { expect, test } from "@playwright/test";

const userData = {
  firstName: "John",
  lastName: "doe",
  email: "john.doe12@example.com",
  password: "Password@123!",
};

function getNextYearDate() {
  const eventDate = new Date();
  eventDate.setFullYear(eventDate.getFullYear() + 1);
  return eventDate.toISOString().substring(0, 16);
}

// https://eventhub.rahulshettyacademy.com/login

// before test create an account

// Step 1 — Login
// log in with the created account
// - Assert: link with text Browse Events → is visible (confirms login success)

test("should first", async ({ page }) => {
  const eventTitle = `Test Event${Date.now()}`;
  let seatsBeforeBooking;
  getNextYearDate();
  await page.goto("https://eventhub.rahulshettyacademy.com/login");

  // Step 1 — Login
  await page.getByLabel("Email").fill(userData.email);
  await page.getByLabel("Password").fill(userData.password);
  await page.getByRole("button", { name: "Sign In" }).click();
  await expect(page.getByRole("link", { name: "Browse Events", exact: true })).toBeVisible();

  // Step 2 — Create a new event
  await page.getByRole("button", { name: "Admin" }).click();
  await page.locator("nav a[href='/admin/events']").click();

  // create event
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
  const eventList = await page.getByTestId("event-card").all();
  
  eventList.forEach(async (event) => {
    const cardTitle = await event.locator("a h3").textContent();
    if(eventTitle ===  cardTitle){
      await expect(event).toBeVisible();
      seatsBeforeBooking = await event.getByText('seats available').textContent();
      seatsBeforeBooking = seatsBeforeBooking?.replace(/\D/g,""); 

      await event.getByTestId("book-now-btn").click();
    }

  });

  await page.pause();

  // get all event cards
  // assert the first card is visible
  // with all cards filter the one with your event title
  // assert the matched card is visible
  // read the seat count text from that card
});

// Step 2 — Create a new event
// - Navigate to /admin/events
// - Generate a unique event title using Test Event ${Date.now()} — store this in a variable
// - Fill Event Date & Time field with a future date
// - Fill Total Seats field— use 50
// - Click the submit button

// - Assert: toast message Event created! is visible

// Step 3 — Find the event card and capture seats
// - Navigate to /events
// - Get all event cards
// - Assert the first card is visible
// - From all cards, filter for the one that contains your event title text
// - Assert the matched card is visible

// - Read the seat count text from that card — store this as seatsBeforeBooking

// Step 4 and 5 — Fill booking form
// - On the matched event card, click the Book Now button
// - Assert: element with id #ticket-count has text 1 (default quantity)
// - Fill form and click confirm

// Step 6 — Verify booking confirmation
// - Locate the booking reference element
// - Assert it is visible
// - Read its inner text, trim it — store as bookingRef

// Step 7 — Verify in My Bookings
// - Click the link View My Bookings
// - Assert: URL is BASE_URL/bookings
// - Get all booking cards
// - Assert the first booking card is visible
// - Filter booking cards for the one that contains an element with class .booking-ref matching your bookingRef text

// - Assert that matched card is visible
// - Assert that matched card contains your eventTitle text

// Step 8 — Verify seat reduction
// - Navigate back to /events
// - Assert the first event card is visible
// - Filter cards again using eventTitle
// - Assert the card is visible
// - Read the seat count text again store as seatsAfterBooking

// - Assert: seatsAfterBooking === seatsBeforeBooking - 1
