**GROUP WORK GUIDE**

Assignment 1 – SRS & UML

**Proposed example concept: BizInsight**

*A practical guide for a four-person team*

Course: ISYS3416 / ISYS3432 – Software Engineering Fundamentals for IT  
Assessment: Assignment 1 – System Requirements Specification  
Due date shown in brief: 28 November 2026, 22:00

# 1. Read this first: what are we producing?

This assignment is a requirements-and-design report. The group is not being asked to build, code, or launch a working application for this initial submission. We are describing a proposed system clearly enough that a reader can understand what it is intended to do, who will use it, and how its main functions should behave.

> **Important – concept is NOT approved yet** BizInsight is only an example concept used in this guide to make the work allocation concrete. The assignment topic is Business Analytics Application. The group must discuss and agree on the system name, scope, actors, features, assumptions, and design decisions before treating any example below as final.

## What is an SRS?

A Software Requirements Specification (SRS) is a structured document that records what a proposed software system must do (functional requirements) and the quality or constraints it must satisfy (non-functional requirements). It is not a coding tutorial or a screen-design-only document.

## The example concept: BizInsight

If the group accepts it, BizInsight would be a mobile-enabled business analytics platform. It would bring together business/mobile-app data and market information so authorized users can monitor performance through dashboards, charts, reports, alerts, and team collaboration.

| **Example capability** | **Plain-English explanation** |
| --- | --- |
| Dashboards and KPIs | Users choose which important business measurements (KPIs) appear on their dashboard and view them as charts or summary values. |
| Analytics | Users examine performance over time, compare periods or categories, and open more detailed data (drill-down). |
| Reporting | Users create reports for a selected period or topic, schedule recurring reports, and share them with permitted people. |
| Notifications | The system alerts relevant users when a configured unusual condition or important performance update occurs. |
| Collaboration and access | Users share dashboards with groups, add comments or annotations, and access only information they are permitted to see. |

The assignment brief identifies these kinds of capabilities for the Business Analytics Application topic. The exact details above are proposed examples and must be confirmed by the group.

## What the report should contain

- Introduction: purpose, scope, definitions and acronyms.

- Overall description: product perspective, main product functions, user classes/characteristics, and constraints.

- Requirements by feature: for each feature, include user stories, use cases, and functional requirements.

- A high-level UML system overview, including the required use case diagram and the overall activity workflow as directed by the template/brief.

- Non-functional requirements (NFRs).

- Traceability matrix linking stories, use cases, requirements and acceptance criteria/test evidence.

- References, plus the project timeframe and chosen software engineering methodology requested in the assignment brief.

> **Page limit** The brief states a maximum of 20 pages. Keep the report concise and professional, and check whether your lecturer counts the cover page, contents page, diagrams, and references within that limit.

# 2. How the rubric should guide our effort

The rubric gives the largest marks to user stories, use cases, and functional requirements. Do not spend most of the group's time decorating the document while these sections remain vague or incomplete.

| **Rubric area** | **Marks** | **What an excellent result needs** |
| --- | --- | --- |
| Introduction and overall description | 5 | Clear purpose and scope; coherent product perspective, functions, user types and constraints. |
| User stories | 10 | Unique IDs; role + goal + reason; business value; specific, testable acceptance criteria. |
| Use cases | 10 | ID, clear title, actors, preconditions, detailed main flow, relevant alternative flows and postconditions. |
| Functional requirements | 10 | Precise, formal, unique and testable statements describing system behaviour. |
| Non-functional requirements | 4 | Relevant quality attributes with measurable, realistic and testable targets. |
| Traceability and consistency | 3 | Complete links among user stories, use cases, functional requirements and acceptance criteria/test cases. |
| Activity diagram | 8 | Correct UML notation; covers key workflows; aligns with use cases; includes clear decisions and explanation. |

> **High-mark principle** Every artefact must agree with the others. A feature in a user story should appear in its use case, be supported by one or more functional requirements, and be traceable in the matrix and diagrams.

# 3. Proposed four-member work allocation

