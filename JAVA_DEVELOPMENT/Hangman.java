import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        String[] words = {
            "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", 
            "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange", 
            "papaya", "quince", "raspberry", "strawberry", "tangerine", 
            "ugli", "vanilla", "watermelon", "xigua", "yellowfruit", "zucchini",
            "peach", "plum", "coconut", "apricot", "blackberry", "blueberry",
            "cantaloupe", "dragonfruit", "gooseberry", "grapefruit", "guava", 
            "jackfruit", "lychee", "mulberry", "olive", "passionfruit", 
            "persimmon", "pineapple", "pomegranate", "pumpkin", "starfruit", 
            "soursop", "sugarapple", "tamarind", "tomato", "waterapple"
        };
        
        Scanner scanner = new Scanner(System.in);
        boolean[] guessed = new boolean[words.length];
        int score = 0;

        System.out.println("Welcome to Hangman! Start guessing words:");

        while (true) {
            System.out.print("Guess a word: ");
            String guess = scanner.nextLine().toLowerCase();

            boolean found = false;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(guess) && !guessed[i]) {
                    guessed[i] = true;
                    score++;
                    found = true;
                    System.out.println("Correct! Words guessed so far:");
                    for (int j = 0; j < guessed.length; j++) {
                        if (guessed[j]) {
                            System.out.print(words[j] + " ");
                        }
                    }
                    System.out.println();
                    break;
                }
            }

            if (!found) {
                System.out.println("Wrong guess! Game over.");
                break;
            }
        }

        System.out.println("Your final score: " + score);
        scanner.close();
    }
}
