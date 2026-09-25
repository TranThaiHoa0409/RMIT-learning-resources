# FoAI Week 8 – Lecture: Ethics & Laws for AI (cont.)

## 1. Good for Others Is Good for Yourself

### 1.1 Volunteering and Well-Being

> "Growing evidence suggests that volunteers reap health and well-being benefits from their altruistic activities."
> — Dr Eric S. Kim, Harvard University

Data were from 12,998 participants in the Health and Retirement Study — a large, diverse, prospective, and nationally representative cohort of U.S. adults. During the 4-year follow-up period, participants who volunteered ≥100 hours/year (versus 0 hours/year) had a reduced risk of mortality and physical functioning limitations, higher physical activity, and better psychosocial outcomes.

### 1.2 What Goes Around, Comes Around

**Good for Others Is Good for Yourself (and Vice Versa)**

> "To every action there is always opposed an equal reaction."
> — Newton's Third Law

## 2. Laws in Data Science & AI

### 2.1 Laws for Data Analytics

**United States:**

- Privacy laws, e.g., HIPAA 1996, COPPA 1998, FACTA 2003
- Negligence laws

**European Union:** General Data Protection Regulation (GDPR), main rules:

- Data analytics must be fair.
- You need permission to process data.
- Individuals are allowed to access their personal data and correct it if it's not correct.
- Accountability.

### 2.2 Fair Information Practices — OECD Guidelines

OECD (Organisation for Economic Co-operation and Development) Guidelines on the Protection of Privacy and Transborder Flows of Personal Data, adopted in 1980.

Adopted by 38 countries, including Australia, Canada, Germany, Italy, Japan, Mexico, New Zealand, the United Kingdom, and the United States. The guidelines are composed of 8 principles.

| Principle | Guideline |
|---|---|
| Collection limitation | The collection of personal data must be limited; all such data must be obtained lawfully and fairly with the subject's consent and knowledge. |
| Data quality | Personal data should be accurate, complete, current, and relevant to the purpose for which it is used. |
| Purpose specification | The purpose for which personal data are collected should be specified and should not be changed. |
| Use limitation | Personal data should not be used beyond the specified purpose without a person's consent or by authority of law. |
| Security safeguards | Personal data should be protected against unauthorized access, modification, or disclosure. |
| Openness principle | Data policies should exist, and a data controller should be identified. |
| Individual participation | People should have the right to review their data, to challenge its correctness, and to have incorrect data changed. |
| Accountability | A data controller should be responsible for ensuring that the above principles are met. |

### 2.3 AI Governance Frameworks

Two different AI governance approaches:

- The European Union's rules-based regulatory approach: Artificial Intelligence Act [[1](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=OJ%3AL_202401689)]
- The United States' innovation-driven model: Executive Order No. 14110 [[2](https://www.federalregister.gov/documents/2023/11/01/2023-24283/safe-secure-and-trustworthy-development-and-use-of-artificial-intelligence)]

### 2.4 EU Artificial Intelligence Act

The EU AI Act is the world's first comprehensive AI legal framework, classifying AI systems according to risk level:

- **Unacceptable Risk (Banned):** Bans on dangerous AI systems, such as those that manipulate individuals without their awareness or exploit vulnerable groups (e.g., children, or persons with disabilities).
- **High Risk (Strictly Regulated):** Classifies AI based on deployment context (e.g., recruitment, grading, critical infrastructure). Developers must prove compliance with strict safety, transparency, and bias-mitigation standards before entering the EU market.

Non-compliance can result in fines of up to 7% of global annual turnover or €35 million, whichever is higher.

### 2.5 US Executive Order No. 14110

The US Executive Order (EO) 14110 relies on existing laws and standards-setting to balance AI risks with innovation flexibility.

- **Treating AI as a National Security Concern:** advanced AI systems are treated as potential national security threats. Companies developing large-scale or "frontier" models must report their training processes and red-teaming test results to the U.S. Department of Commerce.
- **Setting the Standards:** the EO directs the National Institute of Standards and Technology (NIST) to develop AI safety, security, and risk-management standards. 

While compliance with these standards is voluntary, they become mandatory for companies seeking to sell AI systems to the U.S. federal government. Given the scale of government procurement, this approach leverages market power to encourage widespread adherence to safety norms.

*Signed by President Joe Biden in 2023, rescinded by President Donald Trump in 2025.*

### 2.6 Vietnam AI Law — Risk Classification

