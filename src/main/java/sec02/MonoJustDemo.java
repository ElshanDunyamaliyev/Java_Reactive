package sec02;

import reactor.core.publisher.Mono;
import sec01.subscriber.SubscriberImpl;

public class MonoJustDemo {
    public static void main(String[] args) {
        Mono<String> publisher = Mono.just("elshan@gmail.com");
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
