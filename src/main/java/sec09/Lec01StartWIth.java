package sec09;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01StartWIth {
//    In Project Reactor, the startWith() operator is used to prepend one or more values (or another publisher) to the beginning of a Flux or Mono.
    public static void main(String[] args) {
        Flux.just(1,2,3)
                .startWith(-1,0)
                .subscribe(Util.subscriber());
    }
}

