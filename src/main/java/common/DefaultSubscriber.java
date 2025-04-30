package common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} received: {}",this.name,item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("receiver: {} - {}",this.name,throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed - {}",this.name);
    }
}
