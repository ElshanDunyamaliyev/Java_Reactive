package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05FlatMap {
    // In Project Reactor, flatMap is used to transform each item emitted by a source Flux (or Mono) into another Publisher,
//    then flatten the resulting publishers into a single Flux.
//    It's especially powerful when dealing with asynchronous or nested streams, like API calls, DB operations, or chained processing.
//    ⚠️ Note on Concurrency
//    flatMap is concurrent — it doesn’t guarantee order of the resulting items.
//
//  If you need to preserve order, use concatMap instead.
    public static void main(String[] args) {
//        Flux.just("A", "B")
//                .flatMap(letter -> Flux.just(letter + "1", letter + "2"))
//                .subscribe(System.out::println);

        Flux.range(1, 3)
                .flatMap(i -> Flux.just(i * 10).delayElements(Duration.ofMillis(100)))
                .subscribe(System.out::println);

        Util.sleep(10);
    }
}