This is a starting allocation, not a final decision. It divides the application into four related work packages. Each member owns the requirements for their package, while the shared tasks are completed collaboratively.

| **Member** | **Owns this example work package** | **Example user goals / use cases** |
| --- | --- | --- |
| 1 – Dashboard & KPI | Dashboard setup, widget management, KPI display and visualisation. | Customize dashboard; add/remove/rearrange widgets; view selected KPIs. |
| 2 – Analytics | Trend analysis, comparisons, filtering and drill-down. | View trends; compare time periods; filter results; inspect detailed data. |
| 3 – Reports & notifications | Report creation, scheduling/sharing, and performance/anomaly alerts. | Generate report; schedule report; share report; configure/receive alert. |
| 4 – Collaboration & access | Dashboard sharing, comments, annotations, groups, permissions and access rules. | Share dashboard with a group; comment; annotate; manage group permissions. |

Balance note: Member 3 and Member 4 each cover more than one connected workflow. If the group finds either package too large, move one small workflow (for example, report sharing or group management) to another member. Balance actual effort, not just the number of feature labels.

## Shared responsibilities (everyone contributes)

- Agree on the concept, scope, actors, terminology and assumptions before writing detailed requirements.

- Review the whole report for consistency, not only your own section.

- Contribute suitable NFR ideas and check that each has a measurable target.

- Provide your story/use-case/requirement IDs and links for the traceability matrix.

- Give the diagram owner the main and alternative flows from your feature.

- Check the final document for accuracy, grammar, formatting, references and page limit.

# 4. Member-by-member instructions

## Member 1 – Dashboard & KPI Management

Your role in the example: define how an authorized business user sets up a dashboard and views selected key performance indicators. A KPI is a measurable value used to monitor performance, such as monthly revenue, active users, conversion rate, or customer retention. The group must decide which example KPIs make sense for its chosen system.

### What you should produce

- Write approximately 2–3 complete user stories for dashboard/KPI goals.

- For each story, write its business value and specific acceptance criteria (normally 3–5 useful criteria per story).

- Write a corresponding use case for each distinct user goal, including all template fields.

- Write the functional requirements needed to satisfy those goals.

- Explain the dashboard/KPI functions briefly for the Overall Description; coordinate with the group so the description covers the whole product, not only your feature.

- Give the diagram owner your main flow, decisions and alternative flows.

### Possible example scope (confirm with group)

- A user selects from available dashboard widgets.

- A user adds, removes or rearranges widgets.

- The system saves the user's dashboard configuration.

- The system displays selected KPIs for an agreed time period.

### Tips for a strong submission

- Define what a widget is and what information it displays; avoid using the word without explanation.

- Specify whether dashboard settings are personal or shared. Coordinate this with Member 4.

- State how a KPI is selected and what happens if data is unavailable.

- Avoid vague acceptance criteria such as 'dashboard is user-friendly'. State an observable result.

> **Example acceptance criterion** AC-01: When an authorized user selects an available KPI widget and saves the dashboard, the widget appears on that user's dashboard after the page is refreshed. This is an example only; agree on the actual behaviour.

## Member 2 – Business Analytics

Your role in the example: define how users examine business performance beyond the dashboard summary. Analytics includes looking at patterns over time, comparing results, filtering information, and drilling down from a summary into more detailed data.

### What you should produce

- Write approximately 2–3 complete user stories for trend analysis, comparison, filtering or drill-down.

- For each story, include business value and testable acceptance criteria.

- Write complete use cases with actors, preconditions, main flow, alternative flows and postconditions.

- Write unique, testable functional requirements for the analytics behaviour.

- Clarify what data dimensions users can filter (for example, date, product, region or channel) only after the group agrees on the data scope.

- Provide the activity-diagram coordinator with the normal and exception paths.

### Possible example scope (confirm with group)

- View a KPI trend over an agreed date range.

- Compare one time period with another.

- Filter results by an agreed business category.

- Select a chart value to view more detailed information.

### Tips for a strong submission

