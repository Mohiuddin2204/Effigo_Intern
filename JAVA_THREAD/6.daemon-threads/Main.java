import java.util.*;
class MemoryIntensiveTask implements Runnable {

    @Override
    public void run() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        try {
            for (int i = 0; i < 10; i++) { // Reduced number of iterations
                numbers.add(random.nextInt());
                if (i % 1 == 0) {
                    Thread.sleep(150); // Adds a slight delay every 1000 iterations
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Memory intensive task was interrupted");
        }

        System.out.println("Finished generating random numbers");
    }
}

class MemoryMonitorDaemon implements Runnable {

    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();

        while (true) {
            try {
                long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Memory Usage: " + usedMemory + " bytes");
                Thread.sleep(5000); // Check memory usage every 5 seconds
            } catch (InterruptedException e) {
                System.out.println("Memory monitor interrupted");
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Thread memoryMonitorThread = new Thread(new MemoryMonitorDaemon());
        memoryMonitorThread.setDaemon(false);
        memoryMonitorThread.start();

        Thread memoryIntensiveTaskThread = new Thread(new MemoryIntensiveTask());
        memoryIntensiveTaskThread.start();
    }
}