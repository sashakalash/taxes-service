import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Shop extends Thread {
    private LongAdder stat;
    private ExecutorService executorService;

    public Shop(LongAdder stat, ExecutorService executorService) {
        this.stat = stat;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        IntStream.range(0, 500)
                .forEach(i -> executorService.submit(() -> stat.add(i)));
    }
}