The Vietnam Law on AI, No. 134/2025/QH15, took effect in 2026 and is the first legal framework for AI in Southeast Asia. It is similar to the EU AI Act in its use of AI risk classification:

- **High Risk:** AI systems capable of causing significant harm to life, health, public interest, or national security.
- **Medium Risk:** AI systems that can mislead, or influence users who are unaware that they are interacting with AI.
- **Low Risk:** AI systems that do not fall into the high- or medium-risk categories.

### 2.7 Vietnam AI Law — Ethical Framework

The Vietnam AI Law also promotes the development and application of AI and establishes an ethical framework for AI use:

- Ensure safety and prevent harm to human life, health, dignity, and psychological well-being.
- Respect human rights and ensure fairness, non-discrimination in AI development and use.
- Promote happiness, prosperity, and the sustainable development of individuals and society.
- Encourage social responsibility in AI research, development, and application.

*National Assembly of Vietnam. (2025, December 10). Luật Trí tuệ nhân tạo [Law on Artificial Intelligence] (Law No. 134/2025/QH15). [Cổng Thông tin điện tử Chính phủ](https://vanban.chinhphu.vn/?pageid=27160&docid=216334).*

## 3. IRAC Method & Real Stories

### 3.1 IRAC Method

A traditional method for analyzing legal problems, includes 4 parts: Issue, Rule, Application, and Conclusion (IRAC).

- **Issue:** define the legal problem.
- **Rule:** identify laws that are applicable to the Issue.
- **Apply:** analyze the Issue according to the Rule.
- **Conclusion:** make a legal conclusion to the Issue.

### 3.2 Case Study: U.S. Target Marketing (2012) — Personal Data Infringed?

An angry man went into a Target store in Minneapolis and talked to a manager:

> "My daughter got this in the mail! She's still in high school, and you're sending her coupons for baby clothes and cribs? Are you trying to encourage her to get pregnant?"

→ Privacy concern: Personal data infringed?

**The "pregnancy-prediction" model**: Target's data analytics team developed a "pregnancy-prediction" model that could identify pregnant shoppers based on their purchasing habits (e.g., buying specific items like unscented lotion, cotton balls, and certain vitamins).

→ Target's "pregnancy-prediction" model was so accurate it could identify pregnant shoppers and send them targeted ads — in this case, before the girl's father was aware of her pregnancy.

→ Privacy concern: **Personal data infringed?**

### 3.3 Case Study: Tesla Autopilot (2016) — Whose Fault: Tesla or Driver?

In 2016, Joshua Brown was killed when his Tesla Model S, operating in Autopilot mode, crashed into a tractor-trailer making a left turn on a Florida highway.

**The Cause:** Tesla's Autopilot system failed to distinguish the trailer's white side against a brightly lit sky. As a result, the car did not brake and passed under the trailer, shearing off the roof.

**Federal Investigation:** The U.S. National Highway Traffic Safety Administration (NHTSA) opened an investigation into the crash and other incidents where automatic braking systems — expected to activate — did not.

**Tesla's Response:** The Autopilot system is an "assist feature" (Level 2 automation), and the driver is responsible for remaining alert with their hands on the wheel.

**Levels of Self-Driving Cars (SAE J3016)**

| Level | Name | Description |
|---|---|---|
| Level 0 | No Automation | Human does everything |
| Level 1 | Driver Assistance | Assist with steering OR speed |
| Level 2 | Partial Automation | Assist with steering AND speed, Driver monitors |
| Level 3 | Conditional Automation | Car drives in some conditions, Driver must take over when asked |
| Level 4 | High Automation | Car drives itself in certain areas, No driver needed in those cases |
| Level 5 | Full Automation | Car drives anywhere, No human needed |

### 3.4 Explainable AI (XAI)

> "New machine-learning systems will have the ability to **explain their rationale** [...] These models will be combined with state-of-the-art human-computer interface techniques capable of translating models into understandable and useful **explanation dialogues for the end user**."

![DARPA Explainable AI (XAI) diagram illustrating the shift from opaque machine-learning models to explainable models with a user explanation interface](images/Week8/28_darpa_xai_diagram.png)

**Examples of XAI projects:**

- [Microsoft InterpretML](https://github.com/interpretml/interpret)
- [Explainability for PyTorch](https://github.com/jacobgil/pytorch-grad-cam)

**Want to learn about XAI?**

Christoph, M. (2025). *Interpretable machine learning: A guide for making black box models explainable.* ISBN: 978-3-911578-03-5. [Available](https://christophm.github.io/interpretable-ml-book)
