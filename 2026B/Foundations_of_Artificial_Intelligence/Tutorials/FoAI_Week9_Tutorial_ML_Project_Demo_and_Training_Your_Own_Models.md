# FoAI Week 9 – Tutorial: ML Project Demo & Training Your Own Models

## 1. Assignment 3 Consultation

### 1.1 Assignment 3 Brief Info

1. **Assignment Requirements:**
   - Work in groups.
   - Select a meaningful project topic & find a relevant dataset.
   - Train ML models using Orange Data Mining.
   - Create presentation slide & recording.
2. **Submission Details:** Refer to Canvas Assignment 3 for details. Note: Teams must present online in Week 12 by playing their recording, followed by a Q&A session with the tutor. The time slots for presentations will be announced prior to Week 12.
3. **Questions?** Feel free to ask if you have any questions.

## 2. ML Project Demo (cont.)

### 2.1 Review: Key Steps of a ML Project

1. Understand the Problem
2. Prepare the Data
3. Train Models
4. Analyze Your Solution

### 2.2 Step 3. Train Models

Connect Training set's Data to Data of "Test and Score" widget.

Connect Test set's Data to Test Data of "Test and Score" widget.

![Edit Links dialog connecting Training set's Data output to the Data input of the "Test and Score" widget](images/Week9/09_connect_training_set_data.png)

![Edit Links dialog connecting Test set's Data output to the Test Data input of the "Test and Score" widget](images/Week9/09_connect_test_set_data.png)

Connect models, e.g., Linear Regression, Tree, Random Forest, to "Test and Score".

![Test and Score widget comparing Linear Regression, Tree, and Random Forest models by MSE, RMSE, MAE, MAPE, sMAPE, and R2](images/Week9/10_test_and_score_model_comparison.png)

### 2.3 Step 4. Analyze Your Solution

Connect Training set to "Random Forest".

Connect "Python Script" to a "Data Table" widget to show Feature importances.

Connect "Random Forest"'s Model to "Python Script"'s Object.

![Workflow connecting Random Forest's Model output to the Python Script widget's Object input, and the Python Script's Data output to a "Feat. Importances" Data Table widget](images/Week9/12_connect_random_forest_to_python_script.png)

Copy this code into the "Python Script" and click Run:

```python
import pandas as pd
from Orange.data.pandas_compat import table_from_frame

# Check if the model object has been passed into Python script
if in_object is None:
    print("Please connect 'Model' from Random Forest widget to 'Object' input of Python Script widget.")
else:
    # Extract the scikit-learn model from Orange object
    skl_model = in_object.skl_model
    # Check if the model is a Random Forest
    if type(skl_model).__name__ in ('RandomForestClassifier', 'RandomForestRegressor'):
        # Get feature importance scores calculated by Random Forest model
        importances = skl_model.feature_importances_
    else:
        print(f"Error: Expected Random Forest, but got {type(skl_model).__name__} instead.")

    # Get feature names
    feature_names = [attr.name for attr in in_object.domain.attributes]
    # Create a DataFrame to store feature_names and importances
    df_importance = pd.DataFrame({
        'Feature': feature_names,
        'Importance': importances
    }).sort_values(by='Importance', ascending=False)

    # Convert DataFrame to Orange Data Table
    out_data = table_from_frame(df_importance)

    # Print feature importances to the console
    print("--- FEATURE IMPORTANCES ---")
    print(df_importance.to_string(index=False))
```

Learn more about scripting in Orange at: orange3.readthedocs.io/projects/orange-data-mining-library/en/latest/

![Python Script widget editor with the feature-importance code entered, and the "Run" button highlighted](images/Week9/13_python_script_feature_importance_code.png)

![Feat. Importances Data Table widget showing 36 feature rows ranked by Importance, e.g. Area in m2 (0.691), District (0.081)](images/Week9/14_feature_importances_output_table.png)

### 2.4 The Full Orange Workflow for This Project

![Complete Orange workflow: CSV File Import → Select Columns → Filtered Cols → Data Sampler splitting into Training set / Test set → Linear Regression, Tree, and Random Forest models → Test and Score; Random Forest also feeds a Python Script → Feat. Importances table; plus Data Table, Data Info, Column Statistics, Correlations, and Scatter Plot widgets](images/Week9/16_full_orange_workflow_diagram.png)

## 3. Train Your Own Models

Now, apply ML workflow to **your data** of interest!

### 3.1 Example Past Student Projects

![Collage of past FoAI student Assignment 3 project posts, including House Price Predicting, Correlations between sleep and lifestyle factors, Amazon Fine Food Reviews, Stress level and influencing factors, and Real/Fake Job Posting Prediction](images/Week9/19_past_student_project_examples.png)

*Credit: FoAI students*

### 3.2 Activities

1. Students work in teams.
2. Select a topic you like. For example:
   - Analysis of Social Media Usage Habits & Mental Health
   - AI Usage Habits & Their Impact on Thinking Ability
   - AI Usage Habits & Independent Expression Skills
3. Find a dataset relevant to your chosen topic.
4. Create an Orange workflow for your ML project.

**NOTE:** These activities resemble the key steps your team will do in Assignment 3.

### 3.3 Things Your Team Should Try

- Different data visualizations (e.g., Box Plot, Heat Map), transformations (e.g., Unique for duplicate removal, Select Rows for outlier filtering), & models (e.g., Gradient Boosting, Stacking).
- Various training sets with different feature collections (using Select Columns widget).
- New add-ons, such as "Explain", "Networks"… Note: To install an add-on, go to: Options > Add-ons.

![Orange "Visualize" widgets panel: Tree Viewer, Box Plot, Violin Plot, Distributions, Scatter Plot, Line Plot, Bar Plot, Sieve Diagram, Mosaic Display, FreeViz, Linear Projection, Radviz, Heat Map, Venn Diagram, Silhouette Plot, Pythagorean Tree, Pythagorean Forest, CN2 Rule Viewer, Nomogram, Scoring Sheet Viewer](images/Week9/21_visualize_widgets.png)

![Orange "Transform" widgets panel: Data Sampler, Select Columns, Select Rows, Transpose, Split, Merge Data, Concatenate, Select by Data Index, Unique, Aggregate Columns, Group by, Pivot Table, Apply Domain, Preprocess, Impute, Continuize, Discretize, Randomize, Purge Domain, Melt, Formula, Create Class, Create Instance, Python Script](images/Week9/21_transform_widgets.png)

![Orange "Model" widgets panel: Constant, CN2 Rule Induction, Calibrated Learner, kNN, Tree, Random Forest, Gradient Boosting, SVM, Linear Regression, Logistic Regression, Naive Bayes, Scoring Sheet, AdaBoost, PLS, Curve Fit, Neural Network, Stochastic Gradient Descent, Stacking, Save Model, Load Model](images/Week9/21_model_widgets.png)

![Orange "Explain" add-on widgets panel: Feature Importance, Explain Model, Explain Prediction, Explain Predictions, ICE](images/Week9/21_explain_widgets.png)

![Orange "Network" add-on widgets panel: Network File, Network Explorer, Network Generator, Network Analysis, Network Clustering, Network Of Groups, Network From Distances, Network of Neighbors, Network Embeddings, Single Mode, Save Network](images/Week9/21_network_widgets.png)
