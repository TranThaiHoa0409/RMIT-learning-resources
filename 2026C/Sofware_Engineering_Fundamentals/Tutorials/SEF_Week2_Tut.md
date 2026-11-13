# Tutorial 02 – Methodology

## 1. Waterfall vs RUP for a High-Risk Project

A new project proposed with many novel features requires to use of the latest mobile technology and distributed teams across the globe. The project is also highly risky as it involves a large budget and many non-functional requirements (for example, very fast response time for most features). You as the project manager are required to decide between Waterfall and RUP processes. State your decision citing three specific reasons.

**Model Ans.**

a) RUP is iterative within each phase and across the cycle. The project can be terminated after any of the iterations. For example, if during inception the architecture is found unviable; the project can be terminated with limited cost.

b) RUP is also incremental – the not-so-vital features can be left to the second or third stage.

c) RUP supports component-oriented architecture and many tools to structure and manage the development.

## 2. Agile Manifesto and Accelerated Development

Explain how the principles underlying agile methods (agile manifesto) lead to the accelerated development and deployment of software.

**Model Ans.**

Agile builds software iteratively and incrementally: each short cycle (sprint) defines, builds and releases a small working part of the product, instead of delivering everything at once at the end. The four values of the Agile Manifesto each contribute to faster development and deployment:

a) **Individuals and interactions over processes and tools.** Self-organizing, cross-functional teams talk face to face instead of waiting for formal documents and approvals. Decisions are made quickly and problems are solved as soon as they appear, so less time is lost in hand-offs between roles.

b) **Working software over comprehensive documentation.** Effort goes into producing code that runs, not into writing large specifications upfront. A usable increment is delivered at the end of every sprint, so the customer gets value early and frequently rather than only at the final release (as in Waterfall).

c) **Customer collaboration over contract negotiation.** The customer is involved throughout and gives feedback after each release. Misunderstandings are discovered early (avoiding the "tree swing" problem), so the team does not waste time building features nobody wanted, and less rework is needed later.

d) **Responding to change over following a plan.** Planning is incremental, so changing requirements are simply added to the product backlog and handled in the next sprint, instead of forcing a costly re-plan of the whole project. Risk is reduced after each small release rather than accumulating until the end.

Overall, short iterations + continuous feedback + a focus on working code mean the most valuable features are deployed first and quickly, and the product evolves with the customer's needs.

## 3. Avoiding Users "Going Native"

It has been suggested that one of the problems of having a user closely involved with a software development team is that they 'go native'. That is, they adopt the outlook of the development team and lose sight of the needs of their user colleagues. Suggest three ways how you might avoid this problem and discuss the advantages and disadvantages of each approach.

**Model Ans.**

a) Involve multiple users in the development team. The advantages are you get multiple perspectives on the problem, better coverage of user tasks and hence requirements, and less likelihood of having an atypical user. The disadvantages are cost, difficulties in getting user engagement, and possible user conflicts.

b) Change the user who is involved with the team. The advantages are, again, multiple perspectives. The disadvantages are each user takes time to be productive and possibly conflicting requirements from different users.

c) Validate user suggestions with other user representatives. The advantages are an independent check on suggestions; the disadvantage is that this slows down the development process as it takes time to do the checks.

## 4. Pair Programming Productivity

Suggest four reasons why the productivity rate of programmers working as a pair might be more than half that of two programmers working individually.

**Model Ans.**

a) Pair programming leads to continuous informal reviewing. This discovers bugs more quickly than individual testing.

b) Information sharing in pair programming is implicit – it happens during the process. This reduces the need for documentation and the time required if one programmer has to pick up another's work. Individual programmers have to spend time explicitly sharing information and they are not being productive when doing so...

c) Pair programming spreads knowledge across the team. Both programmers understand the code, so there is no single point of failure: if one person is absent or leaves, work continues without a long ramp-up period. It also helps less experienced programmers learn quickly from more experienced ones, raising the productivity of the whole team over time.

d) Pairs produce better designs and simpler code. Two people discuss alternatives before coding, so poor design decisions are caught early. The code is cleaner and easier to maintain, which reduces the time spent on rework and refactoring later.

