package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec06MonoFromCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCompletableFuture.class);

    public static void main(String[] args) {
//        Mono.fromFuture(getName()).subscribe(Util.subscriber());
//        Mono.fromFuture(getName()); // Problem with completable future is that it will still compute although we didnt provide subscriber so it is not lazy by default
//                .subscribe(Util.subscriber());
//        Mono.fromFuture(() -> getName()).subscribe(Util.subscriber()); // But with writing supplier of completable future we can delay that
        Mono.fromFuture(() -> getName());

        Util.sleep(); // Sleeping main thread to see result of completable future
    }

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating name");
            return Util.getFaker().programmingLanguage().name();
        });
    }
}
