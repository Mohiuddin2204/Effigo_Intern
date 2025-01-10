class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class ToString {
    public static void main(String[] args) {
        Person person = new Person("John", 25);
        System.out.println(person.toString()); // Outputs: Person{name='John', age=25}
    }
}