- Define the meaning of 'trend' and 'drill-down' in simple terms.

- Do not promise predictive AI or advanced forecasting unless the group deliberately includes it and can specify it.

- Specify what happens when no data exists for the selected period.

- Make sure analytics uses the same KPI names and data assumptions as Member 1.

> **Example functional requirement** FR-XX: The system shall allow an authorized analyst to select a date range and display the corresponding KPI values for that range. Replace XX with the team's assigned unique number and confirm the requirement is in scope.

## Member 3 – Reporting & Notifications

Your role in the example: define how users turn analytics into reports and how the system informs users about important changes. Reporting and notifications are separate user goals, even though they are grouped here to balance the four-person team.

### What you should produce

- Write approximately 2–3 complete user stories covering report generation/scheduling and notifications. If the team includes all these functions, ensure each important goal is represented.

- Write acceptance criteria that state the report output, selected period, schedule behaviour, recipient rules or alert trigger clearly.

- Write complete use cases for the main reporting and notification workflows.

- Write functional requirements for generating, scheduling, sharing (if assigned here), and sending alerts.

- Coordinate with Member 4 on report sharing versus dashboard sharing and with Member 2 on what analytics data can appear in reports.

- Contribute measurable NFR proposals relevant to response time, reliability or notification delivery, then review them with everyone.

### Possible example scope (confirm with group)

- Generate a report using selected metrics and a date range.

- Schedule a report to be generated at an agreed frequency.

- Send a report to permitted recipients.

- Notify a user when an agreed threshold or anomaly condition is met.

### Tips for a strong submission

- Define 'real-time' or choose a realistic refresh/delivery interval. Do not leave the term undefined.

- Define the alert condition: user-configured threshold, system-detected anomaly, or another agreed rule.

- Specify who receives an alert and through which channel only if the group has decided this.

- Include alternative flows: no data, invalid date range, report-generation failure, schedule conflict, or notification-delivery failure where relevant.

> **Example acceptance criterion** AC-XX: If a KPI crosses the threshold configured for that KPI, the system creates an alert for the users assigned to receive that alert. The group must define the threshold rule and recipient setup before using this.

## Member 4 – Collaboration & Access Management

Your role in the example: define how users work together around dashboards and how the system controls who can view or change shared information. Authentication means checking who a user is; authorization means checking what that user is allowed to do. The assignment brief mentions authentication and authorization, but the team should avoid making login/logout the main use cases because the brief discourages using them as identified use cases.

### What you should produce

- Write approximately 2–3 complete user stories for collaboration and access-control goals.

- Include business value and testable acceptance criteria for each story.

- Write full use cases for sharing a dashboard, commenting/annotating, and/or managing group permissions as agreed.

- Write functional requirements describing who can share, view, comment, annotate, or manage permissions.

- Agree with Member 1 on whether a dashboard is personal, shared, or both; agree with Member 3 on report-sharing boundaries.

- Explain the user roles and access assumptions for the group-wide Overall Description.

### Possible example scope (confirm with group)

- Share a dashboard with a defined user group.

- Add a comment to a shared dashboard or chart.

- Add an annotation to explain a result.

- Assign or change a group's access level, if administration is in scope.

- Prevent a user from viewing or changing content without permission.

### Tips for a strong submission

- Define permission levels in plain language, such as view, comment and edit, only if the group agrees to support them.

- Specify what happens when a user without permission opens a shared link.

- Do not confuse authentication with authorization.

- Avoid vague statements such as 'the system is secure'; write observable access-control behaviour.

> **Example functional requirement** FR-XX: The system shall prevent a user without view permission from opening a shared dashboard. Confirm the access model and expected denial message with the group.

# 5. How to write each required artefact

## A. User stories – 10 marks

A user story explains a requirement from a user's point of view. Use the template structure:

> **Format** As a \[user role\], I want \[goal\] so that \[reason\].

Also include:

- A unique ID, such as US-01.

- Business value: explain why the goal matters to the user or organisation.

