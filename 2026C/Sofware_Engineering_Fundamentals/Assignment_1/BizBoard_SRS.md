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
| Business Manager | Monitors KPIs, compares performance, makes business decisions, and shares reports. |
| Data Analyst | Explores data, filters results, analyzes trends, generates reports, and shares them. |
| Team Member | Opens reports shared with them, writes notes on reports shared through their group, and collaborates. |
| Group Owner | Any user who creates a group. Adds and removes members, renames or deletes the group, for that group only. |
| System Administrator | Manages all groups and permissions across the whole system, and can delete any note. |

## Possible APIs for future versions

- **FakeStore API:** fake products, carts, and users, useful for sales and order data.
- **DummyJSON:** fake products, carts, and users that can stand in for customers.
- **Alpha Vantage:** free stock and currency data (needs an API key).
- **World Bank API:** free economic and industry indicators, useful for optional market intelligence.
- **CoinGecko:** free cryptocurrency prices.

These are from memory, so check each one's current terms and limits before using it.

## Member by menber Instrcution Scope

### Member 1 – Dashboard & KPI Management - Hòa

1. **A widget catalogue**: This is a list or picker screen showing the available widgets, so the user can see what exists. A widget is one block on the dashboard that shows one KPI, either as a summary number or as a chart.
    - The user can add or remove widgets from their dashboard by selecting them in the catalogue. The catalogue can be a separate screen or a pop-up modal.
    - (Optional) Each widget in the catalogue shows its name, the KPI it displays, and a small preview, so the user knows what they will get before adding it.
    - Each widget shows its type, either a summary value or a chart.
    - Each widget in the catalogue has a checkbox. A ticked box means the widget is already on the dashboard. The user ticks it to add the widget and unticks it to remove it, so the same widget cannot be added twice.
    - Widgets are grouped by topic, for example sales, revenue, customers, and app usage, so they are easier to find.
    - The user can search the catalogue by widget name.
    - The catalogue only lists widgets that the user's role is allowed to see.
    - The KPI names in the catalogue must be the same as the ones used in Member 2's analytics.
    - If a widget's data is not available, the catalogue still shows it but marks it as having no data, and the dashboard shows the no-data message once it is added.
    - Search and grouping by topic could be moved to future scope if time is tight.
2. **Saved selection**: The app remembers which widgets the user chose, so their dashboard looks the same next time they open it. This usually means storing the selected widget IDs in a database or local storage.
    - The selection is personal. Each user has their own saved dashboard, and changes do not affect other users.
    - The app saves when the user confirms their choices in the catalogue, for example by tapping a Save button. Nothing changes on the dashboard until then.
    - If the user closes the catalogue without saving, the previous selection stays as it was.
    - When the user opens the app again, even on a different day, the saved widgets appear on the dashboard.
    - A new user who has not chosen anything yet sees a small default set of widgets, so the dashboard is never empty on first use.
    - The user can reset the dashboard back to the default set with one action.
    - The saved selection also keeps the last chosen time period, so the dashboard opens the way the user left it.
    - If a saved widget is later removed from the catalogue or its data is no longer available, the dashboard skips it and shows a short message instead of breaking.
        - Saving the selection on one device and seeing it on another, like phone and laptop, is future scope. It needs the selection stored in the database, not just on the device.
3. **Widget layout**: The user can arrange the widgets on their dashboard, for example by dragging and dropping them into different positions. The app saves the layout so it is restored next time.
    - The layout is personal. Each user has their own arrangement, and changes do not affect other users.
    - The layout saves automatically after each move. There is no Save button, the same as the widget selection.
    - On a phone, the user can press and hold a widget to drag it. On a computer, the user can drag it with the mouse.
    - The user can reset the layout back to the default order with one action.
    - If a widget is removed from the dashboard, the other widgets move up to fill the gap.
    - If a widget is added, it appears at the end of the dashboard until the user moves it.
    - Storage option A, local storage: the layout is saved on the user's own device.
        - Simpler and faster to build.
        - The layout is lost if the user clears the browser data or switches to another device.
    - Storage option B, database: the layout is saved on the server, tied to the user's account.
        - More work to build.
        - The layout follows the user across devices and is not lost when the browser is cleared.
    - The team picks one option, and the layout should be stored in the same place as the saved widget selection, so the two always stay together.
