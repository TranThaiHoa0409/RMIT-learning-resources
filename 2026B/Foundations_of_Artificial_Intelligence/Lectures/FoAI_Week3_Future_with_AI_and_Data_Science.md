# FoAI Week 3 – In The Future with AI & Data Science (Lecture)

**COSC2968 - COSC3053 — Foundation of Artificial Intelligence**

## 1. Human vs AI

### 1.1 AI Milestones: Machine vs Human Champions

#### Chess: Deep Blue vs Garry Kasparov (1997)

World Champion chess player Garry Kasparov competed against artificial intelligence twice. In the first chess match-up between machine (IBM Deep Blue) and man (Kasparov) in 1996, Kasparov won. The next year, Deep Blue was victorious.

Garry Kasparov playing against Deep Blue, 1997 - *Photo credit: Peter Morgan/Reuters*

#### Jeopardy!: IBM Watson vs Ken Jennings & Brad Rutter (2011)

In 2011, IBM Watson took on Ken Jennings and Brad Rutter, two of the most successful contestants of the game show *Jeopardy!*, who had collectively won $5 million during their reigns as Jeopardy champions. To prepare, Watson played 100 games against past winners. Watson won the first prize of $1 million; IBM donated 100% of its winnings to charity.

(Video: *Watson and the Jeopardy! Challenge* — [IBM Research](https://www.youtube.com/watch?v=P18EdAKuC1U))

#### Atari Games: Deep Q-Learning (DeepMind)

The breakthroughs in computer vision and speech recognition allowed the innovators at DeepMind Technologies to develop a convolutional neural network for reinforcement learning, enabling a machine to master several Atari games using only raw pixels as input, and in a few games achieve better results than humans.

(Video: *Deep Q-learning playing Atari* — [Two Minute Papers](https://www.youtube.com/watch?v=V1eYniJ0Rnk))

#### Go: AlphaGo vs Ke Jie (2017) and AlphaGo Zero

AlphaGo defeated Ke Jie in 2017. (Video: [bbc.com](https://www.bbc.com/news/av/world-asia-china-40073960/alphago-computer-defeat-painful-for-chinese-go-prodigy))

The number of legal board positions in Go is approximately 2×10¹⁷⁰, which is far greater than the number of atoms in the observable universe (about 10⁸⁰).

**AlphaGo Zero**, the latest evolution of AlphaGo, is arguably the strongest Go player in history. Previous versions of AlphaGo initially trained on thousands of human amateur and professional games to learn how to play Go. AlphaGo Zero skips this step and learns to play simply by playing games against itself, starting from completely random play. In doing so, it quickly surpassed human level of play and defeated the previously published champion-defeating version of AlphaGo by 100 games to 0.

It does this using a novel form of reinforcement learning in which AlphaGo Zero becomes its own teacher. The system starts off with a neural network that knows nothing about the game of Go. It then plays games against itself, combining this neural network with a powerful search algorithm. As it plays, the neural network is tuned and updated to predict moves, as well as the eventual winner of the games.

(Source: [DeepMind](https://deepmind.google/blog/alphago-zero-starting-from-scratch/), [Wikipedia](https://en.wikipedia.org/wiki/Go_(game)))

*Discussion prompt: What do you think about AI models that can defeat humans using zero human knowledge?*

#### StarCraft II: AlphaStar vs MaNa (2019)

In a series of test matches held on 19 December, AlphaStar decisively beat Team Liquid's Grzegorz "MaNa" Komincz, one of the world's strongest professional StarCraft players, 5-0, following a successful benchmark match against his team-mate Dario "TLO" Wünsch.

(Source: [DeepMind](https://deepmind.com/blog/alphastar-mastering-real-time-strategy-game-starcraft-ii/))

#### MuZero: Mastering Games Without Knowing Their Rules

(Video: [Two Minute Papers](https://www.youtube.com/watch?v=hYV4-m7_SK8))

#### Debate: Project Debater vs Harish Natarajan (IBM Think 2019)

Project Debater's opponent was Harish Natarajan, the 2016 World Debating Championships Grand Finalist and 2012 European Debate Champion. Harish holds the world record for most debate competition victories. Each side had only 15 minutes to prep for the debate, during which they prepared arguments for and against a given thesis statement. Both sides then presented a four-minute opening statement, a four-minute rebuttal, and a two-minute summary.

Topic: *We should subsidize preschools.* Harish won (by audience vote).

(Source: [IBM](https://www.ibm.com/blogs/research/2019/02/ai-debate-think-2019/) — [Watch the debate](https://www.youtube.com/watch?v=m3u-1yttrVw))

#### Surgery: Surgical Robot Transformer (Johns Hopkins University)

A surgical robot performed the first realistic surgery without human help — a system trained on videos of surgeries that performs like an expert surgeon. Demo: the Surgical Robot Transformer-Hierarchy performing a gallbladder surgery.

(Video: [Johns Hopkins University](https://www.youtube.com/watch?v=c1E170Xr6BM))

### 1.2 AI Assistants & "Friends"?

- **Project Astra** (Video: [Google](https://www.youtube.com/watch?v=JcDBFAm9PPI&ab_channel=Google))
- **π0 (pi-zero)** — a general-purpose robot foundation model (Video: [Maginative](https://www.youtube.com/watch?v=J-UTyb7lOEw&ab_channel=Maginative))
- **Woebot** — finding Cognitive Behavioural Therapy with an AI chatbot (Video: [CNA Insider](https://www.youtube.com/watch?v=h6k2IGFfl1o&ab_channel=CNAInsider))
- **Other Mental Health Support Chatbots:**
  - **EarKick** — [earkick.com](https://earkick.com)
  - **Wysa** — [wysa.com](https://wysa.com)
  - **Youper** — [youper.ai](https://youper.ai)
  - **MentalHealthAssistant** — [github.com/Cody-Lange/MentalHealthAssistant](https://github.com/Cody-Lange/MentalHealthAssistant)
  - **Nila AI Therapist** — [github.com/mrnithesh/Nila-AI-Therapist](https://github.com/mrnithesh/Nila-AI-Therapist)
- **AI Companions: A Cure or Danger?** (Video: [TED](https://www.youtube.com/watch?v=-w4JrIxFZRA&ab_channel=TED))

### 1.3 So, What Can't AI Do, Really?

**AI-driven scientific discovery:**

"...an unsupervised method can recommend materials for functional applications several years before their discovery."

 For example, using only abstracts published before 2009, the algorithm was able to spot the best thermoelectric materials today, which were only recommended in the literature several years later, e.g., CuGaTe₂ in 2012, ReS₂ in 2016, and CdIn₂Te₄ in 2017.

**AlphaTensor** builds upon AlphaZero and discovered an algorithm that improves on Strassen's algorithm for the first time since its discovery 50 years ago. The algorithm discovered by AlphaTensor uses 76 multiplications, an improvement over state-of-the-art algorithms which use 80 multiplications.

**AlphaEvolve** — DeepMind's AI system for algorithm discovery. (Video: [Two Minute Papers](https://www.youtube.com/watch?v=T0eWBlFhFzc&ab_channel=TwoMinutePapers))

## 2. How Far Can AI Go?

### 2.1 Philosophical Questions

- *Does this include AI?* (Video: brain-computer interface demo, "China 'Implants' Brain-computer Interface Technology into More Real-world Application" — [Video](https://www.youtube.com/watch?v=Ld01d2-6III))
- *What is intelligence?*
- *What is artificial and what is not?*
- *What am I?*

...we need philosophy ("wisdom") for far-future prediction.

*Further readings:*
- *Skandhas (or Five aggregates): the five factors that constitute and explain a sentient being's person and personality. ([Wikipedia](https://en.wikipedia.org/wiki/Skandha))*
- *Dhammapada: "All that we are is the result of what we have thought: it is founded on our thoughts, it is made up of our thoughts."*

### 2.2 Optimism vs. Pessimism About AI's Future

> "It will either be the best thing that's ever happened to us, or it will be the worst thing. If we're not careful, it very well may be the last thing."
> — Stephen Hawking

> "It is hard to see how you can prevent the bad actors from using it for bad things."
> — Geoffrey Hinton

### 2.3 Ethics & Laws for AI (Module #2)

*Ethics and laws for AI are seriously considered in the field — for example, in the AI training program by Microsoft on edX, Ethics and Laws is offered as a separate course.*

## 3. Introduction to Data Science

### 3.1 What is Data Science?

- Data science is an interdisciplinary academic field that aims at extracting knowledge and insights from the data. *(Wikipedia)*
- Data science combines math and statistics, specialized programming, artificial intelligence (AI), and machine learning to uncover insights hidden in an organization's data. *(IBM)*

**Data Science = Data + Machine Learning**

### 3.2 What is Data?

Data is a collection of discrete or continuous values that convey information, or simply sequences of symbols that may be further interpreted formally. *(Wikipedia)*

Data comes in many forms:

- Tabular data
- Text
- Audio data
- Visual data (images, videos)

### 3.3 Common Data Types

#### Tabular Data

Data that is organized into a table (rows and columns). Typical file extensions: `.csv` or `.xlsx`.

**Features and Samples** — in a tabular dataset, each column is a *feature*, and each row is a *sample*.

#### Textual Data

Data in text format, i.e., collections of writings, sentences, such as emails, social media posts and comments, website content. Typical file extension: `.txt`.

A **text corpus** is a large collection of text data.

Common tasks with text data: Information Retrieval (IR), Natural Language Processing (NLP), and Information Extraction (IE).

#### Audio Data

Represent sound, such as speech, voice, songs. Typical file extensions: `.wav`, `.flac`, `.mp3`.

Common tasks: Speech recognition, Voice recognition, Environmental sound recognition, Text-to-speech.

#### Image Data

Collections of visual representation (2D or 3D). Typical file extensions: `.jpeg`, `.png`, `.tiff`.

Common tasks: Image classification, Image retrieval, Object recognition, Segmentation.
