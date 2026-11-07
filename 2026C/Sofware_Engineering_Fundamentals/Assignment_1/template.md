# Software Requirements Specification (SRS)

**Project Name:**

**Group Number:**

**Members:**

> \<This is the template document. Replace any text in \< \> with your text. There should be no text surrounded by \< \> in this document when you are done. Remember, the purpose of this document is to be useful, not just work to get a grade. A company should be able to use this document to develop software.\>

\<**Table of Contents Here**\>

---

## 1. Introduction

### 1.1 Purpose

> *\<Briefly describe the software system, its key objectives, and its main features.\>*

### 1.2 Scope

> *\<In this subsection:*
>
> 1. *Identify the software product(s) to be produced by name.*
> 2. *Explain what the software product(s) will do and, if necessary, will not do.*
> 3. *Describe the application of the software being specified, including relevant benefits, objectives, and goals.*
>
> *This should be an executive-level summary.\>*

### 1.3 Definitions, Acronyms, Abbreviations

> *\<Provide the definitions of all terms, acronyms, and abbreviations required to properly interpret the SRS. Ensure to arrange it in alphabetical order\>*

---

## 2. Overall Description

*\<This section should briefly describe the system at a high level to help readers understand its context, main purpose, and intended users before reviewing detailed requirements.\>*

### 2.1 Product Perspective

> *\<This subsection should explain how the system fits into its overall environment. Indicate whether the system is standalone or part of a larger system, and briefly mention any external systems, platforms, or dependencies it interacts with.\>*

### 2.2 Product Functions

> *\<This subsection should provide a high-level summary of the system's main functions. Describe what the system does from a general user perspective, focusing on key capabilities rather than detailed processes or technical details.\>*

### 2.3 Use Classes and Characteristics

> *\<This subsection should identify the main user roles of the system. Briefly describe each user's role and expected level of technical skills, focusing on how users interact with the system rather than access permissions.\>*

### 2.4 Constraints

