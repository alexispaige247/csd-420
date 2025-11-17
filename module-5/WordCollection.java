import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCollection {

    public static void main(String[] args) {
        String filename = "collection_of_words.txt";

        // Read words from file
        Set<String> uniqueWords = readWordsFromFile(filename);

        // Display ascending order
        System.out.println("Ascending Order:");
        uniqueWords.stream()
                .sorted()
                .forEach(System.out::println);

        // Display descending order
        System.out.println("\nDescending Order:");
        uniqueWords.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    /** Reads all unique words from the target file. */
    public static Set<String> readWordsFromFile(String filename) {
        Set<String> words = new HashSet<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                // Clean punctuation and normalize to lowercase
                String word = scanner.next()
                        .replaceAll("[^a-zA-Z]", "")
                        .toLowerCase();

                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        return words;
    }
}
