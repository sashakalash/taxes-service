import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    final static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        LongAdder stat = new LongAdder();

        Shop shop1 = new Shop(stat, executorService);
        Shop shop2 = new Shop(stat, executorService);
        Shop shop3 = new Shop(stat, executorService);

        threadPool.submit(shop1);
        threadPool.submit(shop2);
        threadPool.submit(shop3);

        System.out.println("Выручка первого магазина_______________________ " + shop1.getProfit());
        System.out.println("Выручка второго магазина_______________________ " + shop2.getProfit());
        System.out.println("Выручка третьего магазина_______________________ " + shop3.getProfit());
        System.out.println("______________________________________________");
        System.out.println("Выручка со всех магазинов_______________________ " + Long.sum(Long.sum(shop1.getProfit(), shop2.getProfit()), shop3.getProfit()));
        threadPool.shutdown();
    }
}