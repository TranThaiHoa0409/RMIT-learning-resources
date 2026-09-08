# 📘 Foundations of AI — Exam Review Compilation
*(COSC2968/COSC3053 — Assignment 2 prep)*

## Week 1 — Course Info & AI Approaches

### 1. Why Learn AI?

- AI/ML consistently ranks among the top in-demand tech skills/jobs (Indeed, 2023–2026)
- Vietnam's National Strategy on R&D and AI (target 2030):
  - Be in the world's top 50 countries for AI research, development & application
  - Set up 10 renowned AI centers in the region
  - Develop 50 open, linked, connected datasets across economic/social sectors
- Vietnam IT salary/recruitment data (ITviec 2025–2026 report) shown to illustrate market demand for AI-related skills

**AI for Better Lives — example projects**
- Intelligent Stick for Visually Impaired People (Muhammad Farooq et al.)
- Versatile Stick for Visually Impaired People (University of Medicine and Pharmacy at HCMC)
- Finger-mounted Reading Device for the Blind (MIT Media Lab)
- Agricultural Price Prediction (Tran et al., in progress)

---

### 2. AI Approaches (Main reference: Russell & Norvig, *Artificial Intelligence: A Modern Approach*, 2020)

#### What is Artificial Intelligence?
An AI agent (software or hardware) is an intelligent entity created by humans — "artificial" meaning not naturally occurring.

**Key question:** How can we tell if something is "intelligent"?
Two dimensions define the four classic approaches to AI:
1. **Characteristic of intelligence** — Humanly vs. Rationally
2. **How it manifests** — Think vs. Act

| | Humanly | Rationally |
|---|---|---|
| **Act** | Act Humanly | Act Rationally |
| **Think** | Think Humanly | Think Rationally |

#### Act Humanly — The Turing Test
- Proposed by Alan Turing (1950)
- A computer passes if a human judge, questioning via text, cannot distinguish its responses from a human's
- **Example:** Eugene Goostman, a chatbot mimicking a 13-year-old, fooled 33% of judges in a 2014 Turing Test by using humor/quirks to dodge questions rather than demonstrating real understanding
- **Takeaway:** The Turing test can be "gamed" with tricks rather than genuine intelligence — later slides test this informally with Google Gemini and Microsoft Copilot (May 2024)

#### Think Humanly
Three ways to study human thought, to model it computationally:
1. **Introspection** — observing your own mind/thoughts in real time
2. **Psychological experiments** — observing external behavior (e.g., Stanford Prison Experiment — showed how Authority & Anonymity can turn ordinary people evil quickly)
3. **Brain imaging** — observing neural activity directly (MRI, EEG)

**Bonus theme — Introspection & personal growth:**
- Introspection isn't only useful for AI development; it's essential for self-awareness and emotional regulation
- Illustrated with a Japanese folktale: *"Do not act or speak when angry."*
  - A samurai spares a debtor fisherman after being reminded not to act in anger.
  - Later, the samurai almost kills his wife and mother in a jealous rage, but recalls the fisherman's advice just in time, avoiding tragedy.
  - Moral: recognizing an angry thought early (introspection) lets you stop it before it becomes words or actions — like catching a fire while it's still small.

#### Think Rationally
The "right thinking" approach — solving problems through logic:
- **Logicism (strict logic):** translates problems into formal logical notation; requires strict true/false certainty.
  Example: `IF Obstacle_Exists THEN Brake`
- **Fuzzy Logic & Probability Theory:** allows rigorous reasoning under uncertainty.
  Examples:
  `IF Obstacle_Fairly_Close THEN Brake70%`
  `IF Probability_Of_Hitting > 10% THEN Brake20%`
- *Note:* Deep Learning and LLMs are fundamentally built on Probability Theory.

#### Act Rationally
- A rational agent's goal: achieve the best expected outcome — perceive and respond to environment properly, adapt to change.
- **Advantages over other approaches:**
  1. More general than "Thinking Rationally" — logical reasoning isn't the only path to a good outcome (e.g., reflexively jerking your hand from a hot stove beats slow logical deduction).
  2. Can avoid the flaws of human thought/behavior seen in "Humanly" approaches.

---

## Week 2 — AI: Past and Present

**This week's question:** How was AI born & raised?

### 1. A Brief History of AI

Timeline of "waves" and "winters" (Main ref: Wikipedia, Russell 2020):

