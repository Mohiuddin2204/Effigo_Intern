import java.util.*;

public class RunTimeException {
    public static void main(String[] args) {
        // ArrayIndexOutOfBoundsException
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // Invalid index
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        // NullPointerException
        try {
            String str = null;
            System.out.println(str.length()); // Accessing a method on null
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }

        // InputMismatchException
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an integer: ");
            int num = scanner.nextInt(); // If input is non-integer, exception occurs
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: Please enter a valid integer.");
        }

        
    }
}
