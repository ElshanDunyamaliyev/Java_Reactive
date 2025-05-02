package sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockSubscriber implements Subscriber<Integer> {
    private Subscription subscription;
    private static Integer balance = 1000;
    private static List<Integer> balanceArray = new ArrayList<>();
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println(integer);
        if(integer > 110){
            balanceArray.forEach(price -> balance-=price);
            balanceArray = balanceArray.stream().map(price -> integer).collect(Collectors.toList());
            balanceArray.forEach(price -> balance+=price);
            System.out.println("Final profit");
            System.out.println(balance - 1000);
            subscription.cancel();
        }else if(integer < 90){
            balanceArray.add(integer);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
