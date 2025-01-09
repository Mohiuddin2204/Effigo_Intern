// This is a single-line comment: the main class of the program
public class Comments {

    /* This is a multi-line comment:
       The main method is the entry point of the program. */
    public static void main(String[] args) {

        /**
         * This documentation comment explains the purpose of the printGreeting method.
         *
         * @param name The name of the person to greet
         */
        printGreeting("John");
    }

    /**
     * Prints a greeting message to the console.
     *
     * @param name The name of the person to greet
     */
    public static void printGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
