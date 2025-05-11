package sec10;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Batching {

    //Batching is a technique where multiple items are grouped together and processed as a single unit, instead of processing each item individually.
    // In Reactive Programming (like Project Reactor),
    // batching is often used to improve efficiency, reduce resource usage, or optimize I/O operations (like database writes or HTTP calls).

    //    📦 Why use batching?
    //✅ Reduces overhead (e.g., fewer DB/API calls)
    //✅ Improves throughput
    //✅ Allows bulk operations (e.g., insertMany)
    //✅ Controls memory and backpressure
    public static void main(String[] args) {
//        Flux.interval(Duration.ofMillis(200))
//                .take(20)
////                .buffer(3)
//                .buffer(Duration.ofMillis(400))
//                .subscribe(Util.subscriber());

        //        🕒 Time-based batching with bufferTimeout
        Flux.interval(Duration.ofMillis(100))
                .take(10)
                .concatWith(Flux.never())
                .bufferTimeout(3, Duration.ofMillis(300))
                .subscribe(System.out::println);

        Util.sleep(60);
    }
}
