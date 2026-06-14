/*Task 1
File Handling utility  */

import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    // File name to store data
    static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {

        // Scanner object for user input
        Scanner sc = new Scanner(System.in);

        int choice;

        // Loop until user exits
        do {

            // Display menu
            System.out.println("\n========== FILE HANDLING UTILITY ==========");
            System.out.println("1. Write to File");
            System.out.println("2. Read File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            // Switch case for menu operations
            switch (choice) {

                case 1:
                    writeFile(sc);
                    break;

                case 2:
                    readFile();
                    break;

                case 3:
                    modifyFile(sc);
                    break;

                case 4:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        // Close scanner
        sc.close();
    }

    // =====================================================
    // METHOD TO WRITE DATA INTO FILE
    // =====================================================
    public static void writeFile(Scanner sc) {

        try {

            // FileWriter writes characters into file
            FileWriter writer = new FileWriter(FILE_NAME);

            System.out.print("Enter text to write into file: ");
            String data = sc.nextLine();

            // Write data into file
            writer.write(data);

            // Close writer
            writer.close();

            System.out.println("Data written successfully!");

        } catch (IOException e) {

            // Handle file errors
            System.out.println("Error while writing file.");
            e.printStackTrace();
        }
    }

    // =====================================================
    // METHOD TO READ DATA FROM FILE
    // =====================================================
    public static void readFile() {

        try {

            // File object
            File file = new File(FILE_NAME);

            // Check whether file exists
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            // BufferedReader reads text efficiently
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            System.out.println("\n----- FILE CONTENT -----");

            // Read file line by line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("------------------------");

            // Close reader
            reader.close();

        } catch (IOException e) {

            // Handle exceptions
            System.out.println("Error while reading file.");
            e.printStackTrace();
        }
    }

    // =====================================================
    // METHOD TO MODIFY FILE CONTENT
    // =====================================================
    public static void modifyFile(Scanner sc) {

        try {

            File file = new File(FILE_NAME);

            // Check if file exists
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            // Read old content
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder oldContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                oldContent.append(line).append("\n");
            }

            reader.close();

            // Display existing content
            System.out.println("\nCurrent File Content:");
            System.out.println(oldContent);

            // Ask user for replacement text
            System.out.print("Enter word to replace: ");
            String oldWord = sc.nextLine();

            System.out.print("Enter new word: ");
            String newWord = sc.nextLine();

            // Replace text
            String newContent = oldContent.toString().replace(oldWord, newWord);

            // Write modified content back into file
            FileWriter writer = new FileWriter(FILE_NAME);

            writer.write(newContent);

            writer.close();

            System.out.println("File modified successfully!");

        } catch (IOException e) {

            // Handle exceptions
            System.out.println("Error while modifying file.");
            e.printStackTrace();
        }
    }
}