4. **Time period**: The system displays the selected KPIs for an agreed time period. The supported periods are fixed and listed below, so the user picks one instead of typing dates.
    - The chosen period applies to all KPIs on the dashboard, and the dashboard reloads when the user changes it.
    - The user picks the period from a simple menu or a row of buttons at the top of the dashboard.
    - The screen always shows the selected period and its exact dates, for example "Last 7 days: 1 to 7 Nov", so the user does not have to remember what they chose.
    - The periods are:
        - Today
        - Last 7 days
        - Last 30 days
        - This month
        - Custom range (optional, only if time allows)
    - The dashboard opens on a default period, for example Last 7 days, the first time the user opens it. After that, it opens on the last period the user chose.

### KPI names (shared by Member 1 and Member 2) (Decided by the team once the real data is confirmed)

This is the shared list of KPI names. Member 1's dashboard and Member 2's analytics must use these exact names. The final list will be decided by the team once the real data is confirmed.

| Group | KPI name | What it shows |
| --- | --- | --- |
| Sales | Total Sales | The number of items or orders sold in the period. |
| Sales | Average Order Value | The average amount spent per order in the period. |
| Revenue | Total Revenue | The total money earned in the period. |
| Revenue | Revenue Growth | How much revenue went up or down compared with another period. |
| Customers | New Customers | The number of customers who joined in the period. |
| Customers | Customer Retention Rate | The share of customers who came back in the period. |
| App usage | Active Users | The number of users who used the app in the period. |
| App usage | Conversion Rate | The share of users who completed a purchase or key action. |

- The groups match the data in your plan: sales, revenue, customer numbers, and app usage.
- Only KPIs that can be calculated from the sample datasets or uploaded files will be offered.
- Any KPI not on this list must be added here first, so both sections stay consistent.

### Member 2 – Business Analytics - Len

1. **KPI trend view**: The user picks one KPI and sees how it changes over time, shown as a line chart. A trend is simply how a measure, like revenue or active users, goes up or down over a period.
    - The user picks a time range from a fixed list instead of typing dates. The first four match Member 1's time periods so both sections stay consistent.
    - Today (shown by hour)
    - Last 7 days (shown by day)
    - Last 30 days (shown by day)
    - This month (shown by day)
    - Last 3 months (shown by week)
    - Custom range (optional, only if time allows)
2. **Period comparison**: The user compares two periods of the exact same length, and chooses both periods themselves. The two periods can be from any time the user wants, not only the period right before.
    - The user first picks the length, for example 7 days, 30 days, or one month.
    - The user then picks the start date of Period A and the start date of Period B. The system fills in each end date automatically, so both periods always have the same number of days.
        - Example: Period A is 1 to 7 Nov and Period B is 15 to 21 Sep. Both are 7 days.
    - The two periods cannot overlap, and they cannot be the exact same dates. If they do, the system shows a message asking the user to change one.
    - The screen always shows the exact dates of both periods next to their values, so the user does not have to remember what they chose.
    - The system shows both values side by side, with the difference as a number and as a percentage.
    - If one period has no data, the system says so clearly and shows only the other period.
3. **Filtering**: The user narrows the results to one slice of the business, so they can look at one part instead of everything at once. Filtering works together with the trend view and period comparison, so the chart and numbers update to show only the chosen slice.
    - The user picks a category from a list instead of typing it.
    - The categories are taken from the real data in the sample datasets or uploaded files. The final list will be decided by the team in the real SRS document, once the data is confirmed.
    - Possible examples are product category, region, and sales channel, but these are only examples until the data is checked.
    - After choosing a category, the user picks a value from it, for example Region: North.
    - Only categories and values that actually exist in the data are offered.
    - The user can apply one category filter at a time. Combining several filters is future scope.
    - The screen always shows which filter is active, so the user does not have to remember what they chose.
    - The user can clear the filter with one action and return to the full data.
    - If no data matches the filter for the chosen period, the system shows a clear empty message instead of a blank chart.
