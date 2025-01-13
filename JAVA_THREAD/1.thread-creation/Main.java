public class Main {

    // public static class ChildThreadTask implements Runnable {

    // }

    public static void main(String[] args) {
        Thread childt=new Thread(()->count(),"child");
        childt.start();
        count();
    }

    public static void count() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}