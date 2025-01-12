class MyClass {
    int value;

    MyClass(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
        MyClass other = (MyClass) obj; // Typecast to compare fields
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value); // Use Java's built-in hash function for integers
    }
}

public class OverridingEqualsAndHashCode {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass(10);
        MyClass obj2 = new MyClass(10);
        System.out.println(obj1.equals(obj2)); // Output: true (compares values)
        System.out.println(obj1.hashCode() == obj2.hashCode()); // Output: true (same hash code for equal objects)
    }
}