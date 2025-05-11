package sec11;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01Repeat {
    // Resubscribe after oncomplete signal

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            };
            fluxSink.complete();
        }).repeat(1).subscribe(Util.subscriber());
    }
}
