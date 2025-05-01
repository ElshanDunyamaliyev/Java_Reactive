package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec08MonoDefer {
    private static final Logger log = LoggerFactory.getLogger(Lec08MonoDefer.class);

    public static void main(String[] args) {
        Mono.defer(() -> createPublisher()); // With defer we can also delay creation of publisher, it only will get created when subscriber subscribe to publisher
        Mono.defer(() -> createPublisher()).subscribe(Util.subscriber());
    }

    public static Mono<Integer> createPublisher(){
        log.info("Creating publisher");
        Util.sleep();
        return Mono.fromSupplier(() -> calculate(List.of(1,2,3,4,5)));
    }

    public static int calculate(List<Integer> list){
        log.info("Calculating sum of list");
        Util.sleep();
        return list.stream().mapToInt(a -> a*2).sum();
    }
}
