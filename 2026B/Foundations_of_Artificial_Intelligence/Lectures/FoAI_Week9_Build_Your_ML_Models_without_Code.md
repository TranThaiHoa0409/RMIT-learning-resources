# FoAI Week 9 – Lecture: Build Your ML Models without Code

## 1. Getting Started with Orange Data Mining

### 1.1 What Is Orange Data Mining

Orange Data Mining (or **Orange** for short) is a no-code platform for data visualization and machine learning.

- [Docs & Tutorial](https://orangedatamining.com/docs/)
- [Download](https://orangedatamining.com/download/)

### 1.2 Background & Key Features

- Orange was originally developed at the University of Ljubljana in Slovenia in 1996.
- It's open-source: [Github](https://orangedatamining.com/download/)
- Powered by scientific Python libraries, including scikit-learn, numpy, and scipy.
- **Real-Time Interactivity:** Orange immediately updates widgets in the dataflows as soon as you change a setting or select a data point (no need to hit a "Run" button).
- Orange works seamlessly with datasets containing up to 100,000 rows.

### 1.3 Orange Alternatives

| Feature | Orange Data Mining | KNIME Analytics Platform | RapidMiner Studio | Weka |
|---|---|---|---|---|
| Pricing | **100% Free** (Open-source) | Free Desktop version; Paid server scaling | Paid (Commercial); Very limited free tier | **100% Free** (Academic open-source) |
| Best For | Students, Educators, Quick prototyping | Enterprise ETL, Advanced Data Engineering | Business Analytics, Automated ML (AutoML) | Pure Academic research & algorithms |
| Data Handling | Small to Medium (Up to ~100k rows) | **Massive Big Data** (Millions of rows / Databases) | Large (Optimized memory prep) | Small to Medium (Heavy Java footprint) |
| Underlying Code | Python | Java / Python Extensions | Java | Java |

### 1.4 Orange Widgets & Add-ons

Orange Widgets are components used to create data workflows in Orange.

- Widgets can communicate with each other.
- **Input:** on the *left* side. **Output:** on the *right* side.

You can find more widgets by 2 ways:

1. **Add-ons:** go to *Options > Add-ons*. See full list of [Orange widgets](https://orangedatamining.com/widget-catalog/)
2. **Write your own widgets:**  [Orange Widget Development](https://orange-widget-base.readthedocs.io/en/latest/tutorial.html)

## 2. Key Steps in ML Projects

### 2.1 Main Steps of a ML Project

1. Look at the big picture
2. Get the data
3. Explore and analyse the data to gain insights (aka Exploratory Data Analysis (EDA))
4. Prepare the data (aka Data Preprocessing)
5. Train and evaluate models
6. Fine-tune models
7. Test and analyze your solution
8. Launch, monitor, and maintain your system

### 2.2 Understand the Problem

What is the end goal of the project?

- How do the existing solutions (if any) perform?
- What algorithms to use?
- What performance measure is relevant?
- What data we need to collect?

**Performance Measures:**

- Root Mean Square Error (RMSE)
- Mean Absolute Error (MAE)

### Some data repositories

- UCI Machine Learning Repository
- Kaggle datasets
- Amazon's AWS public datasets
- [Data Portals](http://dataportals.org) - A Comprehensive List of [Open Data](https://opendefinition.org/) Portals from Around the World
- Wikipedia's list of Machine Learning datasets
- Datasets subreddit
