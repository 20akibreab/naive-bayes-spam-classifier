# Naive Bayes Spam Classifier

This is a Java project from my Data Structures class. It uses a Naive Bayes approach to classify text messages as spam or not spam.

## What it does
- Reads labeled messages from a dataset
- Extracts word-based features from the text
- Uses probabilities to predict a message's label

## What I used
Java, text processing, word frequencies, probability, and Naive Bayes classification.

## How to run
Compile the Java files:

```bash
javac *.java
```

The `SMSSpamCollection` dataset is included in this repository. It comes from the UCI Machine Learning Repository and contains labeled spam and non-spam text messages.

The program reads the dataset to train the classifier and make predictions.

## About this project
This was a class assignment. The code is a learning implementation, not a production spam filter. I have not included an accuracy benchmark.
