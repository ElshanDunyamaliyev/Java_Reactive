package sec07;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec05PublishOnSubscribeOnTogether {
    public static void main(String[] args) {
        Mono.fromCallable(Lec05PublishOnSubscribeOnTogether::getDataFromBlockingSource)
                .doOnNext(data -> log("Fetched: " + data))
                .subscribeOn(Schedulers.boundedElastic()) // Move source to I/O thread
                .publishOn(Schedulers.parallel())          // Move transformation to CPU-bound thread
                .map(Lec05PublishOnSubscribeOnTogether::complexTransformation)
                .doOnNext(result -> log("After transformation: " + result))
                .subscribe(result -> log("Subscriber got: " + result));

        log("Main thread continues...");

        sleep(4000); // Keep main thread alive
    }

    private static String getDataFromBlockingSource() {
        log("Getting data from DB...");
        sleep(1000); // Simulate slow source
        return "user-data";
    }

    private static String complexTransformation(String data) {
        log("Transforming data...");
        sleep(1000); // Simulate heavy CPU work
        return data.toUpperCase();
    }

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " → " + msg);
    }

    private static void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}
