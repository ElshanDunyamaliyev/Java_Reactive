package sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Faker faker;
    private Subscriber<? super String> subscriber;
    private boolean isCanceled;
    private static final int MAX_ITEMS = 10;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber){
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }
    @Override
    public void request(long requested) {
        if(isCanceled){
            return;
        }
        if(requested > MAX_ITEMS){
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCanceled = true;
            return;
        }
        log.info("Subscriber request {} items", requested);
        for (int i = 0; i < requested && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(faker.internet().emailAddress());
        }

        if(count >= MAX_ITEMS){
            log.info("No more items to produce");
            this.subscriber.onComplete();
            this.isCanceled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Subscriber canceled");
        this.isCanceled = true;
    }
}
