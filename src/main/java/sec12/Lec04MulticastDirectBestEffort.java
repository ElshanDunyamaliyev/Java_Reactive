package sec12;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec04MulticastDirectBestEffort {
    private static final Logger log = LoggerFactory.getLogger(Lec04MulticastDirectBestEffort.class);

    public static void main(String[] args) {
        // Burda 2 subscriber qosuruq biri yavas process edir digeri suretli bu halda yavas olan digerine de tesir edir
        // burda sadece 16 elementi vere bilir o da buffer size a gore
        System.setProperty("reactor.bufferSize.small","16");
//        var sink = Sinks.many().multicast().directBestEffort(); // focus on fast subscriber ignore slow one -
//        burda slow olana backpressure buffer qosuruq ki ordan gotursun valuelari
        var sink = Sinks.many().multicast().directAllOrNothing(); // if one subscribe is slow dont give to anyone
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("fast"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("slow"));

        for (int i = 0; i <100 ; i++) {
            Sinks.EmitResult emitResult = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i,emitResult);
        }

        Util.sleep(5);
    }
}
