# COSC2968/COSC3053 – Foundations of Artificial Intelligence
## Week 3: In the Future with AI & Data Science

**This week's questions:** How far can AI go? What is Data Science?

---

## 1. Human vs AI — Landmark AI Milestones

*(Based on Bernard Marr, "Man vs. Machine: The 6 Greatest AI Challenges")*

- **Chess — Deep Blue vs Garry Kasparov (1996–97):** Kasparov won in 1996; IBM's Deep Blue (able to evaluate ~200 million positions/sec) beat him in 1997.
- **Jeopardy! — IBM Watson (2011):** Watson beat Ken Jennings and Brad Rutter, the two most successful Jeopardy champions (combined $5M in winnings). Watson trained on 100 games against past winners, won $1M first prize, and IBM donated 100% of it to charity.
- **Atari — Deep Q-learning (DeepMind):** A convolutional neural network using reinforcement learning mastered multiple Atari games from raw pixels alone, beating humans in several.
- **Go — AlphaGo vs Ke Jie (2017):** AlphaGo defeated the world's top Go player.
- **AlphaGo Zero:** Learned Go purely from self-play with no human data, becoming (arguably) the strongest Go player ever. Note: Go has ~2×10¹⁷⁰ legal board positions — far more than the ~10⁸⁰ atoms in the observable universe. It beat the previous champion-defeating AlphaGo 100–0.
- **StarCraft II — AlphaStar vs MaNa (2019):** Beat professional player Grzegorz "MaNa" Komincz 5–0.
- **MuZero:** Masters games without even being told the rules in advance.
- **Debate — Project Debater vs Harish Natarajan (2019):** IBM's AI debated a world-champion human debater (topic: "We should subsidize preschools"); Harish won by audience vote — a rare human win in this list.
- **Surgical robotics:** Johns Hopkins' Surgical Robot Transformer demo.
- **AI assistants/companions:** Google Project Astra, π0 (pi-zero, a general-purpose robot foundation model), Woebot (CBT chatbot), an AI psychologist chatbot proposed for Australia's health system, and other mental-health chatbots (EarKick, Wysa, Youper, and open-source projects). Raises the question: **AI companions — cure or danger?**

**Discussion prompt:** What do you think about AI models that defeat humans using zero human knowledge (e.g., AlphaGo Zero)?

### So, what can't AI do, really?
- AI can even accelerate scientific discovery: an unsupervised NLP model mined old materials-science abstracts (pre-2009) and "predicted" thermoelectric materials years before they were actually discovered in the literature (e.g., CuGaTe2 in 2012, ReS2 in 2016, CdIn2Te4 in 2017) — Tshitoyan et al., *Nature* (2019).
- **AlphaTensor** (built on AlphaZero) discovered a new matrix-multiplication algorithm improving on Strassen's 50-year-old algorithm — 76 multiplications vs. the prior best of 80 (Fawzi et al., *Nature*, 2022).
- **AlphaEvolve** (DeepMind) — further AI-driven algorithm discovery.

---

## 2. How Far Can AI Go? (Philosophical reflection)

- Does "AI" even have firm boundaries — what counts as AI and what doesn't?
- **What is intelligence?** What is "artificial" vs. "natural"?
- **What am I?** — referencing Buddhist philosophy (the Skandhas/Five Aggregates: the factors said to constitute a sentient being) and the Dhammapada ("All that we are is the result of what we have thought…"), alongside Descartes' *"I think, therefore I am."*
- Predicting AI's far future may require philosophy ("wisdom"), not just technology.
- Notable quotes:
  - **Stephen Hawking:** AI "will either be the best thing that's ever happened to us, or it will be the worst thing... it very well may be the last thing."
  - **Geoffrey Hinton:** "It is hard to see how you can prevent the bad actors from using it for bad things."
- This leads into **Module #2: Ethics & Laws for AI** — flagged as very important (Microsoft's AI training program on edX treats ethics/law as a separate course).

---

## 3. Introduction to Data Science

### What is Data Science?
- "An interdisciplinary academic field that aims at extracting knowledge and insights from data." — Wikipedia
- "Combines math and statistics, specialized programming, artificial intelligence (AI) and machine learning to uncover insights hidden in an organization's data." — IBM
- Conceptually: **Data + Machine Learning**

### What is Data?
- "A collection of discrete or continuous values that convey information, or sequences of symbols that may be further interpreted formally." — Wikipedia
- Comes in many forms: tabular, text, audio, visual (images/video)

### Common Data Types

| Type | Description | Typical Extensions | Common Tasks |
|---|---|---|---|
| **Tabular** | Organized into rows & columns (e.g., real-estate listings dataset shown: property type, price, area, district, etc.) | `.csv`, `.xlsx` | Feature/sample-based analysis |
| **Textual** | Collections of writing: emails, social posts, website content; large collections = "text corpus" | `.txt` | Information Retrieval (IR), Natural Language Processing (NLP), Information Extraction (IE) |
| **Audio** | Represents sound: speech, voice, songs | `.wav`, `.flac`, `.mp3` | Speech recognition, voice recognition, environmental sound recognition, text-to-speech |
| **Image** | 2D or 3D visual data | `.jpeg`, `.png`, `.tiff` | Image classification, image retrieval, object recognition, segmentation |

- Example datasets referenced: CIFAR-10, MNIST (images), CheXpert (chest X-rays), Waymo Dataset (autonomous-driving object tracking/LiDAR)

---

## Next Week's Topic
**Introduction to Machine Learning**