4. **Drill-down**: The user selects a point on a chart, such as one day's revenue, and sees the detailed records behind that number. Drill-down simply means moving from a summary value to the details that make it up.
    - The user taps or clicks a point on the trend chart, for example the bar for 3 Nov.
    - The system opens a detail view, as a separate screen or a pop-up, listing the records that make up that value, such as individual orders or transactions.
    - The detail view shows the selected KPI, the exact date or period, and any active filter, so the user does not have to remember what they selected.
    - The detail list respects the active filter. If the filter is Region: North, only records from the North are shown.
    - The user can go back to the chart with one action, and the chart stays as it was.
    - If the selected point has no detailed records, the system shows a clear empty message.
    - Drill-down works on the trend view first. Drilling down from the period comparison is a later improvement.
5. **No-data rule**: This applies to all four items above. When there is nothing to show, the system explains why instead of showing a blank screen.
    - If no data exists for the chosen period or filter, the system shows a clear message such as "No data for this period" instead of a blank chart.
        - Note (Future scope): The message tells the user exactly what is missing, for example which dates or categories have no data, so the user knows what to upload or select.
    - The message also shows the period and filter the user selected, so they can see what to change.
    - In period comparison, if only one period has data, the system shows that period and states that the other has no data.
    - The user can change the period or clear the filter directly from the message.
    - An empty result is not treated as an error. The system keeps working normally.
    - The KPI names must be the same as the ones used in Member 1's dashboard.

### Member 3 – Reporting & Notifications - Phu

#### Reporting

1. **Report generation**: The user picks which KPIs to include and a time period, and the system creates a report as a PDF file. A report is a snapshot. It records the numbers for the chosen period at the time it was generated, and it does not change afterward, even when the live data refreshes.
    - The user picks one or more KPIs from the shared KPI list. The KPI names must be the same as the ones used in Member 1's dashboard and Member 2's analytics.
    - The user picks the period from a fixed list instead of typing dates. The periods match Member 1's time periods so all sections stay consistent.
        - Today
        - Last 7 days
        - Last 30 days
        - This month
        - Custom range (optional, only if time allows)
    - The report shows the selected KPIs, the exact dates of the period, and the date and time the report was generated, so readers know which data it reflects.
    - The report is a PDF. Other formats, such as CSV, are future scope.
    - The user can only select KPIs that their role is allowed to see.
    - Each report has a name, which the user can type or leave as a default such as "Total Revenue, Last 7 days".
2. **Report list**: The app saves every generated report in a list, so the user can open it again later.
    - The list is personal. Each user sees the reports they created, plus the reports shared with them in a separate "Shared with me" section.
    - Each item in the list shows the report name, the period, and the date and time it was generated.
    - The user can open a report, download the PDF, or delete a report they created.
    - Deleting a report also removes access for everyone it was shared with.
    - A saved report does not change when the data refreshes. To get newer numbers, the user generates a new report.
3. **Report sharing**: The owner of a report can share it with other users. The report stays view-only for the people it is shared with.
    - Only the owner can share a report.
    - The owner can share with a whole group, but only a group the owner belongs to.
    - The owner can also share with individual users, including users who are not in a shared group with the owner.
    - Recipients can open and download the report. They cannot edit it, delete it, or share it again.
    - The report appears in the recipient's "Shared with me" section.
    - Each recipient gets a notification in the app and by email when a report is shared with them. This uses the same delivery and retry rules as alerts.
    - The owner can stop sharing at any time, and the recipient loses access.
    - If a recipient leaves the group the report was shared with, they lose access to it.
    - Checking whether a recipient is allowed to see the KPIs in the report is future scope. A shared report may show KPIs the recipient could not normally add to their own dashboard. This is a known assumption.
