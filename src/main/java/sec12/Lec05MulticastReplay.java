package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05MulticastReplay {
    public static void main(String[] args) {
        Sinks.Many<Object> multicastSink = Sinks.many().replay().all();
        Flux<Integer> flux = multicastSink.asFlux().cast(Integer.class);
        flux.subscribe(Util.subscriber("Early"));
        multicastSink.tryEmitNext(1);
        multicastSink.tryEmitNext(2);
        multicastSink.tryEmitNext(3);
        Util.sleepCustom(Duration.ofSeconds(2));
        flux.subscribe(Util.subscriber("Late"));
        multicastSink.tryEmitNext(4);
        Util.sleep(3);

        Util.sleep(10);

        // With replay late subscribers also get the values which they missed
    }
}
