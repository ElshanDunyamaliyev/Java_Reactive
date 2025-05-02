package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec01FluxJust {
    public static void main(String[] args) {
        var list = List.of(5,6,7,8);
        var stream = list.stream();
        Flux.just(1,2,3,4).subscribe(Util.subscriber());
        Flux.fromArray(List.of(1,2,3,4).toArray()).subscribe(Util.subscriber());
        Flux.fromStream(stream).subscribe(Util.subscriber());
        Flux.fromStream(stream).subscribe(Util.subscriber());
        Flux.range(1,10).map(i -> Util.getFaker().name().name()).subscribe(Util.subscriber());
    }
}
