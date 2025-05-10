package sec07;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LecMyTestPublishOnUsage {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(i -> log("Source emitted: " + i)) // Runs on main thread
                .publishOn(Schedulers.parallel()) // Switch thread for downstream
                .map(LecMyTestPublishOnUsage::complexTransformation)
                .doOnNext(result -> log("Transformed result: " + result))
                .subscribe(result -> log("Subscriber received: " + result));

        sleep(4000); // Keep main thread alive
    }

    private static String complexTransformation(int i) {
        log("Starting complex transformation for " + i);
        sleep(1000); // Simulate heavy work
        return "Item-" + i + "-processed";
    }

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " → " + msg);
    }

    private static void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}
