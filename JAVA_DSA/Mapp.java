import java.util.*;
public class Mapp {
    public static void main(String[] args) {
        // HashMap Example
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 25);
        hashMap.put("Alice", 30);
        hashMap.put("Bob", 22);

        System.out.println("HashMap:");
        for (String key : hashMap.keySet()) {  // Loop using keySet()
            System.out.println(key + ": " + hashMap.get(key));
        }

        System.out.println();

        // TreeMap Example (sorted by keys)
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("John", 25);
        treeMap.put("Alice", 30);
        treeMap.put("Bob", 22);

        System.out.println("TreeMap:");
        for (String key : treeMap.keySet()) {  // Loop using keySet()
            System.out.println(key + ": " + treeMap.get(key));
        }
    }
}