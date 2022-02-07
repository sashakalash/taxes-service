import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    private final static int CALCULATING_TIME_IN_SECONDS = 3;

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder stat = new LongAdder();

        new Shop(stat, executorService).start();
        new Shop(stat, executorService).start();
        new Shop(stat, executorService).start();

        try {
            executorService.awaitTermination(CALCULATING_TIME_IN_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Выручка со всех магазинов_______________________ " + stat.sum());
        executorService.shutdown();
    }
}