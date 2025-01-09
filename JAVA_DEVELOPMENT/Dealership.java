import java.util.*;

public class Dealership {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Java Dealership");
        System.out.println("Select option 'a' to buy a car");
        System.out.println("Select option 'b' to sell a car");
        String option = scan.nextLine();

        switch (option) {
            case "a":
                System.out.println("You chose to buy a car.");
                System.out.println("What is your budget?");
                int budget = scan.nextInt();
                scan.nextLine(); // Consume the leftover newline

                if (budget >= 10000) {
                    System.out.println("Nissan Altima is available.");
                    System.out.println("\nDo you have insurance? (yes/no)");
                    String insurance = scan.nextLine();
                    System.out.println("\nDo you have a license? (yes/no)");
                    String license = scan.nextLine();
                    System.out.println("What is your credit score?");
                    int creditScore = scan.nextInt();

                    if (insurance.equalsIgnoreCase("yes") &&
                        license.equalsIgnoreCase("yes") &&
                        creditScore > 660) {
                        System.out.println("Sold! Pleasure doing business with you.");
                    } else {
                        System.out.println("We're sorry. You are not eligible.");
                    }
                } else {
                    System.out.println("Sorry, we don't have any cars available within your budget.");
                }
                break;

            case "b":
                System.out.println("You chose to sell a car.");
                System.out.println("What is your car valued at?");
                int value = scan.nextInt();
                System.out.println("What is your selling price?");
                int price = scan.nextInt();

                if (value > price && price < 30000) {
                    System.out.println("We will buy your car. Pleasure doing business with you!");
                } else {
                    System.out.println("Sorry, we're not interested.");
                }
                break;

            default:
                System.out.println("Invalid option. Please select 'a' to buy or 'b' to sell.");
                break;
        }

        scan.close();
    }
}
