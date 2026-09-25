# FoAI Week 10 – Tutorial: ML Project Demo & Hands-on Exercises

## 1. Demo End-to-End ML Project (cont.)

### 1.1 Code Walkthrough

*(See code: [DEMO_end_to_end_ml_project.py](images/Week10/DEMO_end_to_end_ml_project.py))*

### 1.2 Launch and Maintain Your System

- **Write monitoring code:** catch system breakage and performance degradation.
- **Human evaluation:** analyze the system's output regularly (by experts, workers, etc.).
- **Monitor system's input:** poor quality input, e.g., blurry images from cameras or noisy signal from sensors, can degrade system performance.
- **Retrain the models** on a regular basis using fresh data.
- **Save snapshots** of the system regularly, to roll back to a previously working state in case of a crash.

## 2. Hands-on Exercises

Note: You can use the demo code as a starting point & modify it for your project. The following exercises are suggestions for practicing your ML project.

- Exercise #1: Dataset Acquisition
- Exercise #2: Perform Exploratory Data Analysis (EDA)
- Exercise #3: Data Preprocessing
- Exercise #4: Model Training
- Exercise #5: Hyperparameter Tuning
- Exercise #6: Model Testing

### 2.1 Exercise #1: Dataset Acquisition *[Optional]*

You are free to select any dataset you want, as long as it meets the following requirements. The dataset can be downloaded from the internet or collected manually (e.g., using data crawling).

- **Task:** Regression.
- **Features:** At least 5 features, including at least 1 categorical and 4 numerical features.
- **Samples:** Minimum of 1000 samples.

**Search keywords to find data:**

- "Regression dataset in psychology…"
- "Regression data about food processing"
- "Software testing dataset"
- "Dataset for flight prediction"
- "Regression data in robotics"
- "Regression dataset in healthcare"

### 2.2 Exercise #2: Perform EDA

Perform exploratory data analysis (EDA) to understand data characteristics.

- Identify potential issues such as missing values, outliers, and imbalanced classes.

### 2.3 Exercise #3: Data Preprocessing

1. Create a pipeline to preprocess numeric data.
2. Create a pipeline to preprocess categorical data.
3. Create a full pipeline to preprocess the entire dataset.
4. Transform the raw data using the created pipeline and inspect the processed data.

### 2.4 Exercise #4: Model Training

1. Using processed data, train at least 4 models, including at least 2 models different from those covered in the lecture.
2. Evaluate models' performance using appropriate metrics (e.g., r2-score, RMSE, MAE) and K-fold cross-validation.
3. Compare the performance of different models and justify your choice of the best model.

### 2.5 Exercise #5: Hyperparameter Tuning

1. Use random search to fine-tune the hyperparameters of your best model.
2. Discuss with your team and your tutor, and provide justification for the hyperparameters you select to fine-tune and their value ranges.

### 2.6 Exercise #6: Model Testing

1. Test the selected model on the test set.
2. Analyze the model's performance and get insights.
3. Give discussion on the limitations and potential areas for improvement.
