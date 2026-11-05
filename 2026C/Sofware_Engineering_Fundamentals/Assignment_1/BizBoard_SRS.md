# BizBoard - SRS

Google Docs or shared Microsoft Word from the same team is consider using for the SRS document. Instead of using Messanger as a communication tool, team can use Microsoft Teams for better collaboration and communication. The team can also use Google Drive or Microsoft OneDrive to share files and documents.

## Project purpose

[ ] is a web-based business analytics application, accessible on both computers and mobile devices, that helps authorized users monitor business performance in one place. It brings together the company's internal data, such as sales, revenue, customer numbers, and app usage, and presents it through dashboards, KPIs, trend analysis, reports, and real-time alerts. Users can also share dashboards and collaborate within groups, so teams can make faster, better-informed decisions wherever they are.

## Final name options

Three name options for the group to vote on, each with a tagline.

| Option | Name | Why it works | Tagline |
| --- | --- | --- | --- |
| 1 | BizBoard | Short and easy to say; suggests a dashboard (board) for your business, and works well as a phone app name. | Know your numbers, anywhere. |
| 2 | InsightHub | Clear and professional; suggests everything lives in one place. | Every insight, one place. |
| 3 | DataLens | Short; hints at zooming into details, like drill-down. | See the detail behind every number. |

BizBoard taglines:

- Your business, at a glance.
- Stay on top of performance, wherever you are.

## Main capabilities (totally agree)

| Capability | Plain-English explanation |
| --- | --- |
| Dashboards and KPIs | Users choose which important business measurements (KPIs) appear on their dashboard and view them as charts or summary values. |
| Analytics | Users examine performance over time, compare periods or categories, and open more detailed data (drill-down). |
| Reporting | Users create reports for a selected period or topic, schedule recurring reports, and share them with permitted people. |
| Notifications | The system alerts relevant users when a configured unusual condition or important performance update occurs. |
| Collaboration and access | Users share dashboards with groups, add comments or annotations, and access only information they are permitted to see. |

## Business intelligence (BI)

- The system needs a BI component that uses the company's own internal data, such as sales, revenue, customer numbers, and app usage.
- It needs a tool to clean up that data so it is accurate and consistent.
- It shows the cleaned data to the user, so they can see how the business is performing.
    - Memory tip: BI is the kitchen, and KPIs are the dishes it serves up.

## Market intelligence (optional)

- Market intelligence is optional and not compulsory for this assignment; the group may include it only if time allows.
- It would use information from outside the company, such as competitors' pricing, industry and market trends, and customer behavior across the market.
- If included, it would be shown next to the internal BI data so users can compare their own performance with the market.

## Decisions

| Decision | Questions to settle | Hòa's answer |
| --- | --- | --- |
| Concept and name | Do we accept BizInsight or choose another name? What is the one-sentence purpose? | Name: BizBoard.<br>Purpose: a web-based business analytics application, accessible on computers and mobile devices, that helps users monitor business performance through dashboards, reports, and alerts. |
| Platform | Is it a responsive web application accessible on mobile, a dedicated mobile app, or both? | A responsive web application, accessible on both mobile and desktop.<br>Note: it will be developed mobile first and expanded to desktop later. |
| Data sources | Does data come from connected APIs, uploaded files, sample datasets, or a limited combination? | A limited combination of sample datasets and uploaded files.<br>Note: numbers do not update by themselves. They refresh on a set schedule or when a new file is uploaded.<br>Live connections through APIs are left for a later version. |
| Meaning of real-time | What refresh/notification delay do we mean? Is it continuous or periodic? | Periodic, not continuous.<br>The dashboard data refreshes every 5 minutes, or right away when a new file is uploaded.<br>After a refresh, the notification reaches the user within 1 minute if there is an alert condition. |
| Anomaly detection | Are alerts based on user-defined thresholds or automatic detection? | User-defined thresholds. The user sets a rule for a KPI, and an alert is created when the value crosses it.<br>Possible improvement: automatic detection using AI, which learns what is normal from past data and flags unusual values. |
| User roles | Which roles are genuinely needed? What can each role view, comment on, edit or administer? | |
| Sharing model | Can users share dashboards, reports, or both? With individuals, groups, or both? | Reports only. Users can share reports with individuals and with groups inside the app.<br>Note: dashboards are not shared in the app. Users can share them outside the app, for example by screenshot. |
| Scope boundaries | What is explicitly excluded from this first version? | Excluded from the first version:<br>- Live data connections through APIs.<br>- Automatic anomaly detection using AI.<br>- Sharing dashboards inside the app.<br>- Market intelligence (optional).<br>- A dedicated mobile app. |
| ID and document control | Who assigns IDs? Where is the master list? Who integrates the final report? | IDs (US-01, UC-01, FR-01) are reserved in one shared Google Sheets master list.<br>One team member owns the list and assigns the IDs. Members reserve their IDs there before writing.<br>One team member puts the final report together. |

## User roles

| Role | What they do |
| --- | --- |
| Business Manager | Monitors KPIs, compares performance, and makes business decisions. |
| Data Analyst | Explores data, filters results, analyzes trends, and generates reports. |
| Team Member | Views shared dashboards, comments, and collaborates. |
| Group Owner | A team member who owns a group. Adds members and gives them permissions for that group only. |
| System Administrator | Manages all user groups and permissions across the whole system. |

## Possible APIs for future versions

- **FakeStore API:** fake products, carts, and users, useful for sales and order data.
- **DummyJSON:** fake products, carts, and users that can stand in for customers.
- **Alpha Vantage:** free stock and currency data (needs an API key).
- **World Bank API:** free economic and industry indicators, useful for optional market intelligence.
- **CoinGecko:** free cryptocurrency prices.

These are from memory, so check each one's current terms and limits before using it.

## Member by menber Instrcution

### Member 1 – Dashboard & KPI Management

1. **A widget catalogue**: This is a list or picker screen showing the available widgets, so the user can see what exists. 
    - User can add or remove widgets from their dashboard by selecting them in the catalogue. The catalogue can be a separate screen or a pop-up modal, and it can show a preview of each widget.
2. **Saved selection**: The app remembers what the user chose, so their dashboard looks the same next time they open it. 
    - This usually means storing the selected widget IDs in a database or local storage.
3. **Widget layout (Future scope)**: The user can arrange the widgets on their dashboard, for example by dragging and dropping them into different positions. 
    - The app saves the layout so it is restored next time.
4. **Time period.** The system displays the selected KPIs for an agreed time period. The supported periods are fixed and listed below, so the user picks one instead of typing dates.
- The chosen period applies to all KPIs on the dashboard, and the dashboard reloads when the user changes it.
    - Today
    - Last 7 days
    - Last 30 days
    - This month
    - Custom range (optional, only if time allows)

### Member 2 – Business Analytics
