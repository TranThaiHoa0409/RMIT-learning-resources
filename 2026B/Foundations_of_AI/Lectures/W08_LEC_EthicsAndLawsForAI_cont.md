# Foundations of AI — Lecture 8: Ethics & Laws for AI (cont.)

> Source: `W08_LEC_EthicsAndLaws_cont.pptx`
> Recap from previous session: Ethics Approaches, Golden Rule

---

## 1. Recap — Golden Rule

- **Golden Rule (summary):** *Good for others is good for yourself* (and vice versa).
- Parallels drawn from other domains:
  - **Newton's 3rd Law:** "To every action there is always opposed an equal reaction."
  - **Karma / reciprocity idiom**, expressed across many cultures:
    - EN: You reap what you sow.
    - VN: *Gieo nhân nào, gặt quả nấy.*
    - DE: Du erntest, was du säst.
    - FR: On récolte ce que l'on sème.
    - ES: Lo que siembres, cosecharás.
    - ZH: 种瓜得瓜，种豆得豆
    - TH / LO also have equivalent sayings.
- Supporting evidence cited: Dr. Eric S. Kim (Harvard) — study on ~12,998 U.S. adults (Health and Retirement Study) found volunteering ≥100 hrs/year (vs. 0) was linked to lower mortality risk, better physical functioning, and improved psychosocial outcomes.
  - Citation: Kim, E. S., Whillans, A. V., Lee, M. T., Chen, Y., & VanderWeele, T. J. (2020). *Volunteering and subsequent health and well-being in older adults: an outcome-wide longitudinal approach.* American Journal of Preventive Medicine.

---

## 2. Laws in Data Science & AI

### 2.1 Regional/sector laws
- **United States:**
  - Privacy laws: HIPAA (1996), COPPA (1998), FACTA (2003)
  - Negligence laws
- **European Union — GDPR**, key rules:
  - Data analytics must be fair.
  - Permission is required to process data.
  - Individuals can access and correct their personal data.
  - Accountability principle.
- Main reference: *Ethics and Law in Data and Analytics*, Microsoft, edX.

### 2.2 Fair Information Practices (OECD, 1980)
- OECD Guidelines on the Protection of Privacy and Transborder Flows of Personal Data.
- Adopted by 38 countries (incl. Australia, Canada, Germany, Italy, Japan, Mexico, New Zealand, UK, US).
- Composed of **8 principles** (see slide 13 image / Reynolds textbook).
- Reference: Reynolds, G. (2019). *Ethics in Information Technology* (6th ed.). Cengage Learning.

### 2.3 AI Governance Frameworks — two models
| Region | Approach | Instrument |
|---|---|---|
| European Union | Rules-based regulatory approach | Artificial Intelligence Act |
| United States | Innovation-driven model | Executive Order No. 14110 |

#### EU AI Act
- World's first comprehensive AI legal framework; classifies AI systems by **risk level**:
  - **Unacceptable Risk (Banned):** e.g., systems that manipulate individuals without awareness, or exploit vulnerable groups (children, persons with disabilities).
  - **High Risk (Strictly Regulated):** classified by deployment context (recruitment, grading, critical infrastructure); developers must prove compliance with safety, transparency, bias-mitigation standards before market entry.
  - **Non-compliance penalty:** fines up to **7% of global annual turnover or €35 million**, whichever is higher.

#### US Executive Order No. 14110
- Signed by President Biden (2023); **rescinded by President Trump (2025)**.
- Relies on existing laws + standards-setting rather than new binding legislation.
- Key elements:
  - **National security framing:** frontier/large-scale AI developers must report training processes and red-teaming results to the U.S. Department of Commerce.
  - **Standards-setting:** directs NIST to develop AI safety, security, and risk-management standards.
  - Compliance is **voluntary** in general, but **mandatory for vendors selling to the U.S. federal government** — leverages procurement power to drive adoption.

### 2.4 Vietnam AI Law (Law No. 134/2025/QH15)
- Took effect in 2026 — **first legal AI framework in Southeast Asia**.
- Similar structure to the EU AI Act (risk classification):
  - **High Risk:** AI systems capable of significant harm to life, health, public interest, or national security.
  - **Medium Risk:** AI systems that can mislead or influence users unaware they are interacting with AI.
  - **Low Risk:** everything else.
