package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    public static void main(String[] args) {
        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");

        var flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        })
                .doFirst(() -> log.info("First 1"))
                .doFirst(() -> log.info("is virtual : {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("Second 1"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("subscriber 1"));
        Thread.ofPlatform().start(runnable); // Second 1 executed by the thread we created because it is under subscribe On other tasks will go to boundedElastic

//        flux.subscribeOn(Schedulers.immediate()).subscribe(Util.subscriber()); // Execute it by current thread
//        flux.subscribeOn(Schedulers.boundedElastic()).subscribe(Util.subscriber()); // For Network/ IO/ time consuming and blocking operations
//        flux.subscribeOn(Schedulers.parallel()).subscribe(Util.subscriber()); // For cpu intensive tasks



        Util.sleep(5);

    }
}
