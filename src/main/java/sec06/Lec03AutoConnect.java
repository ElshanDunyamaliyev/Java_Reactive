package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03AutoConnect {
    private static final Logger log = LoggerFactory.getLogger(Lec03AutoConnect.class);

    public static void main(String[] args) {
        // Auto connect works exactly like publish + refCount(1) but one difference is
        // if there is no subscriber left publish + refCount(1) stops
        // but autoConnect even publish if there is no subscriber left
//        var fluxGenerate = getMovies().publish().autoConnect();
        // Also we dont have to wait any subscriber to join like tv channel just put 0 inside autoConnect
        var fluxGenerate = getMovies().publish().autoConnect(0);
        Util.sleep(2);
        fluxGenerate.take(3).subscribe(Util.subscriber("sam"));
        Util.sleep(3);
        fluxGenerate.take(4).subscribe(Util.subscriber("mike"));
        Util.sleep(15);
    }

    private static Flux<String> getMovies(){
        return Flux.generate(() -> {
            return 1;
        },(state,sink) -> {
            sink.next("movie " + state);
            log.info("Movie " + state);
            return state + 1;
        }).take(10).delayElements(Duration.ofSeconds(1)).cast(String.class);
    }
}
