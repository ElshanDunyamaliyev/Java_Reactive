package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec03FluxCreateBackpressureProblem {
    private static final Logger log = LoggerFactory.getLogger(Lec03FluxCreateBackpressureProblem.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        Flux<Integer> flux = Flux.create((sink) -> {
            for (int j = 1; j <= 500 && !sink.isCancelled(); j++) {
                log.info("generating value {}", j);
                sink.next(j);
                Util.sleepCustom(Duration.ofMillis(50));
            }

        }).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        flux
                .publishOn(Schedulers.boundedElastic())
                .map(i -> transform(i))
                .subscribe();

        Util.sleep(60);
    }

    private static int transform(int n) {
        log.info("Received value {}",n);
        Util.sleep(1);
        return n;
    }
}
