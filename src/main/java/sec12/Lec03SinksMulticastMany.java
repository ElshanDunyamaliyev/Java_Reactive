package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.ArrayList;

public class Lec03SinksMulticastMany {
    // This emits data but dont wait for subscriber also can accept multiple subscribers
    public static void main(String[] args) {
        Sinks.Many<Object> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = multicastSink.asFlux().cast(Integer.class);
        flux.subscribe(Util.subscriber("Early"));
        multicastSink.tryEmitNext(1);
        multicastSink.tryEmitNext(2);
        multicastSink.tryEmitNext(3);
        Util.sleepCustom(Duration.ofSeconds(2));
        flux.subscribe(Util.subscriber("Late"));
        multicastSink.tryEmitNext(4);
        Util.sleep(3);

        // Warmup behaviour: if there is no subscriber joins and sink emit values
        // it will save values in bounded queue(capacity default 256) and if 3 subscriber
        // subscribe it will give messages only first one and after that when emits other values
        // all subscribers can also get that values too.
    }
}
