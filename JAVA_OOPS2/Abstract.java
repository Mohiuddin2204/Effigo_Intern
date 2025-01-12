abstract class Animal {
    // Abstract method (does not have a body)
    abstract void sound();
    
    // Concrete method (with implementation)
    void sleep() {
        System.out.println("This animal is sleeping.");
    }
}

// Subclass (concrete class)
class Dog extends Animal {
    // Providing implementation for the abstract method
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    // Providing implementation for the abstract method
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Abstract {
    public static void main(String[] args) {
        // Animal animal = new Animal();  // This would give a compile-time error
        
        Animal myDog = new Dog();   // Create a Dog object
        myDog.sound();  // Output: Dog barks
        myDog.sleep();  // Output: This animal is sleeping.
        
        Animal myCat = new Cat();   // Create a Cat object
        myCat.sound();  // Output: Cat meows
        myCat.sleep();  // Output: This animal is sleeping.
    }
}
