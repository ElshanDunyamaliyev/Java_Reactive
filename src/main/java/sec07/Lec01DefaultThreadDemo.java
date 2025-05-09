package sec07;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01DefaultThreadDemo {
    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        });
        // By default this will be executed by main thread
//        flux.subscribe(Util.subscriber());

        // Pass it to other thread to execute
        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);
    }
}
