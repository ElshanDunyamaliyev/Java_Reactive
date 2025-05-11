package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02ConcatWith {
//    In Project Reactor, concatWith() is used to append another Publisher (like a Mono or Flux) to the current one.
//    Unlike merge, it ensures sequential emission — the second source only starts after the first completes.
    public static void main(String[] args) {
        var flux1 = Flux.just(1,2,3).delayElements(Duration.ofMillis(100));
        var flux2 = Flux.just(4,5,6).delayElements(Duration.ofMillis(100));
        flux1.concatWith(flux2).subscribe(Util.subscriber());

        Util.sleep(10);
    }
}
