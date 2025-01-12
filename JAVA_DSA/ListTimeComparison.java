import java.util.*;

public class ListTimeComparison {
    public static void main(String[] args) {
        int size = 1000000 ; // Size of the list
        long startTime, endTime;

        // ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i); // Adding elements
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList add time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        arrayList.get(size / 2); // Accessing an element
        endTime = System.nanoTime();
        System.out.println("ArrayList get time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        arrayList.remove(45000); // Removing an element
        endTime = System.nanoTime();
        System.out.println("ArrayList remove time: " + (endTime - startTime) + " ns");

        // LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i); // Adding elements
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList add time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        linkedList.get(size / 2); // Accessing an element
        endTime = System.nanoTime();
        System.out.println("LinkedList get time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        linkedList.remove(45000); // Removing an element
        endTime = System.nanoTime();
        System.out.println("LinkedList remove time: " + (endTime - startTime) + " ns");
    }
}