- Acceptance criteria (ACs): conditions that can be checked objectively. Use IDs such as AC-01.

Weak: 'As a manager, I want a good dashboard.' This is vague.

Stronger example: 'As a Business Manager, I want to select KPI widgets for my dashboard so that I can focus on the measures relevant to my team.'

Possible acceptance criteria: the user can select from the agreed widget list; selected widgets appear on the dashboard; the user can remove a widget; the saved arrangement remains after reopening the dashboard. Add measurable details when the group has agreed on them.

## B. Use cases – 10 marks

A use case describes the interaction between an actor and the system to achieve a goal. Complete every field required by the template:

| **Field** | **What to write** |
| --- | --- |
| Use Case ID / Title | Unique ID (UC-01) and a short verb-based title, e.g. Generate Business Report. |
| Actor | Primary actor who starts the goal; secondary actors or external systems that participate. |
| Preconditions | What must already be true before the use case starts. |
| Main flow | Numbered, chronological steps showing actor action and system response. |
| Alternative flows | What happens when a decision, error or exception changes the normal path; identify the step where it branches. |
| Postconditions | What is true after successful completion; mention relevant outcomes after failure if useful. |

Write both sides of the interaction. Do not write only 'user clicks button' steps: include what the system displays, validates, retrieves, saves or returns.

## C. Functional requirements – 10 marks

Functional requirements describe what the system shall do. Use a unique ID and formal wording:

> **Preferred wording** FR-01: The system shall \[one clear behaviour\] \[under a stated condition, if needed\].

- One requirement should express one main behaviour.

- Make it specific enough to test.

- Use the same terms and roles used in the stories and use cases.

- Avoid vague words such as easy, fast, appropriate, user-friendly or efficient unless you define how to measure them.

- Avoid design choices that the group has not agreed to.

## D. Non-functional requirements – 4 marks

NFRs describe how well the system must operate or constraints on its operation. They are system-wide, so everyone should propose and review them. Choose relevant quality areas such as performance, security, usability, availability, compatibility and reliability.

| **Weak NFR** | **More testable example (proposed target only)** |
| --- | --- |
| The dashboard must be fast. | Under the agreed normal load, 95% of standard dashboard views shall load within 3 seconds. |
| The system must be secure. | The system shall deny access to a dashboard when the requesting user's account lacks view permission. |
| The system should be available. | The service shall achieve 99.5% monthly availability, excluding scheduled maintenance, if the team accepts this target. |
| The app should work on phones. | The mobile interface shall support the agreed list of mobile operating systems/browsers and screen sizes. |

These numbers and examples are not specified by the assignment. Treat them as discussion starters, check whether they are realistic, and state the measurement conditions (load, network, device, time period) so they can be tested.

## E. UML diagrams – 8 marks for activity diagram in the rubric

Use case diagram: show the system boundary, actors outside it, use cases inside it, and associations between actors and the goals they participate in. Keep names consistent with the written use cases. Do not make login/logout the main use cases.

Activity diagram: the rubric rewards a complete workflow that aligns with the use cases and uses correct UML notation. The template/brief should be followed for the number and scope of diagrams; the recommended plan is one overall activity diagram that combines the major workflows rather than unrelated diagrams for each member.

- Use a clear initial node and final node.

- Use action nodes for activities and decision/merge nodes for branches.

- Use swimlanes to show responsibility (for example, User, BizInsight System, External Data/Notification Service) if useful.

- Show meaningful alternative paths, such as insufficient permission, missing data, report failure or an alert condition not being met.

- Do not add a workflow that is absent from the agreed requirements.

One member may assemble the diagram, but every member must supply their workflow and review the final diagram against their use cases.

## F. Traceability matrix – 3 marks

The matrix proves that requirements connect to user needs. Maintain it while writing, not at the last minute. The rubric expects complete, consistent links in both directions.

| **User story** | **Use case** | **Functional requirements** | **Acceptance criteria / test** |
| --- | --- | --- | --- |
| US-01 | UC-01 | FR-01, FR-02 | AC-01, AC-02 |
| US-02 | UC-02 | FR-03 | AC-03 |

