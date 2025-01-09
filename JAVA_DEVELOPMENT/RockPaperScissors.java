import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        
        String userChoice = getUserChoice();
        String computerChoice = getComputerChoice();
        
        System.out.println("You chose: " + userChoice);
        System.out.println("Computer chose: " + computerChoice);
        
        determineWinner(userChoice, computerChoice);
    }

    public static String getUserChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your choice (rock, paper, or scissors):");
        String choice = scan.nextLine().toLowerCase();
        
        while (!choice.equals("rock") && !choice.equals("paper") && !choice.equals("scissors")) {
            System.out.println("Invalid choice! Please enter rock, paper, or scissors:");
            choice = scan.nextLine().toLowerCase();
        }
        
        return choice;
    }

    public static String getComputerChoice() {
        int randomNum = (int) (3 * Math.random());
        switch (randomNum) {
            case 0: return "rock";
            case 1: return "paper";
            default: return "scissors";
        }
    }

    public static void determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            System.out.println("It's a tie!");
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
    }
}