4. **Report generation rules for missing or invalid input**: This applies to report generation. When the input is wrong or there is nothing to show, the system explains why instead of failing silently.
    - If the user selects no KPIs, the system asks them to choose at least one.
    - If the period is invalid, such as an end date before the start date or a date in the future, the system shows a message asking the user to fix it.
    - If none of the selected KPIs have data for the period, the system shows a clear no-data message and does not create a report.
    - If only some of the selected KPIs have data, the system shows a confirmation box listing the KPIs with no data.
        - "Generate without them" creates the PDF with only the KPIs that have data.
        - "Cancel" creates nothing and returns the user to the selection screen.
        - The PDF does not mention the skipped KPIs.
    - If the system fails while building the PDF, it shows an error message, creates no report, and lets the user try again.
    - If the owner is not in any group, group sharing is not available, but sharing with individual users still works.
5. **Scheduled reports**: The user sets a report to be generated automatically on a repeating schedule, such as every Monday at 8 AM, without doing it by hand each time.
    - The user picks the KPIs, the period, and the frequency, for example weekly or monthly.
    - The system generates the PDF on schedule and adds it to the report list, with a notification to the user.
    - The user can edit or cancel a schedule.
    - If a scheduled run fails, the system notifies the user.
    - Note: the capabilities table lists scheduling, so the SRS must state clearly that it is deferred to a later version.

#### Notifications

1. **Alert rules**: The user creates a rule that tells BizBoard when to warn them about a KPI. This is a user-defined threshold. Automatic anomaly detection using AI is future scope.
    - A rule has three parts: the KPI to watch, the condition, and the number.
        - KPI: picked from the shared KPI list. The names must be the same as the ones used in Member 1's dashboard and Member 2's analytics.
        - Condition: "goes above" or "goes below".
        - Number: the threshold value the user types in.
        - Example: alert me when Total Revenue goes below 1,000.
    - The user sets up a rule with a simple form: a KPI dropdown, a condition dropdown, and a number box, then taps Save. Nothing changes until the user saves.
    - The user can only select KPIs that their role is allowed to see.
    - The number must be a valid number. If it is empty or not a number, the system shows a message asking the user to fix it.
    - The user can edit, turn off, or delete their own rules.
    - Rules are personal. Each user has their own rules, and they do not affect other users.
    - A user can have several rules on the same KPI, for example "Total Revenue goes below 1,000" and "Total Revenue goes above 10,000". Each one is a separate rule, checked on its own, and can be edited, turned off, or deleted without affecting the others.
    - (Future scope) Combining several conditions into one rule, for example "Total Revenue below 1,000 and Active Users below 50".
2. **Alert checking and creation**: After each data refresh, the system checks every active rule against the latest KPI values.
    - The data refreshes every 5 minutes, or right away when a new file is uploaded. Rules are checked after each refresh.
    - If a KPI crosses the threshold in a rule, the system creates one alert for the user who owns that rule.
    - The alert shows the KPI name, the condition, the threshold, the actual value, and the date and time.
    - While the KPI stays across the threshold, the system does not create more alerts for the same rule. The system remembers whether each rule is currently triggered.
    - A new alert is created only after the KPI goes back to normal and then crosses the threshold again.
    - If a rule is turned off and later turned back on, the system treats it as new, so it can alert right away if the KPI is still across the threshold.
    - If the KPI has no data after a refresh, the system skips the rule and creates no alert.
3. **Alert delivery**: The alert reaches the user in the app and by email. Push notifications are future scope, because BizBoard is a web app and not a dedicated mobile app.
    - The alert is always saved in the in-app notification list, so the user never completely misses it.
    - The email is sent within 1 minute after the refresh in which the alert condition was found.
    - The in-app notification list shows unread alerts first, and the user can mark an alert as read.
    - If the email cannot be delivered, the system retries 3 times, 1 minute apart. If an attempt succeeds, the system stops retrying.
    - If every retry fails, the system logs the failure and marks the alert as "email not delivered" in the notification list.
    - The 1-minute target applies to the first email attempt. After that, the in-app alert is the guaranteed record.
    - The same delivery rules apply when a report is shared with the user.