- Also establishes an **ethical framework** for AI development/use:
  - Ensure safety; prevent harm to human life, health, dignity, psychological well-being.
  - Respect human rights; ensure fairness and non-discrimination.
  - Promote happiness, prosperity, sustainable development of individuals and society.
  - Encourage social responsibility in AI research, development, and application.
- Citation: National Assembly of Vietnam. (2025, Dec 10). *Luật Trí tuệ nhân tạo* [Law on Artificial Intelligence] (Law No. 134/2025/QH15). Cổng Thông tin điện tử Chính phủ. https://vanban.chinhphu.vn/?pageid=27160&docid=216334

---

## 3. IRAC Method & Real Cases

### 3.1 IRAC Method (legal analysis framework)
| Step | Meaning |
|---|---|
| **I**ssue | Define the legal problem |
| **R**ule | Identify applicable laws |
| **A**pplication | Analyze the issue according to the rule |
| **C**onclusion | Draw the legal conclusion |

- Main reference: *Ethics and Law in Data and Analytics*, Microsoft, edX.

### 3.2 Case Study — Target "Pregnancy Prediction" (US, 2012)
- A father complained to a Target manager after his (then) high-school-age daughter received baby/crib coupons in the mail.
- Target's data analytics team had built a **"pregnancy-prediction" model** using purchase patterns (unscented lotion, cotton balls, certain vitamins, etc.).
- The model was accurate enough to identify a pregnancy **before the girl's own father knew**.
- Core ethical/legal question: **Was personal data infringed?**
- Source: forbes.com

### 3.3 Case Study — Tesla Autopilot Fatal Crash (2016)
- **Incident:** Joshua Brown killed when his Tesla Model S (in Autopilot) crashed into a tractor-trailer making a left turn on a Florida highway.
- **Cause:** Autopilot failed to distinguish the trailer's white side against a bright sky — the car didn't brake and passed under the trailer, shearing off the roof.
- **Federal investigation:** NHTSA opened an investigation into this and similar automatic-braking failures.
- **Tesla's response:** Autopilot is an "assist feature" (Level 2 automation); the driver remains responsible for staying alert with hands on the wheel.
- Core question: **Whose fault — Tesla or the driver?**
- Context: Tesla Autopilot has been linked to numerous notable fatal crashes from 2016–2024 across the US, Japan, China, and Norway (full list: tesladeaths.com).

### 3.4 Explainable AI (XAI)
- DARPA vision: future ML systems should be able to **explain their rationale**, paired with human-computer interface techniques that turn model internals into understandable explanations for end users.
- Example XAI tools/projects:
  - Microsoft InterpretML — github.com/Microsoft/interpret
  - Explainability for PyTorch (Grad-CAM) — github.com/jacobgil/pytorch-grad-cam
- Further reading: Christoph, M. (2025). *Interpretable Machine Learning: A Guide for Making Black Box Models Explainable*. ISBN 978-3-911578-03-5. https://christophm.github.io/interpretable-ml-book

---

## 4. Key Takeaways (exam-relevant)
1. Golden Rule ≈ reciprocity ethics, cross-culturally universal, backed by empirical well-being research.
2. AI/data laws differ by region: US = sector-specific privacy laws; EU = comprehensive GDPR + risk-tiered AI Act; Vietnam = new risk-tiered AI Law (134/2025/QH15) modeled partly on the EU approach.
3. Two governance philosophies: **EU = rules-based**, **US = innovation-driven/voluntary-but-market-enforced**.
4. IRAC is the standard method to analyze AI-related legal/ethical cases (Issue → Rule → Application → Conclusion).
5. Real-world cases (Target, Tesla) illustrate tension between data-driven personalization/automation and privacy/accountability.
6. XAI is a response to the "black box" problem — making AI decisions interpretable to end users.

---

*Converted from `W08_LEC_EthicsAndLaws_cont.pptx` for personal exam review (Assignment 2, Foundations of AI — COSC2968/COSC3053).*
