package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02LimitRate {
    private static final Logger log = LoggerFactory.getLogger(Lec02LimitRate.class);
    //    Queues
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.generate(() -> {
            return 1;
        }, (state, sink) -> {
            sink.next(state);
            log.info("Producing {}",state);
            return state + 1;
        }).cast(Integer.class);

        flux
                .limitRate(5) // Tells producer produce 5 item and wait again works 75 % queue capacity
                .publishOn(Schedulers.boundedElastic())
                .map(i -> transform(i))
                .subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static int transform(int n){
        Util.sleep(1);
        return n;
    }
}
