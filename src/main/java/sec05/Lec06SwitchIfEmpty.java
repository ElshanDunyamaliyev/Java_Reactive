package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec06SwitchIfEmpty {
    // Can be useful if we query redis and not found any product we can get product from db with this fallback
    public static void main(String[] args) {

        Flux.range(1,10)
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> fallback(){
        return Flux.range(100,3);
    }
}
