import java.util.*;
public class Sccanner {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name:");
        String name=sc.nextLine();
        System.out.println("Enter amount of money spend on coffee");
        double money=sc.nextDouble();
        System.out.println("Enter amount of money spend on food");
        double food=sc.nextDouble();
        System.out.println("Your name is "+name+" and you spend "+(money+food) );
        sc.close();
    }
}