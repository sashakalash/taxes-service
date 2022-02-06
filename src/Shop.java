import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Shop extends Thread {
    LongAdder stat;
    ExecutorService executorService;
    private final int CALCULATING_TIME_IN_SECONDS = 3;

    public Shop(LongAdder stat, ExecutorService executorService) {
        this.stat = stat;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        IntStream.range(0, 500)
                .forEach(i -> executorService.submit(() -> stat.add(i)));
        try {
            executorService.awaitTermination(CALCULATING_TIME_IN_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getProfit() {
        return stat.sum();
    }
}