package sec05;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03Delay {
    public static void main(String[] args) {
        Flux.range(1,10)
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Util.subscriber());

        Util.sleep(30);
    }
}
