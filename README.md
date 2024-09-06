# K Nearest Neighbor (KNN) Classifier

This project implements a K Nearest Neighbor (KNN) classifier for the Iris dataset. It demonstrates the use of the KNN algorithm for multi-class classification.

## Authors

- Rodolfo Lopez
- Andrew Kirraine

## Date

June 13, 2022

## Features

- Implements KNN algorithm from scratch
- Handles the Iris dataset (setosa, versicolor, virginica)
- Allows user to specify the K value and training/test split percentage
- Calculates and reports classification accuracy

## Files

- `KNN.java`: Main driver class
- `KNNClassifier.java`: Core KNN algorithm implementation
- `Data.java`: Class to represent individual data points
- `DistanceData.java`: Helper class for distance calculations
- `CountData.java`: Helper class for vote counting
- `iris-training.txt`: Iris dataset
- `iris-test.txt`: Output file for test results

## Usage

1. Compile all Java files:

   ```
   javac *.java
   ```

2. Run the program, specifying the training data file:

   ```
   java KNN iris-training.txt
   ```

3. Follow the prompts to enter:

   - K value (number of neighbors)
   - Training/test split percentage (0.0 - 1.0)

4. The program will output classification results and accuracy to both the console and `iris-test.txt`.

## Algorithm

The KNN classifier works as follows:

1. Load and split the dataset into training and test sets
2. For each test instance:
   - Calculate its distance to all training instances
   - Find the K nearest neighbors
   - Assign the class based on majority vote of these neighbors
3. Calculate and report the overall accuracy
