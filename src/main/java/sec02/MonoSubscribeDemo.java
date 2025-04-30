package sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribeDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoSubscribeDemo.class);

    public static void main(String[] args) {
//        var monoSubscribe = Mono.just(1);
//        var monoSubscribe = Mono.just(1).map(res -> res + "a"); // We can use map and stream operations
        var monoSubscribe = Mono.just(1).map(res -> res/0);
        monoSubscribe.subscribe(i -> log.info("Received : {}", i),
                error -> log.error("Error: {}", error.getMessage()),
                () -> log.info("Completed"),
                subscription -> subscription.request(3));
    }
}
