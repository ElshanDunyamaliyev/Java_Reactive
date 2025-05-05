package sec05;

import common.Util;
import reactor.core.publisher.Mono;

public class Lec05DefaultIfEmpty {
    public static void main(String[] args) {
        Mono.empty()
                .defaultIfEmpty("No value is returned")
                .subscribe(Util.subscriber());
    }
}
