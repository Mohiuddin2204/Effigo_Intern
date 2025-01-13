public class Main {
    public static void main(String[] args) {
        AthleteThread f1=new AthleteThread(84291,Thread.MAX_PRIORITY,"WKG");
        AthleteThread s1=new AthleteThread(84241,Thread.MIN_PRIORITY,"WKL");
        f1.start();
        s1.start();
    }
}
