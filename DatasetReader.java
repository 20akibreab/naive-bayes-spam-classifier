import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DatasetReader {

    public List<Example> read(File file) throws FileNotFoundException {
        ArrayList<Example> dataset = new ArrayList<Example>();
        FeatureExtractor extractor = new FeatureExtractor();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\t", 2);

            if (parts.length < 2) continue;

            String label = parts[0];
            String message = parts[1];

            Map<String, Double> features = extractor.extract(message);
            Example ex = new Example(label, features);
            dataset.add(ex);
        }

        sc.close();
        return dataset;
    }
}
