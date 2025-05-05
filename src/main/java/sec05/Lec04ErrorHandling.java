package sec05;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec04ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec04ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((ex,obj) -> log.error("Exception happened {}, skipping value {}",ex.getMessage(),obj))
                .subscribe(Util.subscriber());

        Mono.error(new RuntimeException("Invalid input"))
                .onErrorComplete() // hides error and send complete signal
                .subscribe(Util.subscriber());

        Mono.just(5)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorResume(ex -> fallBack()) // In case of error we can use fallback mechanism
                .onErrorReturn(ClassNotFoundException.class, -3) // This is for showing some static value
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(-1)
                .onErrorReturn(IllegalStateException.class, -2)
                .onErrorReturn(ClassNotFoundException.class, -3)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallBack() {
        return Mono.just(1);
    }
}
