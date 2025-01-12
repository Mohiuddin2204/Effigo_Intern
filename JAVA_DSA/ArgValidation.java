class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than zero.");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException("Name cannot be null or blank.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
public class ArgValidation {
    
    
        public static void main(String[] args) {
            try {
                Person p1 = new Person("John", 25); // Valid
                System.out.println(p1);
    
                Person p2 = new Person("", 30); // Invalid name
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
    
            try {
                Person p3 = new Person("Alice", -5); // Invalid age
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    
    
}