e) Pairs stay more focused. Working with a partner creates peer pressure to stay on task (fewer distractions such as email or browsing), and when one programmer gets stuck the other can often suggest a solution immediately instead of losing hours searching alone.

## 5. User Stories for Course Prerequisites

RMIT is planning to mandate prerequisites for course enrolment from 2016 (students cannot enroll in a course without meeting all the course prerequisites. For example, undergraduate students cannot enroll in SEF without passing either Programming 1 or Programming Techniques. Post Graduate students and students with advanced standing may be given some exemptions based on prior learning. You have been asked to improve the Academic Management system to manage the enrolment process. Suggest two user stories each for students who want to enroll and lecturers who wish to specify the prerequisites and exemptions.

**Model Ans:**

User stories follow the format: *As a [user role], I want to [goal], so that [reason].*

**Student stories**

a) As an undergraduate student, I want to see the prerequisites of a course and whether I have already met them before I enroll, so that I know if I am eligible and can plan my study ahead.

- *Acceptance criteria:* the course page lists all prerequisites (e.g. "Programming 1 OR Programming Techniques" for SEF); each prerequisite is marked as met / not met based on my academic record; if a prerequisite is not met, the "Enroll" button is disabled with a clear message explaining why.

b) As a postgraduate student or a student with advanced standing, I want to apply for a prerequisite exemption based on my prior learning, so that I can enroll in the course without repeating content I have already studied.

- *Acceptance criteria:* I can submit an exemption request for a specific course and upload supporting documents (transcripts, certificates); I can see the status of my request (pending / approved / rejected); once approved, I can enroll in the course straight away.

**Lecturer stories**

c) As a lecturer (course coordinator), I want to specify the prerequisites of my course, including alternatives (e.g. Programming 1 OR Programming Techniques), so that only students with the required background can enroll.

- *Acceptance criteria:* I can add, edit and remove prerequisite courses; I can combine them with AND / OR rules; changes are saved and applied to the next enrolment period; students see the updated prerequisites on the course page.

d) As a lecturer, I want to review exemption requests and grant or reject exemptions for individual students, so that postgraduate students and students with advanced standing are treated fairly based on their prior learning.

- *Acceptance criteria:* I can see a list of pending exemption requests for my course with the student's supporting documents; I can approve or reject each request and add a comment; the student is notified of the decision automatically.

## 6. User Stories Activities

### Example

| Title | Priority --- Estimate |
|---|---|
| View outstanding balance for credit card payment | High --- 3 |
| **User Story:** | As a [description of user], Credit Card holder<br>I want[functionality] to view my statement balance.<br>So that [benefit] I can promptly pay the balance before the due date.
| **Acceptance Criteria:** | - When logging into my credit card account, there should be a clearly labeled option or section "view statement balance."<br>- Clicking on the "view statement balance" option should display the current statement balance.<br>- The statement balance should include details such as the due date and minimum payment amount.<br>- The statement balance should be easily accessible on both the web and mobile platforms.<br>- The statement balance should be accurate and updated in real time or with minimal delay.<br>- The information should be presented in a clear and user-friendly manner, with no technical issues or errors.


**Note:**

- The priority section assigns low, medium, or high.
- The estimate section gives a number of points.

### Exercise

Epic story: "As a Shop, I want to provide net shopping to customers, so that they can perform various transactions".

From the given epic story, decompose several features using a "User Story". Create as many User Stories as you can.

| Title | Priority --- Estimate |
|---|---|
| **User Story:** | As a [description of user], I want[functionality]. So that [benefit]|
| **Acceptance Criteria:** | |

**Model Answers**

Here are some user stories but there are more possible user stories.

| Title | Priority --- Estimate |
|---|---|
| View Product Inventory for effective stock management | Medium --- 5 |
| **User Story:** | As a shop owner, I want to access a real-time inventory report that lists all of my products, so that I can accurately track my product stock level. |
| **Acceptance Criteria:** | - Should be able to log into the system using shop owner credentials.<br>- Once logged in, there should be an option or selection labeled "view product inventory."<br>- Clicking on "view product inventory" should display a comprehensive list of all my products.<br>- The inventory report should include key details for each product, such as product name, current stock quantity, stock keeping unit (SKU), and any additional relevant information.<br>- The system should provide options for filtering or searching for specific products, and it should allow sorting based on different criteria (ex. Product name, stock quantity, etc.)<br>- The inventory report should be up-to-date and reflect real-time stock levels.<br>- The report should be accessible on both web and mobile platforms for convenience. |