The rows above are only an illustrative format. Use the group's real IDs. Check that every story has supporting use case(s) and FR(s), every FR traces back to a user need, and every acceptance criterion can be linked to the behaviour it verifies.

## G. Introduction, overall description, methodology and schedule

- Purpose: state why the SRS is being written and who will use it.

- Scope: state what the proposed system includes and excludes. Avoid promising a full enterprise BI replacement unless intended.

- Definitions: explain terms such as KPI, BI, dashboard, drill-down, anomaly, API and SRS.

- Product perspective: explain how the proposed system relates to data sources and users at a high level.

- Product functions: summarise the main capabilities without repeating every detailed requirement.

- User classes and characteristics: describe each role, its goals and expected technical familiarity.

- Constraints: identify relevant limitations such as data-source/API availability, internet connection, data quality, privacy/security and supported devices.

- Methodology and timeframe: briefly identify a suitable approach (for example, a hybrid approach with iterative feature work and formal review) and show realistic phases, owners and dates. The group must select its methodology.

# 6. Decisions the group must make before detailed writing

The examples below are not automatic requirements. Record the group's decision and the reason for it in the meeting notes.

| **Decision** | **Questions to settle** | **Why it matters** |
| --- | --- | --- |
| Concept and name | Do we accept BizInsight or choose another name? What is the one-sentence purpose? | Prevents four different interpretations of the product. |
| Platform | Is it a responsive web application accessible on mobile, a dedicated mobile app, or both? | Affects scope, user classes, constraints and NFRs. |
| Data sources | Does data come from connected APIs, uploaded files, sample datasets, or a limited combination? | Affects actors, integrations, data availability and alternative flows. |
| Meaning of real-time | What refresh/notification delay do we mean? Is it continuous or periodic? | Prevents an undefined promise. |
| Anomaly detection | Are alerts based on user-defined thresholds or automatic detection? | Changes use cases and functional requirements. |
| User roles | Which roles are genuinely needed? What can each role view, comment on, edit or administer? | Keeps actors and permissions consistent. |
| Sharing model | Can users share dashboards, reports, or both? With individuals, groups, or both? | Prevents overlap between Member 3 and Member 4. |
| Scope boundaries | What is explicitly excluded from this first version? | Stops scope creep. |
| ID and document control | Who assigns IDs? Where is the master list? Who integrates the final report? | Prevents duplicate IDs and inconsistent references. |

## Suggested initial roles (only if agreed)

| **Example role** | **Typical goals** |
| --- | --- |
| Business Manager | Monitor KPIs, compare performance and make business decisions. |
| Data Analyst | Explore data, filter results, analyse trends and generate reports. |
| Team Member | View shared dashboards, comment and collaborate. |
| System Administrator | Manage user groups and permissions. |

Do not assume every role must be an actor in every use case. An actor should be included when it interacts with the system to achieve or support a goal.

# 7. Team workflow and quality-control plan

## Recommended sequence

1.  Hold a short planning meeting. Agree on the concept, system boundary, roles, feature ownership, data assumptions and terminology.

2.  Create a shared requirements register before drafting. Reserve unique IDs for stories, use cases, FRs, NFRs and acceptance criteria.

3.  Each member drafts their feature package: stories first, then use cases, then functional requirements. Check that the three perspectives describe the same behaviour.

4.  Share draft workflows with the diagram coordinator. Build the use case diagram from the agreed use cases and the activity diagram from the key flows.

5.  Everyone proposes NFRs. Agree on measurable targets and conditions; remove unrealistic or unsupported claims.

6.  Compile the traceability matrix using the final IDs. Check both forward and backward links.

7.  Integrate the SRS using the provided template. Add methodology/timeframe, references and any required diagram description.

8.  Conduct a cross-review: each member reviews another member's section and checks the full report for contradictions.

9.  Proofread, check the 20-page maximum, confirm file naming and submit the report plus the original UML diagram or a clear image as a separate file, as instructed.

