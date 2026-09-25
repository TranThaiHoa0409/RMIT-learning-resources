# Introduction to Machine Learning & Deep Learning

## Outline

1. Definitions of Machine Learning (ML)
2. Types of ML
3. Neural Networks: What's Inside the Box?

## 1. Definitions of Machine Learning

### Where ML Sits Within AI

![AI, ML, DL concept map showing ML as a subfield of AI and DL relying on Artificial Neural Networks](images/Week4/05_ai_ml_dl_concept_map.png)

- ML is a subfield of AI.
- DL relies on Artificial Neural Networks (inspired by the human brain).
- Other branches of AI include Logic-based AI, Search, and Reinforcement Learning.

*Image credit: Seema Singh*

### Definition (Samuel, 1959)

> Machine learning is a field of study that gives computers the ability to learn from experience without being explicitly programmed.

![Checkers board, the domain Samuel used to define machine learning](images/Week4/06_checkers_example.png)

*Image credit: Michel32Nl*

### Definition (Mitchell, 1998)

> A computer program is said to learn from experience E with respect to some task T and some performance measure P, if its performance on T, as measured by P, improves with experience E.

**Example: E, T, P in the checkers program.**

### In-Class Questions — Identifying E, T, P

**Question 1.** Suppose your email program watches which emails you do or do not mark as spam, and based on that learns how to better filter spam. What is the experience E in this setting?

A. The number (or fraction) of emails correctly classified as spam/not spam.
B. Watching you label emails as spam or not spam.
C. Classifying emails as spam or not spam.
D. None of the above — this is not a machine learning problem.

*Source: Andrew Ng*

**Question 2.** An AI-powered coding assistant analyzes millions of existing codebases and developer interactions. It helps software engineers by suggesting code completions and even generating entire functions. What is the task T in this setting?

A. Analyzing existing codebases and developer interactions.
B. Reviewing code and providing feedback to human developers.
C. Generating code snippets.
D. The number of lines of code produced per hour.

*Source: Gemini*

**Question 3.** A food tech company uses an AI system to develop new plant-based meat alternatives that are good for both the environment and human health. The AI model takes as input thousands of ingredient combinations and processing parameters, constantly refining recipes to mimic the taste and texture of meat. What can be the performance measure(s) P in this setting?

A. The Aus Health Star Rating, which gives food products a rating from ½ a star to 5 stars. The more stars, the healthier the product.
B. The sensory evaluation scores (e.g., taste, texture) given by human panellists.
C. The number of ingredient combinations and processing parameters.
D. Both A & B.

*Source: Gemini*

### Key Terminology

Using the example: *Suppose your email program watches which emails you do or do not mark as spam, and based on that learns how to better filter spam.*

| Term | Meaning in this example |
|---|---|
| Training data (training set) | The set of emails used to train the spam filter |
| Sample / data point / instance | A single email |
| Feature / attribute | A measurable property of an email used by the model |
| Performance measure | How well the filter distinguishes spam from non-spam |

### Popular Performance Measures

| For regression | For classification |
|---|---|
| RMSE (Root Mean Squared Error) | Accuracy |
| MAE (Mean Absolute Error) | F1-score |
| R-squared (R²) score | |

## 2. Types of ML

Main types of ML (with respect to human supervision):

- Supervised learning
- Unsupervised learning
- Reinforcement learning

### Supervised Learning

Use **labeled data**.

#### Classification

*e.g., spam filter, image classification*

![Email classification example: an inbox showing an email correctly flagged as Spam](images/Week4/16_email_classification_example.png)

*Image credit: Getty Images/iStockphoto*

![Image classification example: a grid of labeled images across classes such as airplane, automobile, bird, cat, deer, dog, frog, horse, ship, truck](images/Week4/17_image_classification_example.png)

*Image credit: medium.com*

#### Regression

*e.g., housing price prediction, weather forecast*

![Weather forecast app showing a week-long temperature and condition forecast](images/Week4/19_weather_forecast_example.png)

*Source: Yahoo Weather*

#### Most Important Supervised Learning Algorithms

- k-Nearest Neighbors
- Linear Regression
- Logistic Regression
- Support Vector Machines (SVM)
- Decision Trees and Random Forests
- Neural networks

### Unsupervised Learning

Use **unlabeled data**.

#### Clustering

Most important algorithms include K-Means, DBSCAN, Hierarchical Cluster Analysis (HCA).

![Clustering diagram: data points in a 2D feature space grouped into clusters](images/Week4/21_clustering_diagram.png)

*Source: (Géron, 2019)*

**Example: News clustering** (news.google.com)

![News clustering example 1: related news articles grouped into a single story cluster](images/Week4/22_news_clustering_example.png)

![News clustering example 2: a different set of related news articles grouped into a story cluster](images/Week4/23_news_clustering_example.png)

**Example: Social network analysis**

![Social network analysis diagram showing clusters of connected nodes/users](images/Week4/24_social_network_analysis_diagram.png)

*Image credit: thismarketerslife.it*

**Example: Market segmentation**

![Market segmentation illustration: a pie chart with groups of customer figures representing different segments](images/Week4/25_market_segmentation_example.png)

*Image credit: ilnordestquotidiano.it*

#### Anomaly Detection

Algorithms: One-class SVM, Isolation Forest.

![Anomaly detection diagram: a scatter plot of training instances with an anomaly point marked separately from normal instances](images/Week4/26_anomaly_detection_diagram.png)

