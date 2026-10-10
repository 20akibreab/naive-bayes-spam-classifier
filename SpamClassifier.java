import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SpamClassifier {

    public static void main(String[] args) {
        NaiveBayes nb = new NaiveBayes();
        DatasetReader reader = new DatasetReader();
        List<Example> dataset = null;

        try {
            dataset = reader.read(new File("SMSSpamCollection"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find dataset file.");
            return;
        }

        nb.train(dataset);
        System.out.println("Training complete.");

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("quit")) {
            System.out.print("Enter a message: ");
            input = sc.nextLine();

            if (!input.equals("quit")) {
                System.out.println(nb.predict(input));
            }
        }

        sc.close();
    }
}
