package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

public class Lec01Backpressure {
    private static final Logger log = LoggerFactory.getLogger(Lec01Backpressure.class);
//    Queues
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        Flux<Integer> flux = Flux.generate(() -> {
            return 1;
        }, (state, sink) -> {
            sink.next(state);
            log.info("Producing {}",state);
            return state + 1;
        }).cast(Integer.class);

        flux.publishOn(Schedulers.boundedElastic()).map(i -> transform(i)).subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static int transform(int n){
        Util.sleep(1);
        return n;
    }
}
