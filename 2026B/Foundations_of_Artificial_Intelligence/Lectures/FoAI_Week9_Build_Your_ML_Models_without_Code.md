# FoAI Week 9 – Lecture: Build Your ML Models without Code

## 1. Machine Learning Basics: Review

A quick recap of concepts from previous weeks, relevant to this week's hands-on project:

- ML types
- Dataset types
- Features / Samples / Labels
- Performance metrics

## 2. Assignment 3: ML Project

### 2.1 A3 Overview

1. **Assignment Requirements:**
   - Work in groups.
   - Select a meaningful project topic & find a relevant dataset.
   - Train ML models using Orange Data Mining.
   - Create presentation slide & recording.
2. **Submission Details:** Refer to Canvas Assignment 3 for details. Note: Teams must present online in Week 12 by playing their recording, followed by a Q&A session with the tutor. The time slots for presentations will be announced prior to Week 12.
3. **Questions?** Feel free to ask if you have any questions.

### 2.2 Bring Your Team's Topics of Interest

Bring your team's topics of interest!

### 2.3 Example Past Student Projects

![Collage of past FoAI student Assignment 3 project posts, including House Price Predicting, Correlations between sleep and lifestyle factors, Amazon Fine Food Reviews, Stress level and influencing factors, and Real/Fake Job Posting Prediction](images/Week9/09_past_student_project_examples.png)

*Credit: FoAI students*

## 3. Getting Started with Orange Data Mining

### 3.1 What Is Orange Data Mining

Orange Data Mining (or **Orange** for short) is a no-code platform for data visualization and machine learning.

- Docs & Tutorial: orangedatamining.com/docs
- Download: orangedatamining.com/download

### 3.2 Background & Key Features

- Orange was originally developed at the University of Ljubljana in Slovenia in 1996.
- It's open-source: github.com/biolab/orange3
- Powered by scientific Python libraries, including scikit-learn, numpy, and scipy.
- **Real-Time Interactivity:** Orange immediately updates widgets in the dataflows as soon as you change a setting or select a data point (no need to hit a "Run" button).
- Orange works seamlessly with datasets containing up to 100,000 rows.

### 3.3 Orange Alternatives

| Feature | Orange Data Mining | KNIME Analytics Platform | RapidMiner Studio | Weka |
|---|---|---|---|---|
| Pricing | **100% Free** (Open-source) | Free Desktop version; Paid server scaling | Paid (Commercial); Very limited free tier | **100% Free** (Academic open-source) |
| Best For | Students, Educators, Quick prototyping | Enterprise ETL, Advanced Data Engineering | Business Analytics, Automated ML (AutoML) | Pure Academic research & algorithms |
| Data Handling | Small to Medium (Up to ~100k rows) | **Massive Big Data** (Millions of rows / Databases) | Large (Optimized memory prep) | Small to Medium (Heavy Java footprint) |
| Underlying Code | Python | Java / Python Extensions | Java | Java |

*Source: Google Search AI*

### 3.4 Orange Widgets & Add-ons

Orange Widgets are components used to create data workflows in Orange.

- Widgets can communicate with each other.
- **Input:** on the *left* side. **Output:** on the *right* side.

![Example of a widget connection: Data Table widget outputting "Selected Data → Data" into a Scatter Plot widget](images/Week9/14_widget_io_example.png)

You can find more widgets by 2 ways:

1. **Add-ons:** go to *Options > Add-ons*. See full list of Orange widgets: orangedatamining.com/widget-catalog
2. **Write your own widgets:** see orange-widget-base.readthedocs.io/en/latest/tutorial.html#a-demo-package

![Icons of the "Explain" add-on widgets: Feature Importance, Explain Model, Explain Prediction, Explain Predictions, and ICE](images/Week9/14_explain_addon_icons.png)

## 4. Key Steps in ML Projects

### 4.1 How Do You Analyze Your Data?

*(Select all that apply)*

- Never done it before
- Use my bare eyes
- Use SPSS
- Use ChatGPT, Gemini…
- Use Python/coding tools
- Use other tools/solutions (specify names)

### 4.2 Main Steps of a ML Project (Detailed)

1. Look at the big picture
2. Get the data
3. Explore and analyse the data to gain insights (aka Exploratory Data Analysis (EDA))
4. Prepare the data (aka Data Preprocessing)
5. Train and evaluate models
6. Fine-tune models
7. Test and analyze your solution
8. Launch, monitor, and maintain your system

