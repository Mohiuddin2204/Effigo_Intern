import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileException {
    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } finally {
            System.out.println("Execution completed.");
        }
    }

    // Method that declares FileNotFoundException
    public static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file); // This may throw FileNotFoundException
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}