package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec04BufferStrategy {
    private static final Logger log = LoggerFactory.getLogger(Lec04BufferStrategy.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        Flux<Integer> flux = Flux.create((sink) -> {
                    for (int j = 1; j <= 500 && !sink.isCancelled(); j++) {
                        log.info("generating value {}", j);
                        sink.next(j);
                        Util.sleepCustom(Duration.ofMillis(50));
                    }

                }).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        // we can give default strategy when creating flux
//        Flux<Integer> flux = Flux.create((sink) -> {
//                    for (int j = 1; j <= 500 && !sink.isCancelled(); j++) {
//                        log.info("generating value {}", j);
//                        sink.next(j);
//                        Util.sleepCustom(Duration.ofMillis(50));
//                    }
//
//                }, FluxSink.OverflowStrategy.BUFFER).cast(Integer.class)
//                .subscribeOn(Schedulers.parallel());

        flux
//                .onBackpressureBuffer() // Adds buffer between publisher and subscriber buffer acts as a subscriber and gets all data and subscriber gets data from buffer
//                .onBackpressureBuffer(10) // If exceed maxSize throw error
//                .onBackpressureDrop() // Receive when subscriber requested amount and drops after that and again receives when it is available
                .onBackpressureLatest() // Drops old items and give last item to subscriber when subscriber is available
//                .onBackpressureError() // sends signal to producer to cancel stream because of backpressure
                .log()
                .limitRate(2)
                .publishOn(Schedulers.boundedElastic())
                .map(i -> transform(i))
                .subscribe();

        Util.sleep(60);
    }

    private static int transform(int n) {
        log.info("Received value {}",n);
        Util.sleep(1);
        return n;
    }
}