| Period | Era | Description | Key milestones |
|---|---|---|---|
| **1956** | Dartmouth Workshop | Formal inception of AI as an academic discipline | — |
| **1950s–60s** | 1st Wave — Great Expectations | Focused on tasks seen as indicative of human intelligence (games, maths) | Lisp (McCarthy, 1958), Checkers program (Samuel, 1959), Perceptron machines (Rosenblatt, 1957–62) |
| **1970s** | 1st Winter — A Dose of Reality | AI faced critiques & funding cuts | Problems: limited computing power, intractable problems, Moravec's paradox |
| **1970s–80s** | 2nd Wave — Expert Systems | Programs answering domain-specific questions using logical rules from expert knowledge | DENDRAL (Buchanan et al., 1969), MYCIN (Shortliffe et al., 1972), XCON (McDermott, 1978) |
| **1980s–90s** | 2nd Winter — Brittle Systems | Expert systems proved difficult to build/maintain for complex domains | — |
| **Since 1990s** | 3rd Wave — Deep Learning | More data, faster compute, and better learning techniques revived neural networks | Deep Blue (IBM, 1997), Watson (IBM, 2011), AlphaGo (Google, 2015), ChatGPT (OpenAI, 2022) |

### 2. State-of-the-Art AI Applications

**AI in Language & Communication**
- Gemini 3.5 Live Translate (Google, June 2026) — near real-time speech-to-speech translation across 70+ languages
- Microsoft 365 Interpreter Agent — real-time speech translation in Teams
- Transcribe Glass — wearable live transcription device

**AI in Computer Vision**
- Google Lens (2017) — visual search/recognition from camera input
- Google Translate — camera-based real-time text translation
- Be My Eyes — AI-assisted visual support for blind/low-vision users
- See & Spray (Blue River Technology) — AI agricultural robot for precision weed spraying
- Food quality inspection (Solomon AI + 3D Vision), food safety tracking (Sodexo India), AI food scanners estimating nutrition (NBC News)

**AI for a Better World**
- Hungry Root — AI to cut food waste
- Flood forecasting & sustainable flight routing
- AI-enhanced bushfire detection
- Preventing train derailments (US) — Norfolk Southern automated inspection portals
- AI-driven typhoon forecasting (Taiwan) — CWA + NVIDIA (CorrDiff), forecast Typhoon Koinu's trajectory 5 days ahead

**AI in Banking**: Fraud detection, automation, etc.

**Real or "Fake"? — Generative AI & Deepfakes**
- StyleGAN (2019) & BigGAN (2018) — photorealistic AI-generated faces/images (try: whichfaceisreal.com)
- Discussion prompt: Can AI "understand" image content, or just generate convincing pixels?
- Deepfake example: fabricated "Obama" video (BBC News)

**AI in Music**
- Blind listening test: human-composed (John Williams, Hans Zimmer) vs. AI-composed (AIVA)

**AI in Machine Control**
- Controlling Assistive Robots with Learned Latent Actions (Stanford, 2019)

---

## Week 3 — In the Future with AI & Data Science

### 1. Human vs AI & How Far Can AI Go? (condensed)
- **Landmark AI milestones:**
  - Chess: Deep Blue (IBM) beat Garry Kasparov, 1997
  - Jeopardy!: IBM Watson beat the two top champions, 2011
  - Atari: DeepMind's Deep Q-learning mastered games from raw pixels via reinforcement learning
  - Go: AlphaGo beat Ke Jie (2017); AlphaGo Zero learned purely via self-play (no human data), beat AlphaGo 100–0
  - StarCraft II: AlphaStar beat pro player MaNa 5–0 (2019)
  - MuZero: masters games without even being told the rules
  - Debate: Project Debater vs. Harish Natarajan (2019) — rare case of a human win
  - Also: surgical robotics, AI assistants/companions (Google Project Astra, Woebot, etc.) — raises "AI companions: cure or danger?"
  - AI accelerating science: NLP model predicted materials years before discovery (Tshitoyan et al., 2019); AlphaTensor improved on Strassen's matrix-multiplication algorithm; AlphaEvolve (DeepMind)
