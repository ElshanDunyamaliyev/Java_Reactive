package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class MyNote01FluxNext {
    public static void main(String[] args) {
        Flux.range(1,10)
                .next()
                .subscribe(Util.subscriber()); // Takes first value and converts flux to mono
    }
}
