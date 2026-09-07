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
*(paste sections here)*

---

## Week 8 — Ethics & Laws for AI (cont.) / Policy Making
*(paste sections here)*

---

## 🔑 Master List of Terms to Define
*(fill in as you go — e.g. intractable problems, Moravec's paradox, expert systems, brittle systems...)*

-
-
-