4. **Notification settings**: Each user controls their own email notifications from a section on their profile page. Settings are personal and do not affect other users.
    - There are two separate on-off switches.
        - Email when an alert is triggered.
        - Email when a report is shared with the user.
    - Both switches are on by default.
    - In-app notifications cannot be turned off.
    - If a switch is off, the system sends no email and makes no retries for that type, but the in-app notification still appears.
    - If the user has no email address saved, the system sends in-app notifications only.
    - Turning off a single alert rule is separate from turning off email. A turned-off rule creates no alerts at all.

### Member 4 – Collaboration & Access Management - Yuki

1. **Groups**: A group is a set of users who work together and share reports. Any user can create a group, and the creator automatically becomes its Group Owner.
    - The user creates a group by giving it a name. The Group Owner can rename the group later.
    - A user can belong to several groups, and can be the Group Owner of several groups.
    - The Group Owner adds members directly by searching for a user by name or email. The person is added right away. There is no invitation step.
    - The Group Owner can remove a member at any time. A removed member loses access to anything that was shared with that group, and can no longer write notes on those reports.
    - A member can leave a group at any time.
    - A group must always have one Group Owner. If the Group Owner wants to leave, they must first hand the group over to another member, or delete the group.
    - The Group Owner can delete the group. Reports are not deleted. They are no longer shared with that group.
    - (Future scope) Invitations that the person must accept before joining.
2. **Group roles and permissions**: Inside a group, there are two roles. Permissions apply to that group only.
    - Member: belongs to the group, can open reports shared with the group, can write notes on those reports, and can share their own reports with the group.
    - Group Owner: can do everything a member can, and can also add or remove members and rename or delete the group.
    - The System Administrator sits above all groups. See item 5.
    - A user who is not in a group cannot see the group, its members, or what was shared with it.
    - If a user without permission tries to open a report or a group, the system shows a clear message saying they do not have access, and shows nothing else.
3. **Report sharing boundaries**: This item sets how Member 3's report sharing connects to groups.
    - Any user who creates a report can share it with any individual user in the system.
    - The same user can share it with any group they belong to, but not with a group they are not in.
    - Dashboards are not shared inside the app. Users can share them outside the app, for example by screenshot.
    - A recipient who gets a report through a group is in the same group as the report, so they can read and write notes.
    - A recipient who gets a report only as an individual, outside any group the report was shared with, can open the report and read the notes, but cannot write notes.
    - The owner of the report can stop sharing at any time, and the recipient loses access.
4. **Report notes**: A note is a short text message written under a report. Notes work like a small comment thread. The PDF itself never changes, only the notes under it grow.
    - Each note shows the author's name and the date and time it was written.
    - The report owner can always write notes on their own report.
    - Members of a group that the report is shared with can write notes on that report.
    - A person who has the report only as an individual, outside the group, can read notes but cannot write them.
    - The author can edit or delete their own notes.
    - The report owner can delete any note on their report.
    - The System Administrator can delete any note.
    - If a user loses access to the report, for example by leaving the group, they can no longer see or write notes on it. Their old notes stay.
    - (Future scope) Annotations pinned to a specific KPI or point inside a report.
5. **System Administrator**: The System Administrator manages all groups and permissions across the whole system.
    - View all groups and their members.
    - Add or remove members in any group.
    - Change the Group Owner of any group, for example when the owner has left the company.
    - Delete any group.
    - Delete any note.
    - Managing user accounts such as deactivating a user, and an activity log of who added or removed members.

## Non-Functional Requirements

