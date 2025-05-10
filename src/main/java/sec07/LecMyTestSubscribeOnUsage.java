package sec07;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class LecMyTestSubscribeOnUsage {
    public static void main(String[] args) {
        log("Main thread started");

        Mono.fromCallable(LecMyTestSubscribeOnUsage::blockingOperation)
                .subscribeOn(Schedulers.boundedElastic()) // move blocking call to separate thread
                .doOnNext(data -> log("Received: " + data))
                .subscribe();

        log("Main thread continues...");

        // Keep main thread alive to let async work complete
        sleep(3000);
    }

    private static String blockingOperation() {
        log("Starting blocking operation...");
        sleep(2000); // Simulate blocking (e.g., file/db access)
        return "Result from blocking call";
    }

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " → " + msg);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