### 4.3 Key Steps of a ML Project (Simplified)

1. Understand the Problem
2. Prepare the Data
3. Train Models
4. Analyze Your Solution

### 4.4 Step 1. Understand the Problem

- What is the end goal of the project?
- How do the existing solutions (if any) perform?
- What algorithms to use?
- What performance measure is relevant?
- What data we need to collect?

**Performance Measures:**

- Root Mean Square Error (RMSE)
- Mean Absolute Error (MAE)

### 4.5 Step 2. Prepare the Data

Get the data determined in step 1.

Some data repositories:

- UCI Machine Learning Repository
- Kaggle datasets
- Amazon's AWS public datasets
- http://dataportals.org
- http://opendatamonitor.eu
- Wikipedia's list of Machine Learning datasets
- Datasets subreddit

**This week's demo dataset:** http://www.laydulieu.com/nha-dat (Note: the website is no longer in service.)

The task is predicting **APARTMENT PRICE** in Hochiminh, Vietnam.

## 5. ML Projects Demo: Building a Workflow in Orange

### 5.1 Orange Interface: Canvas & Widgets Panel

- **Canvas:** the area where you create your ML workflow by "drag & drop" widgets.
- **Widgets Panel:** you can type a widget name in the "Filter…" box to find it.

![Orange interface with the Widgets Panel on the left and the empty Canvas on the right, both highlighted](images/Week9/22_orange_canvas_and_widgets_panel.png)

### 5.2 Importing Data

Drag "CSV File Import" widget to the Canvas. Then select your data file.

![Dragging the CSV File Import widget onto the Canvas, and the CSV File Import dialog showing the ApartmentPrice_SG_June2021 file with 1950 rows, 11 features, 0 metas](images/Week9/23_import_csv_file.png)

### 5.3 Viewing Data: Data Table & Data Info

Drag "Data Table" widget to the Canvas, and connect with "CSV File Import". Then connect "Data Info" widget with "Data Table".

![Data Table widget showing the apartment listings (Property Type, Need, Province/City, District, etc.) and Data Info widget showing dataset properties: ~1950 rows, 11 columns, 6 categorical + 5 numeric features, 12.7% missing data](images/Week9/24_data_table_and_data_info.png)

### 5.4 Column Statistics

Widget "Column Statistics": shows statistics of features.

![Column Statistics widget showing mean, mode, median, dispersion, and min for features such as Number of Toilets, Number of Bedrooms, Area in m2, and Price in Million VND](images/Week9/25_column_statistics_widget.png)

### 5.5 Scatter Plot: Visualizing Samples

Widget "Scatter Plot": visualizes samples. (Click to export plot.)

![Scatter Plot widget with Area in m2 on the x-axis and Price in Million VND on the y-axis, points colored by District](images/Week9/26_scatter_plot_visualize_samples.png)

### 5.6 Scatter Plot: Computing Correlations

Widget "Scatter Plot" (Correlations): computes correlation between features.

![Correlations widget listing Pearson correlations of Area in m2, Number of Bedrooms, and Number of Toilets against Price in Million VND](images/Week9/27_scatter_plot_correlations_widget.png)

### 5.7 Selecting Feature Columns

Right-click to rename to "Filtered Cols".

![Select Columns widget choosing features/target, connected to a renamed "Filtered Cols" Data Table widget showing the resulting dataset](images/Week9/28_select_columns_and_filtered_cols.png)

### 5.8 Splitting Data into Training & Test Sets

Widget "Data Sampler": randomly split data into training & test set.

Right-click the connection link and choose "Remaining Data".

![Data Sampler widget set to 80% Fixed proportion of data, and the Edit Links dialog connecting "Remaining Data" to the Test set widget](images/Week9/29_data_sampler_split_train_test.png)

### 5.9 Training & Test Set Preview

![Training set (1365 instances) and Test set (585 instances) Data Table widgets, each showing Price in Million VND and District columns](images/Week9/30_training_and_test_set_preview.png)

## 6. Next Week & Wrap-up

### 6.1 Next Week's Topic: End-to-End ML Project

**End-to-end ML project\***

*\*Optional & advanced topic: Required programming background (Python)*

*Source: scikit-learn.org*

![Preview of the scikit-learn.org website homepage, showing Classification, Regression, and Clustering sections with example plots](images/Week9/32_scikit_learn_website_preview.png)
