package sec10;

import reactor.core.publisher.Flux;

public class Lec02Windowing {
//    In Project Reactor, window() is similar to buffer(),
//    but instead of collecting items into a List, it groups them into sub-Fluxes (i.e., each batch is itself a Flux<T>).
    public static void main(String[] args) {
        Flux.range(1, 6)
                .window(2)
                .subscribe(flux -> {
                    System.out.println("New Window:");
                    flux.subscribe(System.out::println);
                });
    }
}
