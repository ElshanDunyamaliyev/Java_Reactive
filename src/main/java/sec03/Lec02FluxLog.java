package sec03;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec02FluxLog {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,5)
                .log("fluxtolog")
                .map(i -> Util.getFaker().name().firstName())
                .log("maptolog")
                .subscribe(Util.subscriber());
    }
}
