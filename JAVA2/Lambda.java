import java.util.*;

public class Lambda {
    public static void main(String[] args) {
        // Consumer
        List<Integer> l=Arrays.asList(1,2,3,4,5,6,4);
        l.forEach(x->System.out.println(x));

        //Comparator
        l.sort((r,le)->{return r.compareTo(le);});
        System.out.println(l);

        Map<Integer,Integer> m=new HashMap<>();
        m.put(1,1);
        m.forEach((k,v)->{System.out.println(k+" "+v);});

     }
}