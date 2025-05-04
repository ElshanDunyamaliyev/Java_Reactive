package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01Operators {
    public static void main(String[] args) {
        // The .handle() operator in Project Reactor is a powerful and flexible operator used to conditionally emit elements,
        // transform them, or skip them — all in a single step. It is like a combination of .filter() and .map(), with added control.
//        Flux.range(1, 10)
//                .handle((item, sink) -> {
//                    switch (item) {
//                        case 1 -> sink.next(-2);
//                        case 4 -> {
//                        }
//                        case 7 -> sink.error(new RuntimeException("Invalid value"));
//                        default -> sink.next(item);
//                    }
//                }).subscribe(Util.subscriber());

        Flux.generate(synchronousSink -> {
            var country = Util.getFaker().country().name();
            synchronousSink.next(country);
        }).cast(String.class).handle((item, sink) -> {
            if (!item.equalsIgnoreCase("canada")) {
                sink.next(item);
            }else{
                sink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
