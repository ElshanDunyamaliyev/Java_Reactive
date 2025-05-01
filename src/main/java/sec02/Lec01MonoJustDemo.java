package sec02;

import reactor.core.publisher.Mono;
import sec01.subscriber.SubscriberImpl;

public class Lec01MonoJustDemo {
    public static void main(String[] args) {
        Mono<String> publisher = Mono.just("elshan@gmail.com"); // Just is a static method that creates mono
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
