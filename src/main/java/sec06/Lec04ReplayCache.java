package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ReplayCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04ReplayCache.class);

    public static void main(String[] args) {
        // Sometimes when subscriber join to publisher they have to see old data too
        // We can do it with replay
//        var stockFlux = stockStream().publish().autoConnect(0); // miss old data
        var stockFlux = stockStream().replay(2).autoConnect(0); // miss old data

        Util.sleep(4);

        log.info("sam joining");
        stockFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleep(4);

        log.info("mike joining");
        stockFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleep(15);

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.getFaker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
