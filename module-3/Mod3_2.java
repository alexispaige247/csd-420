// Alexis Mitchell
// November 9, 2025
// This program defines a generic method to remove duplicates from an ArrayList.

import java.util.ArrayList;
import java.util.Random;

public class Mod3_2 {
    // Generic method to remove duplicates from an ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Fill the original list with 50 random integers (1–20)
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Call removeDuplicates and store the result
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // Display the lists
        System.out.println("Original ArrayList (with duplicates):");
        System.out.println(originalList);
        System.out.println("\nArrayList after removing duplicates:");
        System.out.println(noDuplicatesList);
    }
}