> *\<Provide a general description of any other items that will limit the developer's options. These may include **(Choose only what is appropriate to your project):***
>
> 1. *Regulatory policies (e.g. some policies that users need to follow when using the app)*
> 2. *Hardware limitations (e.g., signal timing requirements)*
> 3. *Interfaces to other applications (e.g. API or XML data sharing)*
> 4. *Parallel operation (e.g. what are the operations that need to be supported for parallel operations without breaking the integrity of the data stored.)*
> 5. *Audit functions (e.g. administrators' ability to choose and filter the user's desired dataset)*
> 6. *Control functions (e.g. administrators' ability to enable/disable some functions/features of the app).*
> 7. *Higher-order language requirements (e.g. Java shall be the implementation language with Android, Facebook, Google SDKs.)*
> 8. *Reliability requirements (e.g. shall operate on Android v4.0 or higher operating system)*
> 9. *Criticality of the application (e.g. How critical the users' data is)*
> 10. *Safety and security considerations (e.g. Information of the users shall be encrypted before transferred to the database in order to maintain the security of the crucial information about users.)*
>
> *\>*

---

## 3. Requirements by Feature

*(Each feature has all three perspectives: user story, use case, and functional requirements.)*

### 3.1 \<Feature Name\>

#### 3.1.1 User Story *(Agile Perspective)*

- **ID:** *US-[Number]*
- **Statement:** *As a [role], I want [goal] so that [reason].*
- **Business Value:** *[Why this is important to the customer/business]*
- **Acceptance Criteria:**
  - *AC-01: [Testable condition]*
  - *AC-02: [Testable condition]*

> *\<**Example:***
>
> ***Feature: Password Reset***
>
> ***User Story***
>
> - ***ID:*** *US-01*
> - ***Statement:*** *As a registered user, I want to reset my password so that I can regain access to my account if I forget it.*
> - ***Business Value:*** *Reduces customer support tickets related to login issues, improves user satisfaction, and maintains account security.*
> - ***Acceptance Criteria:***
>   - ***AC-01:*** *User can request a password reset from the login page.*
>   - ***AC-02:*** *A password reset email is sent to the registered email within 2 minutes of request submission.*
>   - ***AC-03:*** *Reset link expires after 24 hours.*
>   - ***AC-04:*** *User can set a new password that meets security policy (minimum 8 characters, mix of letters and numbers).\>*

#### 3.1.2 Use Case *(Interaction Perspective)*

| | |
|---|---|
| **Use Case ID** | *UC-[Number]* |
| **Title** | *[Short Name]* |
| **Actor** | *[Primary, Secondary]* |
| **Pre-condition** | *[Conditions that must be true before starting]* |
| **Main Flow** | 1. *Step 1*<br>2. *Step 2*<br>3. *…..* |
| **Alternative Flows** | *AF1: [Description]*<br>*AF2: [Description]* |
| **Postconditions** | *[Expected outcome after completion]* |

> *\<Example:*
>
> ***Use Case (Interaction Perspective)***
>
> | | |
> |---|---|
> | **Use Case ID** | *UC-01* |
> | **Title** | *Reset Password* |
> | **Actor** | *Primary: Registered User*<br>*Secondary: Email System* |
> | **Pre-condition** | *User is on the login page.*<br>*User has a registered email in the system.* |
> | **Main Flow** | 1. *User clicks **"Forgot Password"** link on the login page.*<br>2. *System displays a form prompting for the registered email address.*<br>3. *User enters email address and submits the form.*<br>4. *System validates the email format and existence in the database.*<br>5. *System generates a unique, time-limited reset token.*<br>6. *System sends a password reset email containing the reset link to the registered email.*<br>7. *User clicks the reset link.*<br>8. *System displays the password reset form.*<br>9. *User enters a new password and confirms it.*<br>10. *System validates password strength and updates it in the database.*<br>11. *System displays a confirmation message.* |
> | **Alternative Flows** | ***From step 4: AF1:*** *Email address not registered → System displays "Email not found" error.*<br>***From step 6: AF2:*** *Reset token expired or already used → System displays "Link expired. Please request a new password reset."*<br>***From step 10: AF3:*** *New password does not meet policy → System prompts user to re-enter a valid password.* |
> | **Postconditions** | *User can log in with the new password.*<br>*Old password is invalidated.* |
>
> *\>*

#### 3.1.3 Activity Diagram

> \<Illustrate the software workflows to understand how the system responds to events and how users interact with the system, in conjunction with use case diagrams. In this section, only **ONE** activity diagram will be presented for the entire system or application, showing the complete process or steps of how the user interacts with the system or application. Combine all the use cases, courses of events, and alternative courses of events into a single activity diagram.\>

#### 3.1.4 Functional Requirements *(Formal Perspective)*

- *FR-[Number]: [Exact, testable requirement]*
- *FR-[Number]: [Exact, testable requirement]*
- *FR-[Number]: [Exact, testable requirement]*

> *\<Example:*
>
> ***Functional Requirements***
>
> - ***FR-01:*** *The system shall provide a "Forgot Password" link on the login page.*
> - ***FR-02:*** *The system shall allow the user to enter a registered email address for password reset.*
> - ***FR-03:*** *The system shall validate whether the entered email exists in the database.*
> - ***FR-04:*** *The system shall send a password reset email containing a unique, secure token within 2 minutes of request submission.*
> - ***FR-05:*** *The reset token shall expire 24 hours after issuance or upon first use.*
> - ***FR-06:*** *The system shall enforce a minimum password length of 8 characters and require a mix of letters and numbers.*
> - ***FR-07:*** *The system shall display appropriate error messages for invalid email, expired token, or weak password.*
> - ***FR-08:*** *The system shall log password reset requests for audit purposes.\>*

---

## 4. Non-Functional Requirements

> (Performance, security, usability, availability, etc.)

- *NFR-01: [Requirement]*
- *NFR-02: [Requirement]*

---

## 5. Traceability Matrix

**\<***Provide a structured way to map user stories, use cases, and functional requirements. This will help you identify the test cases on the next assessment. Provide a short justification on how the matrix was structured.*\>

\<Example

| User Story ID | Use Case ID | Functional Requirement IDs | Acceptance Criteria ID |
|---------------|-------------|----------------------------|------------------------|
| US-01         | UC-01       | FR-01, FR-02, FR-03        | AC-01, AC-02           |
| US-02         | UC-02       | FR-04, FR-05               | AC-03                  |

*\>*

---

## 6. References

> *\<In this subsection:*
>
> *(1) Provide a complete list of all documents referenced elsewhere in the SRS.*
>
> *(2) Identify each document by title, report number (if applicable), date, and publishing organization.*
>
> *(3) Specify the sources from which the references can be obtained.*
>
> *(4) Use the IEEE referencing style.*
>
> *If your application uses specific protocols, for example, then reference them here so designers know where to find them.\>*

\<GOOD LUCK!!!\>