| Title | Priority --- Estimate |
|---|---|
| Search for products to facilitate purchase | High --- 5 |
| **User Story:** | As a customer, I want to use a search feature for products on the website or app so that I can quickly find and purchase the products I'm interested in. |
| **Acceptance Criteria:** | - There should be a clearly visible and easily accessible search bar or button on the application.<br>- The search functionality should accept keywords, product names, or other relevant search terms.<br>- The system should provide a list of relevant products that match the search criteria.<br>- The results should be accurate and should display important information such as product name, price, and a brief description.<br>- The search should be responsive and provide results in a timely manner, without significant delays.<br>- If there are no search results for a particular query, a clear message or indication should be provided, suggesting alternative search terms or actions. |

| Title | Priority --- Estimate |
|---|---|
| Multiple Payment Modes for Customer Convenience | High --- 8 |
| **User Story:** | As a customer, I want to have the option to choose different payment modes within the application so that I can pay using cash or credit card based on my preferred method. |
| **Acceptance Criteria:** | - The application should offer a clear and easily accessible option for selecting the payment mode during the checkout process.<br>- When selecting the payment mode, there should be two distinct options: "Pay with Cash" and "Pay with Credit Card."<br>- If "Pay with Cash," the application should provide instructions for the cash payment process, which may include providing change or confirming the payment with a staff member.<br>- If "Pay with Credit Card," the application should enable secure credit card payment processing, ensuring the protection of the customer's personal and financial information.<br>- The payment process for both cash and credit card options should be reliable and free from technical errors.<br>- Payment receipts and confirmation should be provided for both payment modes. |

| Title | Priority --- Estimate |
|---|---|
| Add a Product to the Shopping Cart for Purchase | High --- 5 |
| **User Story:** | As a customer, I want to be able to add a product to my shopping cart, so that I can review and proceed with the purchase of the selected product. |
| **Acceptance Criteria:** | - The product should be displayed on the website or app, and there should be a clearly identifiable button or options to "Add to Cart."<br>- When the customer clicks "Add to Cart," the selected product should be added to customer's shopping cart.<br>- The shopping cart should display the product name, quantity, price, and a subtotal for the added item.<br>- There should have an option to add multiple products to the cart, and each added product should be listed in the cart separately.<br>- The cart should provide a running total of the order, showing the sum of all the selected products.<br>- It should be easily access and review the contents of the shopping cart before proceeding to checkout.<br>- There should be an option to modify the quantity or remove products from the cart.<br>- The cart should clearly indicate any applicable taxes, fees, or shipping costs. |

| Title | Priority --- Estimate |
|---|---|
| Track Order Status after Purchase | Medium --- 5 |
| **User Story:** | As a customer, I want to track the status of my order after checkout, so that I know when my products will arrive. |
| **Acceptance Criteria:** | - After placing an order, the customer should receive an order confirmation with a unique order number.<br>- There should be a "My Orders" section listing all past and current orders.<br>- Each order should show its current status (e.g. Processing, Shipped, Delivered, Cancelled) and an estimated delivery date.<br>- The customer should be notified (email or app notification) when the order status changes. |

| Title | Priority --- Estimate |
|---|---|
| Manage Product Listings | High --- 5 |
| **User Story:** | As a shop owner, I want to add, edit, and remove products in the online catalogue, so that customers always see accurate product information and prices. |
| **Acceptance Criteria:** | - The shop owner should be able to add a new product with name, description, price, images, category, and stock quantity.<br>- Existing product details should be editable, and changes should appear to customers immediately.<br>- The shop owner should be able to remove or hide a product that is no longer sold.<br>- Required fields should be validated (e.g. price must be a positive number) with clear error messages. |

**Note on Priority/Estimate:** priorities reflect how essential the feature is for customers to complete a purchase (search, cart, payment and product listings are High). Estimates use story points on a 1–10 scale based on relative complexity; payment is rated highest (8) because it requires secure integration with external payment services.
