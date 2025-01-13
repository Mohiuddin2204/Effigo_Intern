import java.util.*;
public class Pipeline {
    public static void main(String[] args) {
        List<Integer> l=Arrays.asList(25,10,12,14,16);
        l.stream().filter(p->{return p>15;}).
        map(p->{return p*2;}).sorted((r,le)->{return r.compareTo(le);}).
        map(p->{return "$"+p;}).forEach(p->{System.out.println(p);});

        List<Integer> l2=Arrays.asList(25,10,12,14,16);
        Integer sum = l2.stream()
        .filter(p -> p > 15)
        .reduce(0, (x, y) -> x + y); 

System.out.println("Sum: " + sum); // Print the result

        
List<Integer> l3=Arrays.asList(25,10,12,14,16);
Long c = l3.stream().filter(p->p>15).count();
System.out.println("Count: " + c);

    }
}