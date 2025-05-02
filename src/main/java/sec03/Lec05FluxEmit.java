package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05FluxEmit {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(500))
                .subscribe(Util.subscriber());

        Flux.empty().subscribe(Util.subscriber());
        Flux.error(new RuntimeException("Error occured")).subscribe(Util.subscriber());

        Flux.defer(() -> Flux.just(5)).subscribe(Util.subscriber()); // Delays flux's creation till subscribe subscribe to publisher

        Thread.sleep(Duration.ofSeconds(10));
    }
}
