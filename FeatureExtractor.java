import java.util.HashMap;
import java.util.Map;

public class FeatureExtractor {

    public Map<String, Double> extract(String message) {
        Map<String, Double> features = new HashMap<String, Double>();

        // lowercase and remove punctuation
        String cleaned = message.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        String[] tokens = cleaned.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.isEmpty()) continue;

            if (features.containsKey(token)) {
                features.put(token, features.get(token) + 1.0);
            } else {
                features.put(token, 1.0);
            }
        }

        return features;
    }
}
