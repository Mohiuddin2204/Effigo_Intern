// Superclass (Parent Class)
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass (Child Class) that overrides the method
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

// Another subclass (Child Class) that overrides the method
class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Polymorphism {
    
    // Method Overloading: Same method name with different parameters
    void displayInfo(String name) {
        System.out.println("Animal name is: " + name);
    }

    void displayInfo(int age) {
        System.out.println("Animal age is: " + age);
    }

    public static void main(String[] args) {
        // Runtime Polymorphism (Method Overriding)
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();   // Upcasting (Animal reference, Dog object)
        Animal myCat = new Cat();   // Upcasting (Animal reference, Cat object)

        myAnimal.sound();   // Output: Animal makes a sound
        myDog.sound();      // Output: Dog barks (Overridden method)
        myCat.sound();      // Output: Cat meows (Overridden method)

        // Compile-time Polymorphism (Method Overloading)
        Polymorphism demo = new Polymorphism();
        demo.displayInfo("Buddy"); // Output: Animal name is: Buddy
        demo.displayInfo(5);       // Output: Animal age is: 5
    }
}