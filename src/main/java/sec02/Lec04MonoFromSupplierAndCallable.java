package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec04MonoFromSupplierAndCallable {
    private static final Logger log = LoggerFactory.getLogger(Lec04MonoFromSupplierAndCallable.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3,4);
        Mono.just(sum(list)).subscribe(Util.subscriber());
        Mono.just(sum(list));  // Problem here is that if we subscribe or dont subscribe to this publisher it will still calculate the sum we have to delay that
//                .subscribe(Util.subscriber());
        // Difference between supplier and callable - supplier has throw exception in method signature, callable doesnt have
//        Mono.fromSupplier(() -> sum(list)).subscribe(Util.subscriber()); // With from Supplier we are delaying that operation
        Mono.fromCallable(() -> sum(list)).subscribe(Util.subscriber());
    }

    // Lest assume we have computed intensive operation
    public static int sum(List<Integer> list){
        log.info("Calculating sum of given list {}", list);
        return list.stream().mapToInt(a -> a * 2).sum();
    }
}
