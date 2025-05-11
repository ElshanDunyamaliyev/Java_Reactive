package sec11;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec02Retry {
    // Resubscribe after onerror signal

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            };
            fluxSink.error(new RuntimeException("Unexpected error"));
        }).retry(1).subscribe(Util.subscriber());
    }
}
