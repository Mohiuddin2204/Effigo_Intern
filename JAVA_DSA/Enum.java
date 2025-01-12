// Enum Declaration
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
public class Enum {
    public static void main(String[] args) {
        // Using enum values
        Day today = Day.MONDAY;
        
        // Printing the day
        System.out.println("Today is: " + today);

        // Using enum in a switch-case
        switch (today) {
            case MONDAY:
                System.out.println("Start of the workweek!");
                break;
            case FRIDAY:
                System.out.println("Almost weekend!");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Weekend!");
                break;
            default:
                System.out.println("Midweek!");
                break;
        }
        // Looping through all enum values
        System.out.println("\nAll Days of the Week:");
        for (Day day : Day.values()) {
            System.out.println(day);
        }
    }
}
