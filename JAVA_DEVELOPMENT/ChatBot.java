import java.util.*;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello. What is your name?");
        String name = scanner.nextLine();

        System.out.println("Hi " + name + "! I'm Javabot. Where are you from?");
        String place = scanner.nextLine();

        System.out.println("I hear it's beautiful at " + place + "! I'm from a place called Oracle.");
        System.out.println("How old are you?");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline

        System.out.println("So you're " + age + ", cool! I'm 400 years old.");
        System.out.println("This means I'm " + (400 / age) + " times older than you.");
        System.out.println("Enough about me. What's your favorite programming language? (just don't say Python)");
        String language = scanner.nextLine();

        if (language.equalsIgnoreCase("Python")) {
            System.out.println("I told you not to say Python!");
        } else {
            System.out.println(language + " is a great language!");
        }

        System.out.println("It was nice chatting with you, " + name + "! Goodbye!");
        scanner.close();
    }
}