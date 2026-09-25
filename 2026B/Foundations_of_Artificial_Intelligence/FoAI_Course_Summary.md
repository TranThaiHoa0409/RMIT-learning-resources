# Foundations of Artificial Intelligence (FoAI) — Course Summary

**COSC2968 / COSC3053, RMIT University Vietnam**
Main reference: Russell, S. J., & Norvig, P. (2020). *Artificial Intelligence: A Modern Approach*. Pearson.

This summary is compiled from the Lecture and Tutorial notes for Weeks 1–11.

## Course Structure

| Module | Weeks | Theme | Topics |
| --- | --- | --- | --- |
| Module #1 | 1–4 | Evolution & Core Concepts of AI | AI approaches; AI current state; future with AI; machine learning core concepts |
| Module #2 | 5–7 | AI Ethics & Safety | Ethics approaches; ethical issues in data science; law cases in AI; explainable AI |
| Module #3 | 8–12 | Applied AI | Build AI models (no-code); machine learning project (coding); AI for work & life |

**Week-by-week:**

- Week 1 — Course Info & AI Approaches
- Week 2 — AI: Past and Present
- Week 3 — In the Future with AI & Data Science
- Week 4 — Introduction to Machine Learning
- Week 5 — Ethics & Safety of AI
- Week 6 — Ethics & Safety of AI (cont.)
- Week 7 — Personal Development Week
- Week 8 — Guest lecture / company visit (tentative)
- Week 9 — Machine Learning Projects (no-code)
- Week 10 — End-to-End Machine Learning (coding)
- Week 11 — AI for Work & Life
- Week 12 — Presentation

---

## Module 1 — Evolution & Core Concepts of AI

### Week 1: Course Overview & AI Approaches

- **Why learn AI:** AI/ML is a top in-demand tech skill in Vietnam and globally; Vietnam's National Strategy on R&D and AI targets top-50 global AI standing by 2030. Vietnam IT Salary & Recruitment Market Report (2025–2026) data: 73% of Vietnamese companies use AI, 96.8% of IT professionals use AI daily, and code generation/knowledge-base generation/code review are the top AI use cases.
- **What is AI:** an artificial (human-made) intelligent entity. Two dimensions define an AI approach: characteristics (**Humanly** vs **Rationally**) crossed with manifestation (**Think** vs **Act**), giving four approaches:
  - **Act Humanly** — the Turing Test (Alan Turing, 1950): a machine passes if a human judge cannot distinguish its written responses from a human's. Example: Eugene Goostman chatbot fooled 33% of judges in 2014.
  - **Think Humanly** — modeling human thought via introspection, psychological experiments (e.g., Stanford Prison Experiment), and brain imaging (MRI, EEG).
  - **Think Rationally** — logic-based reasoning (Logicism, requiring strict true/false) and probability/fuzzy logic for reasoning under uncertainty; deep learning and LLMs are fundamentally grounded in probability theory.
  - **Act Rationally** — choosing actions that maximize expected outcome; considered the most general approach since logical reasoning is not the only path to a good outcome (e.g., reflexes), and it can avoid the flaws of human cognition.
- **Bonus theme — introspection & self-awareness:** noticing an angry thought early (like noticing a small fire) lets you stop it before it becomes harmful words/actions (illustrated with a Japanese samurai folktale).

### Week 2: AI — Past and Present

- **Three waves, two AI winters:**
  1. **1st Wave (1950s–60s):** Dartmouth Workshop (1956) founded AI as a discipline; early successes in games/maths (Lisp, Checkers program, Perceptron).
  2. **1st AI Winter (1970s):** funding cuts due to limited compute, intractable problems, Moravec's paradox.
  3. **2nd Wave (1970s–80s):** expert systems using expert-derived logical rules (DENDRAL, MYCIN, XCON).
  4. **2nd AI Winter (1980s–90s):** expert systems proved brittle and hard to maintain.
  5. **3rd Wave (since 1990s):** deep learning resurgence driven by more data and compute (Deep Blue 1997, Watson 2011, AlphaGo 2015, ChatGPT 2022).
