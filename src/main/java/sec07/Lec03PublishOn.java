package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03PublishOn {
    private static final Logger log = LoggerFactory.getLogger(Lec03PublishOn.class);
    // Publisj on ozunden asagidaki pipeline tesir edir (Downstream)

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(i + 1);
            };
            fluxSink.complete();
        }).doOnNext((i) -> log.info("Executing by {}", Thread.currentThread().getName()))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext((i) -> log.info("Executing by 2 {}", Thread.currentThread().getName()))
                .subscribe(Util.subscriber());
    }
}
