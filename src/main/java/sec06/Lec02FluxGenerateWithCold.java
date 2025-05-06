package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02FluxGenerateWithCold {
    private static final Logger log = LoggerFactory.getLogger(Lec02FluxGenerateWithCold.class);

    public static void main(String[] args) {
        // Turning cold publisher to hot with share method
//        var fluxGenerate = getMovies().share();
        var fluxGenerate = getMovies().publish().refCount(2);
        // share = publish + refCount(1) will start publishing when specified amount of subscriber joined it has
        // to be minimum 1
        Util.sleep(2);
        fluxGenerate.subscribe(Util.subscriber("sam"));
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
