package sec10;

import reactor.core.publisher.Flux;

public class Lec03GroupBy {
    //In Project Reactor, groupBy() is a powerful operator that splits a Flux<T> into multiple GroupedFlux<K, V>, where:
    //
    //K is the key to group by (like a category),
    //
    //V is the value (usually same as the original element or a transformation of it).
    //
    //It's like SQL's GROUP BY but in a reactive, stream-based way.

    public static void main(String[] args) {
        Flux.range(1, 6)
                .groupBy(i -> i % 2 == 0 ? "EVEN" : "ODD")
                .subscribe(groupedFlux -> {
                    groupedFlux.subscribe(value ->
                            System.out.println("Group: " + groupedFlux.key() + ", Value: " + value)
                    );
                });
    }
}