- **How far can AI go? (philosophical reflection):**
  - Does "AI" have firm boundaries? What is intelligence/"artificial" vs. "natural"? "What am I?" (Buddhist Skandhas, Descartes' *"I think, therefore I am"*)
  - Predicting AI's far future may require philosophy ("wisdom"), not just technology
  - Key quotes: **Hawking** — AI could be the best or worst thing to happen to humanity; **Hinton** — hard to stop bad actors from misusing AI

### 2. Introduction to Data Science

#### What is Data Science?
- "An interdisciplinary academic field that aims at extracting knowledge and insights from data." — Wikipedia
- "Combines math and statistics, specialized programming, artificial intelligence (AI) and machine learning to uncover insights hidden in an organization's data." — IBM
- Conceptually: **Data + Machine Learning**

#### What is Data?
- "A collection of discrete or continuous values that convey information, or sequences of symbols that may be further interpreted formally." — Wikipedia
- Comes in many forms: tabular, text, audio, visual (images/video)

#### Common Data Types

| Type | Description | Typical Extensions | Common Tasks |
|---|---|---|---|
| **Tabular** | Organized into rows & columns (e.g., real-estate listings dataset shown: property type, price, area, district, etc.) | `.csv`, `.xlsx` | Feature/sample-based analysis |
| **Textual** | Collections of writing: emails, social posts, website content; large collections = "text corpus" | `.txt` | Information Retrieval (IR), Natural Language Processing (NLP), Information Extraction (IE) |
| **Audio** | Represents sound: speech, voice, songs | `.wav`, `.flac`, `.mp3` | Speech recognition, voice recognition, environmental sound recognition, text-to-speech |
| **Image** | 2D or 3D visual data | `.jpeg`, `.png`, `.tiff` | Image classification, image retrieval, object recognition, segmentation |

- Example datasets referenced: CIFAR-10, MNIST (images), CheXpert (chest X-rays), Waymo Dataset (autonomous-driving object tracking/LiDAR)

---

## Week 4 — Introduction to Machine Learning

### 1. Definitions of Machine Learning

**Relationship to AI:** ML is a subfield of AI (alongside logic-based AI, search, etc.). Deep Learning (DL) is a subfield of ML that relies on Artificial Neural Networks, inspired by the human brain. Reinforcement Learning is another related branch.

**Classic definitions:**
- **Samuel (1959):** "Machine learning is a field of study that gives computers the ability to learn from experience without being explicitly programmed."
- **Mitchell (1998):** "A computer program is said to learn from experience E with respect to some task T and some performance measure P, if its performance on T, as measured by P, improves with experience E."

**Applying E/T/P — worked examples:** 
- *Spam filter:* E = watching the user label emails as spam/not spam
- *AI coding assistant:* T = the task the system performs (e.g., analyzing codebases / generating code suggestions)
- *AI-designed plant-based meat:* P = objective health ratings (Health Star Rating) and/or subjective sensory panel scores

**Key ML vocabulary** : Training data (training set), Sample/data point/instance, Feature/attribute, Performance measure

**Popular performance measures:**
- *Regression:* RMSE, MAE, R² score
- *Classification:* Accuracy, F1-score

### 2. Types of Machine Learning (by level of human supervision)

**Supervised Learning** — uses **labeled data**
- Classification (e.g., spam filtering, image classification), Regression (e.g., housing price prediction, weather forecasting)
- Key algorithms: k-NN, Linear Regression, Logistic Regression, SVM, Decision Trees & Random Forests, Neural Networks

**Unsupervised Learning** — uses **unlabeled data**
- Clustering (K-Means, DBSCAN, HCA) — e.g., news article clustering, social network analysis, market segmentation
- Anomaly detection (One-Class SVM, Isolation Forest)
- Dimensionality reduction (PCA, LLE, t-SNE) — reduces dimensions while preserving essential structure
- Association rule learning (Apriori, Eclat) — "if-then" patterns (e.g., bread → butter)

**Reinforcement Learning** — agents learn via **rewards**. Examples: robots learning to walk, AlphaGo Zero.

**Supervised vs. unsupervised — quick quiz examples:**
- Spam filter with labeled emails → supervised
- Grouping news articles about the same story → unsupervised (clustering)
- Grouping customers into market segments → unsupervised (clustering)
- Classifying diabetic/non-diabetic from labeled data → supervised
- Grouping people into personality types with no predefined labels → unsupervised

<br>

> 💡 **Review tip — 4 basic ML types :**
> - **Classification:** predicts discrete labels/classes (e.g., spam vs. not spam)
> - **Regression:** predicts a continuous numeric value (e.g., house prices)
> - **Clustering:** groups unlabeled data by similarity (unsupervised)
> - **Reinforcement Learning:** agent learns via trial-and-error rewards/penalties

### 3. Neural Networks: What's Inside the Box?

**History:**
- ANNs first introduced in 1943 (McCulloch & Pitts, "A Logical Calculus of Ideas Immanent in Nervous Activity")
- 1958: Frank Rosenblatt's **Perceptron** — first trainable neural network; its inability to solve non-linear problems (e.g., XOR) contributed to the **1st AI Winter** (late 1960s)
- ANNs are the foundation of the current (Deep Learning) wave of AI

**Biological inspiration:** artificial neurons loosely modeled on biological neurons — inputs, weighted connections, activation/output signal.

**Structure:** A feed-forward network with many hidden layers = **Deep Neural Network** → "Deep Learning." An ANN is a general function approximator.

**The Math Behind a Neuron (advanced/optional):**
`y = φ( x₁w₁ + x₂w₂ + x₃w₃ + b )`
- Weighted sum (`x·w + b`) = **linear function** (straight lines/planes/hyperplanes)
- **φ (activation function)** = non-linear function enabling curves/curved surfaces, not just straight lines

**Popular activation functions:** Sigmoid, tanh, ReLU

**Hands-on resource:** playground.tensorflow.org

---

## Week 5 — Ethics & Laws for AI

### 1. Why Do Ethics & Law Matter in AI?

**Cautionary real-world cases (condensed):**
- **"AI psychosis":** A Dutch IT consultant (2024) lost his marriage and over €100,000 after believing an AI chatbot was a sentient business partner/romantic interest.
- **Human Line Project (2025):** documented 15 suicides, 90 hospitalizations, 6 arrests, over $1M spent — all tied to AI-related delusional episodes.
- Stanford research: chatbots tend to be highly **sycophantic**, especially risky for people vulnerable to delusional thinking.
- **Generative-AI CSAM reports to NCMEC** rose sharply: ~4,700 (2023) → ~67,000 (2024) → ~1.5 million (2025).
- MIT study (Kosmyna et al., 2025): LLM-assisted essay writers performed worse than a "brain-only" group after 4 months — "**cognitive debt**."

**Expert warnings:**
- **Stephen Hawking:** AI "will either be the best thing that's ever happened to us, or... the worst thing... it very well may be the last thing."
- **Geoffrey Hinton:** "It is hard to see how you can prevent the bad actors from using it for bad things."
- Reference case: Microsoft's **Tay chatbot** (rapidly learned to produce offensive content).

**Deepfake fraud cases:**
- **2024, Hong Kong:** finance employee tricked into transferring **$25 million** via a video call where every participant (incl. the "CFO") was a deepfake.
- **2026, Singapore:** a business professional lost **$4.9 million** to a scam using deepfakes of Singapore PM Lawrence Wong and other officials.
- "**Deepfake-as-a-service**" platforms (e.g., "Haotian") — GAN-based real-time face-swapping, marketed as easy ("one-button"), able to defeat liveness checks.

### 2. Ethics: Principles & Practical Usage

**Three Main Approaches in Ethics**

| Approach | Core Idea | Notes |
|---|---|---|
| **Virtue Ethics** | Emphasizes moral character/virtues (generosity, loving-kindness, courage, compassion) | Fits pre-industrial/simpler societies |
| **Deontology** | Action is right if it follows accepted **rules** (school rules, company regs, laws) | Preserves societal stability. Ex: is running a red light right or wrong? |
| **Consequentialism** | Action is right if its **result** is good | Same red-light example: right if it saves a life, wrong if it causes an accident. Open problem: how do we judge "good"? |

**The Golden Rule**
- Near-universal ethical principle across human traditions.
- Endorsed by **143 leaders** of the world's major faiths (1993 "Declaration Toward a Global Ethic").
- Core idea: *treat others as you would want to be treated; do not do to others what you would not want done to you.*

**"Bad for others is bad; good for others is good" — simplified practical framework**
- Running a red light to let an ambulance pass → **good** (helps others)
- Vietnamese Criminal Code (Art. 132): failing to help someone in mortal danger when capable → punishable — legal duty tied to "harm to others"
- Price-gouging on medical masks during COVID-19 → **bad**
- General pattern: Killing, stealing, infidelity, lying, drunkenness → bad; saving lives, helping those in need, faithfulness, hard work, honesty → good

**When outcomes are both good and bad**
- Compare by **breadth** (how many affected) and **depth** (how severely affected).
- **Trolley Problem:** save 5 by (1) switching a lever → kills 1 instead of 5, or (2) pushing 1 person off a bridge → same numeric tradeoff but more morally uncomfortable (direct physical harm).
- Closing reflection: ethics frameworks focus on impact "for others" — but what about impact on **oneself**?

> 💡 **Exam tip:** Be ready to classify a scenario under Virtue Ethics / Deontology / Consequentialism, and to apply the Golden Rule + "good/bad for others" framework to a novel case.

---

## Week 8 — Ethics & Laws for AI (cont.) / Policy Making

### 1. Recap — Golden Rule

- **Golden Rule (summary):** *Good for others is good for yourself* (and vice versa).
- Parallels across domains:
  - **Newton's 3rd Law:** "To every action there is always opposed an equal reaction."
  - **Karma / reciprocity idiom** across cultures — EN: *You reap what you sow*; VN: *Gieo nhân nào, gặt quả nấy* (also DE/FR/ES/ZH/TH/LO equivalents).
- **Supporting evidence:** Dr. Eric S. Kim (Harvard) — study of ~12,998 U.S. adults found volunteering ≥100 hrs/year (vs. 0) was linked to lower mortality risk, better physical functioning, improved psychosocial outcomes (Kim et al., 2020, *American Journal of Preventive Medicine*).

### 2. Laws in Data Science & AI

**Regional/sector laws:**
- **US:** sector-specific privacy laws — HIPAA (1996), COPPA (1998), FACTA (2003); plus negligence laws.
- **EU — GDPR:** data analytics must be fair; permission required to process data; individuals can access/correct their data; accountability principle.
- **OECD Fair Information Practices (1980):** Guidelines on Protection of Privacy & Transborder Flows of Personal Data, adopted by 38 countries; **8 principles**.

**AI Governance Frameworks — two models:**

| Region | Approach | Instrument |
|---|---|---|
| EU | Rules-based regulatory | Artificial Intelligence Act |
| US | Innovation-driven | Executive Order No. 14110 |

- **EU AI Act** — world's first comprehensive AI legal framework; risk-tiered:
  - **Unacceptable Risk (Banned):** manipulates individuals without awareness, or exploits vulnerable groups (children, persons with disabilities).
  - **High Risk (Strictly Regulated):** context-based (recruitment, grading, critical infrastructure); must prove safety/transparency/bias-mitigation compliance before market entry.
  - **Penalty:** up to **7% of global annual turnover or €35 million**, whichever is higher.
- **US Executive Order 14110** — signed by Biden (2023), **rescinded by Trump (2025)**. Relies on existing laws + standards (NIST). National-security framing: frontier developers must report training/red-teaming to Dept. of Commerce. Compliance voluntary in general, **mandatory for federal vendors**.
- **Vietnam AI Law (No. 134/2025/QH15)** — effective 2026, **first legal AI framework in Southeast Asia**; similar risk-tiered structure to EU:
  - **High Risk:** significant harm to life, health, public interest, national security.
  - **Medium Risk:** can mislead/influence users unaware they're interacting with AI.
  - **Low Risk:** everything else.
  - Ethical framework: ensure safety; respect human rights/fairness/non-discrimination; promote well-being & sustainable development; encourage social responsibility in AI R&D.

### 3. IRAC Method & Real Cases

**IRAC (legal analysis framework):** **I**ssue → **R**ule → **A**pplication → **C**onclusion.

**Case Study — Target "Pregnancy Prediction" (US, 2012)**
- Target's analytics team built a pregnancy-prediction model from purchase patterns (unscented lotion, cotton balls, certain vitamins).
- Accurate enough to identify a teenage girl's pregnancy **before her own father knew** (he complained after she received baby/crib coupons).
- Core question: **Was personal data infringed?**

**Case Study — Tesla Autopilot Fatal Crash (2016)**
- Joshua Brown died when his Tesla Model S (Autopilot on) crashed into a tractor-trailer turning left on a Florida highway.
- Cause: Autopilot failed to distinguish the trailer's white side against a bright sky — didn't brake, went under the trailer.
- NHTSA opened a federal investigation; Tesla's position: Autopilot is a Level-2 "assist feature" — driver remains responsible.
- Core question: **Whose fault — Tesla or the driver?**

**Explainable AI (XAI)**
- DARPA vision: ML systems should explain their rationale via human-computer interfaces that make model internals understandable.
- Tools: Microsoft InterpretML, Grad-CAM (PyTorch explainability).
- Response to the "black box" problem.

### 4. Key Takeaways (exam-relevant)
1. Golden Rule ≈ reciprocity ethics, cross-culturally universal, backed by empirical well-being research.
2. AI/data laws differ by region: **US** = sector-specific privacy laws; **EU** = GDPR + risk-tiered AI Act; **Vietnam** = new risk-tiered AI Law (134/2025/QH15), modeled partly on the EU approach.
3. Two governance philosophies: **EU = rules-based**, **US = innovation-driven/voluntary-but-market-enforced**.
4. **IRAC** = standard method for AI-related legal/ethical case analysis.
5. Target & Tesla cases illustrate the tension between data-driven personalization/automation and privacy/accountability.
6. **XAI** addresses the "black box" problem — making AI decisions interpretable.

---

## Week 11 — AI for Work & Life

### 1. Multimedia + AI
- **Image:** upscaling (upscale.media, imgupscaler.com); object removal via brush (cleanup.pictures, Adobe Firefly) or prompt (chatgpt.com, gemini.google.com/app); color-changing (aiease.ai/app/ai-recolor); generation (gemini.google.com "Nano Banana", chatgpt.com, piclumen.com, canva.com/ai-image-generator; also Adobe Photoshop's built-in generator)
- **Music:** suno.com, udio.com, elevenlabs.io, aiva.ai
- **Video:** ai.byteplus.com/lumina, Adobe Firefly, Gemini video generation, CapCut

### 2. Build Deep Learning Models (No-Code)
- **Teachable Machine** (teachablemachine.withgoogle.com) — no-code DL training in the browser.
- Core idea: training a classifier = collecting **labeled examples per class**; works the same way whether input is **image** (e.g., "Me" vs. "Bottle" vs. "Phone") or **audio/voice command** (e.g., "Open door" vs. "Open windows") — the tool handles feature extraction & training automatically.

### 3. Task Automation
- **No-code tools:** zapier.com, n8n.io, diaflow.io
- Core concept: a **"Zap"** connects a **trigger** (e.g., new Facebook comment) → optional **AI step** (classify/generate data) → **action** (e.g., log to Google Sheet).
- **Chatbot directive structure** (Zapier demo): **Objective** (role + allowed scope) + **Style** (tone) + **Other Rules** (language, topic restriction) — mirrors the **PARTS** prompt framework from Week 3.

### 4. Other AI Tools

**RAG Chatbots**
- **RAG (Retrieval-Augmented Generation):** answers using a **specific knowledge base** (own PDFs/websites) instead of only general training knowledge.
- Use cases: sales/marketing chatbots, tailored "personal experts." Tools: chatbase.co, voiceflow.com
- Build workflow: create agent → add **Sources** (website + files) → write **system prompt** (persona, tone, language, and a hard rule: *only use provided sources, don't invent info*) → **deploy** via embeddable chat-bubble widget.
- Testing: probe with in-scope questions (should answer from sources) and out-of-scope questions (should correctly decline).

**Google AI Studio** (aistudio.google.com) — build simple apps/websites from natural-language prompts ("prompt-to-app"); iterate via follow-up edit prompts.

**NotebookLM** (notebooklm.google.com) — research & "chat" with your own uploaded documents; a document-centric RAG use case.

**Running AI Models Locally**

| Pros | Cons |
|---|---|
| Security (less data-breach risk), Cost efficiency (no API/cloud fees), Offline access | Setup complexity (self-host), Maintenance cost (slower updates, IT support) |

Tools: Ollama, LMStudio, AnythingLLM, GPT4All

**AI Coding Tools** — Claude Code, Google Antigravity, Cursor, GitHub Copilot (write/understand/debug code faster)

**AI Agents**
> ⚠️ **Exam-relevant warning:** AI agents may expose systems to **severe security vulnerabilities** and cause **cognitive decay** by outsourcing critical human thinking.
- Definition: autonomous platforms executing **complex, multi-step tasks** across local data/resources with **minimal human intervention**.
- Examples: OpenClaw, Hermes Agent, Claude Cowork

### 5. Course Wrap-Up (what the course covered)
- **Knowledge:** AI approaches; history & future of AI; core AI concepts; ethics approaches.
- **Tech skills:** ML projects (no-code & coding); writing ML reports.
- **Soft skills:** problem-solving; teamwork & presentation; applying ethical principles.

---

## 🔑 Master List of Terms to Define
*(fill in as you go — e.g. intractable problems, Moravec's paradox, expert systems, brittle systems...)*

-
-
-