package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {
//        Flux.generate(synchronousSink -> {
//           synchronousSink.next(2);
//           synchronousSink.complete(); // This way with generate we can only emit one value
//        }).subscribe(Util.subscriber());

//        Flux.generate(synchronousSink -> {
//            synchronousSink.next(2); // if we dont call complete it will keep produce 2 forever
//        }).subscribe(Util.subscriber());

//        Flux.generate(synchronousSink -> {
//                    synchronousSink.next(2); // so can use take or use complete or throw error
//                })
//                .take(2)
//                .subscribe(Util.subscriber());

//        Flux.generate(() -> 0, (state, sink) -> {
//            sink.next("State " + state);
//            if (state == 5) sink.complete();
//            return state + 1;
//        }).subscribe(Util.subscriber());

//                Flux.generate(synchronousSink -> {
//                    synchronousSink.next(Util.getFaker().country().name()); /
//                })
//                .takeWhile(country -> !country.equals("Canada"))
//                .subscribe(Util.subscriber());

        Flux.generate(() -> 0,(state, synchronousSink) -> {
                    var countryName = Util.getFaker().country().name();
                    synchronousSink.next(countryName);
                    if(countryName.equalsIgnoreCase("canada") || state >= 9) synchronousSink.complete();
                    return state + 1;
                })
                .subscribe(Util.subscriber());

    }
}
