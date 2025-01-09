import java.util.*;

public class DiceGame {

    public static void main(String[] args) {
        System.out.println("Welcome to the Dice Game!");
        int sumOriginal = getUserNumbers();
        
        int sumDice = rollDiceAndComputeSum();
        
        checkIfUserWins(sumOriginal, sumDice);
    }

    public static int getUserNumbers() {
        Scanner scan = new Scanner(System.in);
        int sum = 0;

        System.out.println("Choose 3 numbers between 1 and 6:");
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter number " + i + ": ");
            int num = scan.nextInt();
            
            while (num < 1 || num > 6) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                num = scan.nextInt();
            }
            sum += num;
        }

        System.out.println("Your chosen numbers sum up to: " + sum);
        return sum;
    }

    public static int rollDiceAndComputeSum() {
        int sum = 0;
        System.out.println("Rolling the dice...");

        for (int i = 1; i <= 3; i++) {
            int roll = (int) (6 * Math.random()) + 1;
            System.out.println("Roll " + i + ": " + roll);
            sum += roll;
        }

        System.out.println("The dice rolls sum up to: " + sum);
        return sum;
    }

    public static void checkIfUserWins(int sumOriginal, int sumDice) {
        int difference = Math.abs(sumOriginal - sumDice);
        
        if (sumDice < sumOriginal && difference < 3) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("Sorry, you lose. Better luck next time!");
        }
    }
}