## Suggested cross-review pairs

| **Writer** | **Reviewer** |
| --- | --- |
| Member 1 | Member 3 |
| Member 2 | Member 4 |
| Member 3 | Member 1 |
| Member 4 | Member 2 |

## Master register – minimum columns

- ID \| Title/statement \| Owner \| Actor/role \| Related story/use case/FR \| Acceptance criteria \| Status \| Reviewer

- Use one shared source of truth (a shared spreadsheet or table). Never let members independently invent IDs.

- When a requirement changes, update its linked story, use case, diagram and traceability row.

# 8. Common mistakes that can cost marks

| **Avoid this** | **Do this instead** |
| --- | --- |
| Writing only a feature name or a vague story. | Include role, goal, reason, business value and testable acceptance criteria. |
| Writing use cases as a list of button clicks. | Describe actor actions and system responses in a logical sequence. |
| Leaving alternative flows blank. | Consider realistic exceptions and identify the step where each branch starts. |
| Writing FRs such as 'the system is user-friendly'. | Use one precise 'The system shall...' behaviour per requirement. |
| Using NFRs like 'fast', 'secure' or 'reliable' without measures. | Define a metric, target, operating condition and verification method where possible. |
| Adding features that were never agreed. | Keep every artefact inside the approved scope. |
| Using different names for the same role or feature. | Maintain a shared glossary and consistent labels. |
| Creating diagrams that do not match the written requirements. | Build diagrams from the final use cases and review them against the register. |
| Filling the report with unnecessary technical design or code. | Focus on what the system must do and the quality constraints it must meet. |
| Leaving the traceability matrix until the final day. | Update it as IDs and requirements are created. |

# 9. Final group checklist

- [ ] The group has explicitly approved the concept, name, scope, actors and assumptions.

- [ ] The report follows the supplied SRS template and uses consistent terminology.

- [ ] Introduction and overall description cover purpose, scope, perspective, functions, user classes and constraints.

- [ ] Every user story has a unique ID, role-goal-reason statement, business value and testable acceptance criteria.

- [ ] Every use case has an ID, title, actors, preconditions, main flow, alternative flows and postconditions.

- [ ] Functional requirements are unique, precise, testable and use consistent 'The system shall...' wording.

- [ ] NFRs cover relevant quality attributes and use realistic measurable targets.

- [ ] Use case diagram actors and use cases match the written requirements.

- [ ] Activity diagram uses correct UML notation and covers the key workflows and decision paths.

- [ ] Traceability matrix links stories, use cases, FRs and acceptance criteria/test cases without gaps.

- [ ] Methodology and project timeframe/estimation are included.

- [ ] References are complete and consistently formatted in IEEE style if required by the course/template.

- [ ] The report is professional, proofread and within the 20-page maximum.

- [ ] The Canvas filename follows the assignment naming convention: ISYS3416_A1\_\[GroupNumber\] for SGS, as stated in the brief.

- [ ] The original UML diagram or a clear image file is attached separately in Canvas; additional files are not bundled into a ZIP.

# 10. Source basis and important note

This guide is based on the uploaded Assignment 1 brief for ISYS3416/3432 – Software Engineering Fundamentals for IT and the uploaded A1 SRS Template v1.1. The assignment brief states that the initial submission is an SRS and high-level UML-based system overview, sets a 20-page maximum, describes the Business Analytics Application capabilities, gives the rubric, asks teams to outline a timeframe and methodology, discourages login/logout as identified use cases, and specifies Canvas submission instructions.

The BizInsight name, sample actors, feature allocation, example acceptance criteria, example FRs, proposed NFR metrics, and suggested workflow are planning suggestions created to help the group interpret the topic. They are not stated as mandatory facts in the assignment. Confirm them with the team and follow any additional lecturer instructions.

Academic integrity: use this guide as a planning aid. Each member should understand, discuss and write their own contribution in their own words, check course materials, cite any external sources actually used, and avoid copying example wording without adapting and verifying it.
