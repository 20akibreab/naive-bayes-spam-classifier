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

The program expects a dataset file called `SMSSpamCollection`, which is **not included here**. Add a properly sourced copy before running the classifier, and check the file path in `SpamClassifier.java`.

## About this project
This was a class assignment. The code is a learning implementation, not a production spam filter. I have not included an accuracy benchmark.
