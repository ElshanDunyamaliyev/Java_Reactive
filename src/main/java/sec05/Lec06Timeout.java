package sec05;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec06Timeout {
    //    In Project Reactor, .timeout() is an operator used to fail a reactive sequence
    //    if it takes too long to emit an item. It's a way to protect your application from hanging
    //    or slow publishers by specifying a time limit for data to arrive.
    // In this case it will wait 3 seconds and if no value accepted it will emit 5 as fallback value
    public static void main(String[] args) {

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5))
                .timeout(Duration.ofSeconds(3),fallBack(3)) // We can also handle our fallback logic in here
                .timeout(Duration.ofSeconds(5),fallBack(5)) // We can have multiple timeouts
                .timeout(Duration.ofSeconds(1),fallBack(-1)) // The timeout which has minimum amount will get executed
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5))
                .timeout(Duration.ofSeconds(3),fallBack(3)) // We can also handle our fallback logic in here
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5))
                .timeout(Duration.ofSeconds(3))
                .onErrorReturn(5)
                .subscribe(Util.subscriber());

        Util.sleep(50);
    }

    private static Mono<Integer> fallBack(int value) {
        return Mono.just(value);
    }
}
