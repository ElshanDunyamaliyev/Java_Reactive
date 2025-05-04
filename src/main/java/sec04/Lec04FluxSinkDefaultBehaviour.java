package sec04;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import sec01.subscriber.SubscriberImpl;

public class Lec04FluxSinkDefaultBehaviour {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxSinkDefaultBehaviour.class);

    public static void main(String[] args) {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
//            for (int i = 0; i < 10; i++) {
//                var name = Util.getFaker().name().firstName();
//                log.info("Generated name {}", name); // By default generate names even if no subscriber subcribed to producer. When generates names it add names to the queue
//                fluxSink.next(name);
//            }
//            fluxSink.complete();

            fluxSink.onRequest(request -> {
                for (int i = 0; i < request; i++) {
                    var name = Util.getFaker().name().firstName();
                    log.info("Generated name {}", name); // With this approach only creates when subscriber subscribes and requests
                    fluxSink.next(name);
                }
            });


        }).subscribe(subscriber);

        Util.sleep();
        subscriber.getSubscription().request(3);
    }
}
