class MyClass {
    int value;
}

public class DefaultEquals {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();
        System.out.println(obj1.equals(obj2)); // Output: false (different references)
    }
}