- **State-of-the-art applications surveyed:** language/communication (real-time speech translation, meeting interpreters), computer vision (Google Lens, visual search, agricultural weed-spraying robots, food inspection), AI for good (food-waste reduction, bushfire detection, train-derailment prevention, typhoon forecasting), generative AI/deepfakes (photo reconstruction, video-from-image, fake video of public figures), robotics/machine control (assistive robot arms learning from a joystick), and AI in banking.

### Week 3: In the Future with AI & Data Science

- **AI milestones vs. human champions:** Deep Blue beat Kasparov at chess (1997); IBM Watson beat Jeopardy! champions (2011); DeepMind's Deep Q-Learning mastered Atari from raw pixels; AlphaGo beat Ke Jie (2017) and AlphaGo Zero surpassed all prior versions by self-play alone; AlphaStar beat a top StarCraft II pro (2019); MuZero mastered games without being told the rules; IBM's Project Debater lost a formal debate to human champion Harish Natarajan (2019); a Johns Hopkins surgical robot performed autonomous surgery.
- **AI assistants/companions:** Project Astra, π0 (general-purpose robot foundation model), mental-health chatbots (Woebot, EarKick, Wysa, Youper) — raising the question of AI companionship as "cure or danger."
- **What AI still struggles with / frontier research:** AI-driven materials discovery (predicting thermoelectric materials years ahead of human discovery), AlphaTensor (improved on Strassen's matrix-multiplication algorithm after 50 years), AlphaEvolve (algorithm discovery).
- **Philosophical questions:** what is intelligence, what counts as artificial, what is "the self" — framed with reference to Buddhist philosophy (Skandhas, Dhammapada) as an example of how philosophy informs long-range thinking about AI.
- **Optimism vs. pessimism:** quotes from Stephen Hawking (AI could be "the best or the worst" thing to happen to humanity) and Geoffrey Hinton (hard to stop bad actors from misusing AI) — setting up Module 2 (Ethics & Laws).
- **Introduction to Data Science:** Data Science = Data + Machine Learning; an interdisciplinary field extracting knowledge/insight from data. Common data types: tabular (rows = samples, columns = features; `.csv`/`.xlsx`), textual (`.txt`; tasks: IR, NLP, IE), audio (`.wav`/`.mp3`; tasks: speech/voice recognition, TTS), and image (`.jpeg`/`.png`; tasks: classification, retrieval, object recognition, segmentation).

### Week 4: Introduction to Machine Learning & Deep Learning

- **Hierarchy:** AI ⊃ Machine Learning ⊃ Deep Learning. ML improves with data exposure (Samuel, 1959); formally, a program learns from experience E w.r.t. task T and performance measure P if performance on T improves with E (Mitchell, 1998).
- **Key terms** (spam-filter example): training data, sample/instance, feature/attribute, performance measure. Common metrics: regression — RMSE, MAE, R²; classification — Accuracy, F1-score.
- **Three main types of ML:**
  - **Supervised learning** (labeled data): classification (e.g., spam filter) and regression (e.g., house price prediction). Key algorithms: k-NN, Linear/Logistic Regression, SVM, Decision Trees/Random Forests, Neural Networks.
  - **Unsupervised learning** (unlabeled data): clustering (K-Means, DBSCAN, HCA), anomaly detection (One-class SVM, Isolation Forest), dimensionality reduction (PCA, LLE, t-SNE), association rule learning (Apriori, Eclat).
  - **Reinforcement learning:** agents learn via rewards (e.g., robots learning to walk, AlphaGo Zero).
- **Neural networks:** inspired by biological neurons (dendrites, cell body, axon); ANNs (McCulloch & Pitts, 1943) and the Perceptron (Rosenblatt, 1958) — the Perceptron's inability to solve non-linear problems (e.g., XOR) contributed to the first AI Winter. A feedforward network has input/hidden/output layers; many hidden layers = Deep Learning.
- **Math behind a neuron (optional/advanced):** output `y = φ(x₁w₁ + x₂w₂ + x₃w₃ + b)`, where the linear part represents straight lines/hyperplanes and φ (a non-linear activation function) enables curved decision boundaries. Common activation functions: Sigmoid (`1/(1+e⁻ˣ)`, range 0–1), Tanh (`2/(1+e⁻²ˣ)−1`, range −1 to 1), ReLU (`max(0,x)`).

---

## Module 2 — AI Ethics & Safety

### Week 5: Ethics & Laws for AI Usage & Development

- **Why ethics/law matter — real-world harms:**
  - **AI psychosis:** a 2024 case where a man lost his marriage and over €100,000 believing an AI chatbot was sentient. In 2025 the Human Line Project documented 15 suicides, 90 hospitalizations, 6 arrests, and over $1M spent on AI-related delusional projects linked to chatbot sycophancy (Stanford research). NCMEC reports of generative-AI child exploitation rose from 4,700 (2023) to 1.5 million (2025).
  - **Autonomous weapons & cybersecurity risk:** Hawking and Hinton warnings about AI risk; Israel's AI-powered robot guns; concern over AI systems that write and run their own code ("killer robots").
  - **Impact on cognition:** an EEG-based study found LLM users showed weaker neural connectivity than a "brain-only" group over 4 months, performing worse at neural, linguistic, and scoring levels — alongside survey figures linking AI to laziness, privacy risk, and reduced decision-making.
  - **Deepfakes & fraud:** a 2024 Hong Kong case lost $25M to a deepfake video call impersonating a company's CFO and colleagues; a 2026 Singapore case used deepfakes of PM Lawrence Wong. "Deepfake-as-a-service" platforms (e.g., Haotian) enable real-time face-swapping that bypasses common verification methods.
- **Main ethical approaches:**
  - **Virtue Ethics** — emphasizes moral character (generosity, compassion, courage); fits pre-industrial/simple societies.
  - **Deontology** — emphasizes rule-following (school/company/national rules); illustrated with the "run a red light" dilemma.
  - **Consequentialism** — judges an action by its outcome (good vs. bad result), raising the question of how "good"/"bad" is determined.
- **The Golden Rule:** endorsed across 143 world faith leaders (1993 "Declaration Toward a Global Ethic"); expressed in many equivalent forms across traditions ("treat others as you would like to be treated").
- **"Good/bad for others"** framework, illustrated with Vietnamese Criminal Code Article 132 (failure to assist a person in peril), and a table of beneficial vs. questionable uses of big data (license-plate readers, facial recognition, GPS, healthcare, education, smart homes, law enforcement, retail, urban planning).
- **The Trolley Problem:** classic dilemma (lever-switching vs. pushing a person) used to explore act/omission and the difficulty of weighing good vs. bad outcomes, including a "both good and bad" example (publicly pressuring philanthropists over misused charity funds).

### Week 8: Ethics & Laws for AI (cont.)

- **Good for others is good for yourself:** research (Health and Retirement Study, ~13,000 participants) links ≥100 hrs/year volunteering to lower mortality risk and better well-being; framed via Newton's Third Law ("what goes around, comes around").
- **Laws for data analytics:** US sectoral privacy laws (HIPAA 1996, COPPA 1998, FACTA 2003) plus negligence law; EU GDPR (fairness, consent, right to access/correct data, accountability).
- **OECD Fair Information Practices (1980):** 8 principles — collection limitation, data quality, purpose specification, use limitation, security safeguards, openness, individual participation, accountability. Adopted by 38 countries.
- **AI governance models compared:**
  - **EU AI Act** — rules-based, risk-tiered: Unacceptable Risk (banned, e.g., manipulation/exploitation of vulnerable groups) and High Risk (strict compliance for recruitment, grading, critical infrastructure); fines up to 7% of global turnover or €35M.
  - **US Executive Order 14110** — innovation-driven; treats frontier AI as a national-security matter (mandatory reporting to Dept. of Commerce for large models), directs NIST to set voluntary standards that become mandatory for federal vendors. Signed by Biden (2023), rescinded by Trump (2025).
  - **Vietnam's Law on AI (No. 134/2025/QH15)** — Southeast Asia's first AI legal framework (effective 2026), EU-Act-like risk tiers (High / Medium / Low Risk based on potential harm or capacity to mislead users), and an ethical framework covering safety, human rights/non-discrimination, social well-being, and responsible AI research.
- **IRAC method** (Issue, Rule, Application, Conclusion) for analyzing legal problems, applied to two case studies:
  - **Target's "pregnancy-prediction" model (2012):** purchase-pattern-based targeted ads revealed a teen's pregnancy to her family before she disclosed it — raising personal-data-infringement concerns.
  - **Tesla Autopilot fatal crash (2016):** Autopilot failed to detect a trailer against a bright sky; NHTSA investigated; Tesla maintained Autopilot is a Level 2 "assist feature" requiring driver attention. Covers the SAE J3016 levels of driving automation (0 = no automation through 5 = full automation).
- **Explainable AI (XAI):** the shift from opaque "black box" models to models that can explain their own reasoning to end users (DARPA XAI initiative); example toolkits: Microsoft InterpretML, PyTorch Grad-CAM.

### Week 8 Tutorial: Golden Rule, Vietnam AI Law & AI Policy Making

- Provides the full text of Vietnam's Law on AI (No. 134/2025/QH15) in English and Vietnamese as reference material.
- **AI policy = Ethics + Laws in AI.** A good AI policy sets expectations for staff, supports consistent decisions, and aligns AI use with legal obligations. Template: Australian National AI Center's AI policy template (usable as-is or adapted).
- **Pre-publishing checklist:** the policy should state what AI can/cannot be used for, who approves higher-risk use cases, what data staff may input into AI tools, how misuse is reported, and when the policy will be reviewed.

---

## Module 3 — Applied AI

### Week 9: Build Your ML Models without Code (Orange Data Mining)

- **Orange Data Mining:** a free, open-source, no-code platform (University of Ljubljana, 1996) built on scikit-learn/NumPy/SciPy; real-time interactive widget updates; handles datasets up to ~100,000 rows. Workflows are built by connecting widgets (input on the left, output on the right); more widgets available via Add-ons or custom development.
- **Comparison with alternatives:** KNIME (free desktop, paid enterprise scaling, handles massive/big data), RapidMiner Studio (paid, strong for business analytics/AutoML), Weka (free, academic, Java-based).
- **Main steps of an ML project:** (1) look at the big picture, (2) get the data, (3) exploratory data analysis (EDA), (4) data preprocessing, (5) train & evaluate models, (6) fine-tune models, (7) test & analyze the solution, (8) launch, monitor & maintain the system.
- **Understanding the problem:** define the end goal, existing solution performance, candidate algorithms, relevant performance measures (RMSE, MAE), and data needs. Data sources: UCI ML Repository, Kaggle, AWS public datasets, Data Portals, Wikipedia's ML dataset list, r/datasets.

### Week 10 Tutorial: End-to-End ML Project & Hands-on Exercises

- Walkthrough of a complete end-to-end ML project in code (`DEMO_end_to_end_ml_project.py`).
- **Launching & maintaining an ML system:** write monitoring code to catch breakage/performance drops, perform regular human evaluation of outputs, monitor input quality (e.g., degraded sensor/camera data), retrain models regularly on fresh data, and save system snapshots for rollback.
- **Hands-on exercises** (build a regression project, using the demo code as a base): (1) dataset acquisition (≥5 features incl. ≥1 categorical, ≥1000 samples), (2) EDA (missing values, outliers, imbalance), (3) preprocessing pipelines for numeric and categorical data, (4) train ≥4 models (≥2 beyond those covered in lecture) and compare via r²/RMSE/MAE with k-fold cross-validation, (5) hyperparameter tuning via random search, (6) test the final model and discuss limitations/next steps.

### Week 11: AI for Work & Life (+ Course Wrap-up)

- **Multimedia + AI tools:** image upscaling (Upscale.media, Imgupscaler), object removal (cleanup.pictures, Adobe Firefly), recoloring (Aiease), image generation (Gemini/Nano Banana, ChatGPT, PicLumen, Canva), music generation (Suno, Udio, ElevenLabs, Aiva), video generation (BytePlus Lumina, Adobe Firefly, Gemini, CapCut).
- **No-code deep learning:** Google's Teachable Machine.
- **Task automation:** Zapier, n8n, Diaflow — demoed via a Zapier "Zap" that logs Facebook comments to Google Sheets, with an optional AI step to extract structured info (name/address/phone), and a Zapier-based RMIT chatbot restricted to answering only from rmit.edu.vn.
- **RAG chatbots** (Retrieval-Augmented Generation): answer from a defined knowledge base (PDFs, websites); tools: Chatbase, Voiceflow. Demoed building an RMIT-scoped chatbot in Chatbase (website + PDF sources, constrained system prompt) and embedding its widget script into an app built with **Google AI Studio**.
- **Other AI tools:** NotebookLM (chat with your documents); running AI models locally via Ollama, LM Studio, AnythingLLM, GPT4All (pros: security, cost efficiency, offline use; cons: setup complexity, maintenance burden); AI coding tools (Claude Code, Google Antigravity, Cursor, GitHub Copilot).
- **AI agents:** autonomous platforms that execute multi-step tasks across data/resources with minimal human input (e.g., OpenClaw, Hermes Agent, Claude Cowork) — flagged with a warning about security vulnerabilities and the risk of "cognitive decay" from outsourcing critical thinking.
- **Course wrap-up — what the course aimed to build:**
  - *Knowledge:* AI development approaches; history & future of AI; core AI concepts; key ethics approaches.
  - *Tech skills:* developing ML projects (no-code and coding); writing ML project reports.
  - *Soft skills:* problem-solving; teamwork & presentation; understanding and applying ethical principles.

---

## Tutorials — Other Key Content

### Week 2 Tutorial: Assignment Info & AI Applications

- Explored AI applications across fields: biology, biomedicine/chemistry, robotics, automotive, human biosciences, healthcare, engineering, computer science, the arts, gaming, biosciences & food, sustainability.
- **Worked example — self-driving cars (Level 5):** addresses safety, cost-effectiveness, and accessibility; relies on sensor data (radar/cameras) and deep learning/reinforcement learning/generative AI for lane-keeping, speed control, etc.; pitfalls include job loss, high cost, liability in accidents, and hacking/terrorism risk.
- Further field examples: AI in IT (code generation, bug fixing), psychology (mental health diagnosis, personalized therapy), engineering (smart grids, predictive maintenance), food tech (personalized nutrition), agriculture (precision farming), smart cities (traffic management).

### Week 3 Tutorial: Prompt Engineering & Custom AI Chatbots

- **PARTS framework** for prompt writing: **P**ersona, **A**im, **R**ecipients, **T**heme, **S**tructure. Template: *"You are a [Persona]. [Aim and Recipients]. [Theme and Structure]."*
- **Creating chatbots:** using Poe or RMIT Val with system prompts built on PARTS; demoed with an RMIT Marketing Officer persona restricted to official sources.
- **Prompt layers for building AI "Personas":** System (overall framework), Context (task-specific details), Role (consistent identity) — from broad foundation to specific behavior.
- **Detailed persona-prompt template (worked example, "TRANSLO"):** structured with Markdown headings — `#Purpose`, `#Context` (with `##Variables` and `##Conditions`), `#Output` (`##Response format`), `##Constraints`, and `#Interaction` (a numbered conversational workflow) — illustrating how Markdown structure improves an LLM's ability to follow complex, multi-step instructions.
