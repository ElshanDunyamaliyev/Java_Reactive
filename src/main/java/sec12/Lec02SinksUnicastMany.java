package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Lec02SinksUnicastMany {
    // Allows only one subscriber, buffers data until that subscriber is attached.
    // If we try second subscriber it will throw illegal state exception
    public static void main(String[] args) {
//        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer();
//        for (int i = 0; i < 10; i++) {
//            objectMany.tryEmitNext(i + 1);
//        }
//        objectMany.tryEmitComplete();
//        Flux<Integer> cast = objectMany.asFlux().cast(Integer.class);
//        cast.subscribe(Util.subscriber("unicast-sub"));
        tryThreadSafety();
    }

    public static void tryThreadSafety(){
        Sinks.Many<Object> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = unicastSink.asFlux().cast(Integer.class);
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
//               unicastSink.tryEmitNext(finalI); // tryemitnext if error happens just pass that
               unicastSink.emitNext(finalI,(signalType, emitResult) -> {
                   return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
               });
            });
        }

        Util.sleep(5);

        System.out.println("Size of the list " + list.size());
    }
}
