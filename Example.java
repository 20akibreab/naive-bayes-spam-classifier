import java.util.Map;
import java.util.Set;

public class Example {
    private String label;
    private Map<String, Double> features;

    public Example(String label, Map<String, Double> features) {
        this.label = label;
        this.features = features;
    }

    public String getLabel() {
        return label;
    }

    public double getWordCount(String word) {
        if (features.containsKey(word)) {
            return features.get(word);
        }
        return 0;
    }

    public Set<String> getWords() {
        return features.keySet();
    }
}