*Source: (Géron, 2019)*

#### Dimensionality Reduction

Transforms high-dimensional data into a lower-dimensional space while preserving its essential patterns and making computations faster.

Example algorithms: Principal Component Analysis (PCA), Locally-Linear Embedding (LLE), t-distributed Stochastic Neighbor Embedding (t-SNE).

#### Association Rule Learning

Discovers "if-then" patterns between variables in large datasets, e.g., if a customer buys bread, they are also likely to buy butter.

Example algorithms: Apriori, Eclat.

### Reinforcement Learning

Agents are taught using rewards.

Examples: robots learn to walk, DeepMind's AlphaGo Zero.

![Reinforcement learning diagram: an agent observing the environment, selecting an action, receiving a reward or penalty, and updating its policy in a loop](images/Week4/28_reinforcement_learning_diagram.png)

*Source: (Géron, 2019)*

### In-Class Questions — Types of ML

**Question 1.** Of the following examples, which would you address using an unsupervised learning algorithm? (Check all that apply.)

A. Given email labeled as spam/not spam, learn a spam filter.
B. Given a set of news articles found on the web, group them into a set of articles about the same story.
C. Given a database of customer data, automatically group customers into different market segments.
D. Given a dataset of patients diagnosed as either having diabetes or not, learn to classify new patients as having diabetes or not.

*Source: Andrew Ng*

**Question 2.** Which of the following scenarios is the best example of a problem that would be addressed using an unsupervised learning algorithm?

A. A food technology company predicts the shelf life of a new dairy product based on temperature, humidity, and other data from similar products with known shelf lives.
B. An IT security firm uses historical data of known malware signatures to predict whether new .exe files are malicious or benign.
C. A psychology research team uses AI to group individuals into distinct personality types based on survey responses.
D. None of the above.

*Source: Gemini*

## 3. Neural Networks: What's Inside the Box?

Artificial Neural Networks (ANNs) form the foundation of Deep Learning.

![Illustration of a brain overlaid with a neural network structure, representing the biological inspiration behind ANNs](images/Week4/34_brain_neural_network.png)

*Image credit: quantamagazine.org*

**Historical notes:**

- Artificial Neural Networks were first introduced in 1943 by neurophysiologist Warren McCulloch and mathematician Walter Pitts in their landmark paper, *A Logical Calculus of Ideas Immanent in Nervous Activity*.
- In 1958, Frank Rosenblatt developed the Perceptron, the first trainable neural network capable of learning. However, its inability to solve non-linear problems (such as XOR) contributed to the onset of the first "AI Winter" in the late 1960s.

### Biological Neurons

![Diagram of a biological neuron labeled with dendrites, nucleus, cell body, axon, and axon terminals](images/Week4/35_biological_neuron_diagram.png)

*Image credit: appliedgo.net*

### Biological Neural Networks — Human Cortex

![Dense network of interconnected biological neurons in the human cortex](images/Week4/36_human_cortex.png)

*Image credit: (Géron, 2019)*

### Artificial Neurons

![Comparison diagram of a biological neuron and a simplified artificial neuron (inputs in₁, in₂, in₃ producing an output)](images/Week4/37_artificial_neuron_diagram.png)

*Image credit: appliedgo.net*

### Artificial Neural Networks

![Feed-forward neural network diagram showing an input layer, hidden layer, and output layer](images/Week4/38_feedforward_neural_network_diagram.png)

Many hidden layers constitute a Deep NN → Deep Learning.

*Image credit: appliedgo.net*

An ANN is a general function capable of accepting various inputs and producing (almost) any desired output.

→ How?

## Neural Networks: (A Little Bit) Math Behind

*Advanced & optional.*

### Output of an Artificial Neuron

![Diagram of an artificial neuron taking weighted inputs, summing them, and applying an activation function to produce an output](images/Week4/41_42_perceptron_neuron_diagram.png)

Reference: https://appliedgo.net/perceptron

Output of this neuron:

```
y = φ( x₁w₁ + x₂w₂ + x₃w₃ + b )
```

where `x₁, x₂, x₃` are outputs of previous neurons (inputs), `w₁, w₂, w₃` are the corresponding weights, `b` is the bias, and `y` is the output of this neuron.

- **Linear function** (degree = 1): represents ALL straight lines (2D space), planes (3D), hyperplanes (3+D).
- **φ (phi): Activation function** (non-linear): represents curves, curved surfaces (e.g., sphere), hyperspheres.

### Activation Functions

Popular activation functions:

- Sigmoid function
- Hyperbolic tangent (tanh)
- Rectified Linear Unit function (ReLU)

| Function | Formula | Graph |
|---|---|---|
| Sigmoid | y = 1 / (1 + e⁻ˣ) | ![Sigmoid activation function graph, an S-shaped curve bounded between 0 and 1](images/Week4/44_sigmoid_graph.png) |
| Tanh | y = 2 / (1 + e⁻²ˣ) − 1 | ![Hyperbolic tangent (tanh) activation function graph, an S-shaped curve bounded between -1 and 1](images/Week4/45_tanh_graph.png) |
| ReLU | y = max(0, x) | ![ReLU activation function graph, flat at 0 for negative x and linear for positive x](images/Week4/46_relu_graph.png) |

### Play with ANNs

https://playground.tensorflow.org

## Next Week's Topic

Ethics & Law for AI Usage and Development.
