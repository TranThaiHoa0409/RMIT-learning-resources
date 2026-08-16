# COSC2968/COSC3053 – Foundations of Artificial Intelligence
## Week 4: Introduction to Machine Learning & Deep Learning

**This week's questions:** What is Machine Learning? Neural Networks — what's inside the box?

---

## 1. Definitions of Machine Learning

**Relationship to AI:** ML is a subfield of AI (alongside logic-based AI, search, etc.). Deep Learning (DL) is a subfield of ML that relies on Artificial Neural Networks, inspired by the human brain. Reinforcement Learning is another related branch.

**Classic definitions:**
- **Samuel (1959):** "Machine learning is a field of study that gives computers the ability to learn from experience without being explicitly programmed." (Illustrated with his checkers program.)
- **Mitchell (1998):** "A computer program is said to learn from experience E with respect to some task T and some performance measure P, if its performance on T, as measured by P, improves with experience E."

**Applying E/T/P — worked examples from class exercises:**
- *Spam filter:* E = watching the user label emails as spam/not spam (the data the system learns from)
- *AI coding assistant:* T = the task the system performs (e.g., analyzing codebases / generating code suggestions)
- *AI-designed plant-based meat:* P (performance measure) could be objective health ratings (e.g., Health Star Rating) and/or subjective sensory panel scores — i.e., both can serve as P

**Key ML vocabulary (using the spam-filter example):**
- Training data (training set)
- Sample / data point / instance
- Feature / attribute
- Performance measure

**Popular performance measures:**
- *Regression:* RMSE (Root Mean Squared Error), MAE (Mean Absolute Error), R² score
- *Classification:* Accuracy, F1-score

---

## 2. Types of Machine Learning

By level of human supervision, there are three main types:

### Supervised Learning
Uses **labeled data**.
- **Classification** — e.g., spam filtering, image classification
- **Regression** — e.g., housing price prediction, weather forecasting
- **Most important algorithms:** k-Nearest Neighbors, Linear Regression, Logistic Regression, Support Vector Machines (SVM), Decision Trees & Random Forests, Neural Networks

### Unsupervised Learning
Uses **unlabeled data**.
- **Clustering** (grouping similar data) — algorithms: K-Means, DBSCAN, Hierarchical Cluster Analysis (HCA). Examples: news article clustering (Google News), social network analysis, market segmentation.
- **Anomaly detection** — algorithms: One-Class SVM, Isolation Forest
- **Dimensionality reduction** — transforms high-dimensional data to lower dimensions while preserving essential structure, speeding up computation. Algorithms: PCA, LLE, t-SNE
- **Association rule learning** — discovers "if-then" patterns in data (e.g., customers who buy bread also tend to buy butter). Algorithms: Apriori, Eclat

### Reinforcement Learning
Agents learn via **rewards**. Examples: robots learning to walk, DeepMind's AlphaGo Zero.

**In-class quiz examples (supervised vs. unsupervised):**
- Spam filter with labeled emails → supervised
- Grouping news articles about the same story → unsupervised (clustering)
- Grouping customers into market segments → unsupervised (clustering)
- Classifying patients as diabetic/non-diabetic from labeled data → supervised
- Grouping people into personality types from survey responses (no predefined labels) → unsupervised

---

## 3. Neural Networks: What's Inside the Box?

**History:**
- Artificial Neural Networks (ANNs) were first introduced in 1943 by Warren McCulloch and Walter Pitts ("A Logical Calculus of Ideas Immanent in Nervous Activity").
- In 1958, Frank Rosenblatt developed the **Perceptron** — the first trainable neural network. Its inability to solve non-linear problems (e.g., XOR) contributed to the first AI Winter (late 1960s).
- ANNs are the foundation of the current (Deep Learning) wave of AI.

**Biological inspiration:** Artificial neurons are loosely modeled on biological neurons in the human cortex — inputs, weighted connections, and an activation/output signal.

**Structure:** A feed-forward neural network with many hidden layers is called a **Deep Neural Network** → hence "Deep Learning." An ANN is essentially a general function that can accept various inputs and (with enough capacity) approximate almost any desired output.

### The Math Behind a Neuron (advanced/optional)
Output of a single artificial neuron:

**y = φ( x₁w₁ + x₂w₂ + x₃w₃ + b )**

- The weighted sum (`x·w + b`) is a **linear function** (degree 1) — represents straight lines (2D), planes (3D), or hyperplanes (3+D).
- **φ (phi)** is the **activation function** — a non-linear function that lets the network represent curves and curved surfaces (e.g., spheres, hyperspheres), not just straight lines.

**Popular activation functions:**
- Sigmoid
- Hyperbolic tangent (tanh)
- Rectified Linear Unit (ReLU)

**Hands-on resource:** Experiment with neural networks interactively at **playground.tensorflow.org**

---

## Next Week's Topic
**Ethics & Law for AI Usage and Development**
