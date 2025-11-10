// Alexis Mitchell
// November 2, 2025
// This program generates two arrays of random numbers (integers and doubles)
// and writes them to a file named "AlexisMitchell_datafile.dat".

import java.io.*;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        String filename = "AlexisMitchell_datafile.dat";
        Random rand = new Random();

        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        // Fill arrays with random numbers
        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100); // random integers 0-99
            doubleArray[i] = rand.nextDouble() * 100; // random doubles 0.0-99.9
        }

        // Write or append data to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println("Random Integers:");
            for (int num : intArray) {
                writer.print(num + " ");
            }
            writer.println();

            writer.println("Random Doubles:");
            for (double num : doubleArray) {
                writer.printf("%.2f ", num);
            }
            writer.println();
            writer.println("------------------------------");
            System.out.println("Data written successfully to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
