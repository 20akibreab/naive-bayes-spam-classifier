import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NaiveBayes {
    private double pSpam;
    private double pHam;
    private Map<String, Double> spamProbs;
    private Map<String, Double> hamProbs;
    private double spamTotal;
    private double hamTotal;
    private int V;

    public NaiveBayes() {
        spamProbs = new HashMap<String, Double>();
        hamProbs = new HashMap<String, Double>();
        spamTotal = 0;
        hamTotal = 0;
    }

    public void train(List<Example> dataset) {
        int spamCount = 0;
        int hamCount = 0;

        Map<String, Double> spamWords = new HashMap<String, Double>();
        Map<String, Double> hamWords = new HashMap<String, Double>();
        Set<String> vocab = new HashSet<String>();

        for (int i = 0; i < dataset.size(); i++) {
            Example ex = dataset.get(i);

            if (ex.getLabel().equals("spam")) {
                spamCount++;
                for (String word : ex.getWords()) {
                    vocab.add(word);
                    double wordCount = ex.getWordCount(word);
                    spamTotal += wordCount;
                    if (spamWords.containsKey(word)) {
                        spamWords.put(word, spamWords.get(word) + wordCount);
                    } else {
                        spamWords.put(word, wordCount);
                    }
                }
            } else {
                hamCount++;
                for (String word : ex.getWords()) {
                    vocab.add(word);
                    double wordCount = ex.getWordCount(word);
                    hamTotal += wordCount;
                    if (hamWords.containsKey(word)) {
                        hamWords.put(word, hamWords.get(word) + wordCount);
                    } else {
                        hamWords.put(word, wordCount);
                    }
                }
            }
        }

        // P(spam) and P(ham)
        pSpam = (double) spamCount / dataset.size();
        pHam = (double) hamCount / dataset.size();

        // V is total unique words across all examples, needed for smoothing
        V = vocab.size();

        // calculate P(word|spam) and P(word|ham) for each word in vocab
        // +1 and +V is laplace smoothing so we don't multiply by 0
        for (String word : vocab) {
            double spamWordCount = 0;
            double hamWordCount = 0;

            if (spamWords.containsKey(word)) {
                spamWordCount = spamWords.get(word);
            }
            if (hamWords.containsKey(word)) {
                hamWordCount = hamWords.get(word);
            }

            spamProbs.put(word, (spamWordCount + 1.0) / (spamTotal + V));
            hamProbs.put(word, (hamWordCount + 1.0) / (hamTotal + V));
        }
    }

    public String predict(String message) {
        FeatureExtractor extractor = new FeatureExtractor();
        Map<String, Double> features = extractor.extract(message);

        // start with log of the prior
        double logSpam = Math.log(pSpam);
        double logHam = Math.log(pHam);

        for (String word : features.keySet()) {
            double count = features.get(word);
            double spamP;
            double hamP;

            if (spamProbs.containsKey(word)) {
                spamP = spamProbs.get(word);
            } else {
                spamP = 1.0 / (spamTotal + V);
            }

            if (hamProbs.containsKey(word)) {
                hamP = hamProbs.get(word);
            } else {
                hamP = 1.0 / (hamTotal + V);
            }

            logSpam += count * Math.log(spamP);
            logHam += count * Math.log(hamP);
        }

        if (logSpam > logHam) {
            return "Spam";
        } else {
            return "Ham";
        }
    }
}
