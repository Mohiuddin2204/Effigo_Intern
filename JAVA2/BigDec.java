import java.math.BigDecimal;

public class BigDec {
    public static void main(String[] args) {
        double x=0.1;
        double y=0.2;
        double z=x+y;
        System.out.println(z);

        BigDecimal a=new BigDecimal(0.1);
        BigDecimal b=new BigDecimal(0.2);
        System.out.println(a.add(b));
        System.out.println(b.subtract(a));
    }
}