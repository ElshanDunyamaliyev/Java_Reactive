package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Zip {
//    In Project Reactor, zip is used to combine elements from multiple publishers pairwise, i.e., by index.
//    It waits for all sources to emit an element at the same index, then combines those into a tuple (or via a function).
    public static void main(String[] args) {
        var flux1 = Flux.just(1,2,3);
        var flux2 = Flux.just('A','B','C');
//        flux1.zipWith(flux2).subscribe(Util.subscriber());
        // Output received: [1,A],[2,B],[3,C]
        flux1.zipWith(flux2,(integer, character) -> integer + ":" + character).subscribe(Util.subscriber());
        // Output received: 1:A
    }
}
