package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxTake {
    public static void main(String[] args) {
//        Flux.range(1,10)
//                .log("take")
//                .take(3)  // works like limit only 3 item will emit
//                .log("sub")
//                .subscribe(Util.subscriber());

//        Flux.range(1,10)
//                .log("take")
//                .takeWhile(i -> i < 5)  // takes predicate. works until conditions not met
//                .log("sub")
//                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .log("take")
                .takeUntil(i -> i < 5)  // takes predicate. works until conditions met and takes first item too
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
