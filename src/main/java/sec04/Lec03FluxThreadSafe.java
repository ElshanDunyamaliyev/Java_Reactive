package sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

import java.time.Duration;
import java.util.ArrayList;

public class Lec03FluxThreadSafe {
    private static final Logger logger = LoggerFactory.getLogger(Lec03FluxThreadSafe.class);
    public static void main(String[] args) throws InterruptedException {
        addListWithArrayList();
        addListWithFluxSink();
    }

    public static void addListWithArrayList() throws InterruptedException {
        var list = new ArrayList<>();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        logger.info("list size is : {}",list.size());
        Thread.sleep(Duration.ofSeconds(3));
    }

    public static void addListWithFluxSink() throws InterruptedException {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var sink = Flux.create(generator);
        sink.subscribe((el) -> list.add(el));

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        Thread.sleep(Duration.ofSeconds(3));
        logger.info("list size is : {}",list.size());
    }
}
