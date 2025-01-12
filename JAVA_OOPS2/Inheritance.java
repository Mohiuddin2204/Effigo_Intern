// Superclass (Parent Class)
class Animal {
    String name;

    // Constructor of superclass
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called");
    }

    // Method of superclass
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass (Child Class) inheriting from Animal
class Dog extends Animal {

    // Constructor of subclass
    Dog(String name) {
        // Calling the superclass constructor using super
        super(name);
        System.out.println("Dog constructor called");
    }

    // Overriding the method of superclass
    @Override
    void sound() {
        // Using super to call the superclass method (optional)
        super.sound();
        System.out.println("Dog barks");
    }

    // New method in subclass
    void displayInfo() {
        System.out.println("Dog's name: " + name);
    }
}

public class Inheritance {
    public static void main(String[] args) {
        // Creating an object of the subclass
        Dog dog = new Dog("Buddy");

        // Calling the overridden method
        dog.sound();

        // Calling the method from subclass
        dog.displayInfo();
    }
}