| ID | Quality area | Requirement | Measurement and conditions |
| --- | --- | --- | --- |
| NFR-01 | Performance | Under normal load, 95% of dashboard views shall display their main content within 3 seconds. This target is set slightly above Google's recommended Largest Contentful Paint of 2.5 seconds (web.dev), allowing for the extra data processing that dashboards need. | Measured on a normal mobile connection using the sample datasets or uploaded files. |
| NFR-02 | Performance | The system shall generate a PDF report within 10 seconds for a typical selection of KPIs and a period of up to 30 days. | Measured from the moment the user confirms until the report appears in the report list. |
| NFR-03 | Performance | The system shall refresh dashboard data every 5 minutes, or right away when a new file is uploaded. | Checked by comparing data timestamps over a 1-hour period. |
| NFR-04 | Performance | The system shall send the alert email within 1 minute after the data refresh in which the alert condition was found. | Measured from the end of the refresh to the first email send attempt. |
| NFR-05 | Security | The system shall deny access to a report, group, or note when the requesting user lacks permission, and shall show a clear no-access message. | Tested by trying to open items without permission, using accounts with different roles. |
| NFR-06 | Security | The system shall require users to sign in before accessing any data, and shall end a session after 30 minutes of inactivity. | Tested by leaving a signed-in session idle for 30 minutes and then trying to open a page. |
| NFR-07 | Security | The system shall use HTTPS for all communication between the browser and the server. | Checked by confirming no page or request is served over plain HTTP. |
| NFR-08 | Security | The system shall store passwords only as salted hashes, never as plain text. | Checked by inspecting the stored user data. |
| NFR-09 | Availability | The system shall achieve 99.5% monthly availability, excluding scheduled maintenance. | Measured as the percentage of successful health checks over a 30-day month. |
| NFR-10 | Reliability | If an alert email cannot be delivered, the system shall retry 3 times, 1 minute apart, and shall always keep the alert in the in-app notification list. | Tested by simulating a mail server failure and checking the retries and the in-app alert. |
| NFR-11 | Reliability | The system shall not lose saved dashboard selections, reports, alert rules, groups, or notes after a restart or failure. | Tested by restarting the system and confirming all saved items are still there. |
| NFR-12 | Compatibility | The interface shall work on the latest two versions of Chrome, Safari, and Edge, on screen widths from 360 pixels (phones) up to desktop sizes. | Tested on each browser at 360 pixels wide and at a standard desktop width. |
| NFR-13 | Usability | A new user shall be able to add a widget to their dashboard within 1 minute without instructions. | Tested by timing at least 5 first-time users on the task. |
| NFR-14 | Usability | All interactive controls on the mobile interface shall be at least 44 by 44 pixels. | Checked by measuring buttons and controls on a 360-pixel-wide screen. |

## Traceability Matrix

### How the team fills it in
1. Reserve your IDs in the master Google Sheet before writing anything. Never invent an ID on your own.
2. Add your row as soon as you write a user story. Do not wait until the end.
3. Fill the columns left to right: user story, use case, functional requirements, acceptance criteria.
4. If one story has several use cases or requirements, write them all in the same cell, separated by commas.
5. Each person fills only their own rows. The person who owns the master list checks the whole table.
6. When a requirement changes, update its story, use case, diagram, and row in the same sitting.

### Matrix

| User story | Use case | Functional requirements | Acceptance criteria | NFR (if related) | Owner | Status |
| --- | --- | --- | --- | --- | --- | --- |
| US-01 | UC-01 | FR-01, FR-02 | AC-01, AC-02 | NFR-01 | Member 1 | Draft |
| US-02 | UC-02 | FR-03 | AC-03 | | Member 2 | Draft |
| US-03 | UC-03 | FR-04, FR-05 | AC-04, AC-05 | NFR-02 | Member 3 | Draft |
| US-04 | UC-04 | FR-06 | AC-06 | NFR-05 | Member 4 | Draft |

The rows above are only examples. Replace them with the team's real IDs.

### Checks before submitting

| Check | Done |
| --- | --- |
| Every user story appears in at least one row, with a use case, a functional requirement, and an acceptance criterion. | [ ] |
| Every use case in the use case diagram appears in the matrix. | [ ] |
| Every functional requirement traces back to a user story (backward check). | [ ] |
| Every acceptance criterion traces back to a user story and a requirement (backward check). | [ ] |
| Every NFR listed is tied to the feature it affects, where one applies. | [ ] |
| No ID appears twice in the master list, and no ID is used in the report that is missing from it. | [ ] |
| Names of KPIs, roles, and features are spelled the same in the matrix as in the report. | [ ] |
| Each row has a named owner and a status of Final. | [ ] |
