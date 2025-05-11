package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06ConcatMap {
    // Works like flatMap but gives in preserve order
    public static void main(String[] args) {
        Flux.range(1, 3)
                .concatMap(i -> Flux.just(i * 10).delayElements(Duration.ofMillis(100)))
                .subscribe(System.out::println);

        Util.sleep(10);
    }
}
