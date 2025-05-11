package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03Merge {
//    In Project Reactor, merge() is used to combine multiple publishers concurrently,
//    emitting items as they arrive — interleaving values from all sources based on availability.
    public static void main(String[] args) {
        var flux1 = Flux.just(1,2,3).delayElements(Duration.ofMillis(100));
        var flux2 = Flux.just(4,5,6).delayElements(Duration.ofMillis(100));
        flux1.mergeWith(flux2).subscribe(Util.subscriber());

        Util.sleep(10);
    }
}
