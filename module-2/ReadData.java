// Alexis Mitchell
// November 2, 2025
// This program reads data from a file named "AlexisMitchell_datafile.dat"

import java.io.*;

public class ReadData {
    public static void main(String[] args) {
        String filename = "AlexisMitchell_datafile.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Contents of " + filename + ":");
            System.out.println("---------------------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please run WriteData first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}