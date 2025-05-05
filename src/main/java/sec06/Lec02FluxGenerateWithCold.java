package sec06;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02FluxGenerateWithCold {
    public static void main(String[] args) {
        var fluxGenerate = getMovies();
        Util.sleep(3);
        fluxGenerate.subscribe(Util.subscriber("sam"));
        Util.sleep(3);
        fluxGenerate.subscribe(Util.subscriber("mike"));
        Util.sleep(15);
    }

    private static Flux<String> getMovies(){
        return Flux.generate(() -> {
            return 1;
        },(state,sink) -> {
           sink.next("movie " + state);
           return state + 1;
        }).take(10).timeout(Duration.ofSeconds(1)).cast(String.class);
    }
}
