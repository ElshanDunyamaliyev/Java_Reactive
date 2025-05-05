package sec05;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec07Transform {
    // In Project Reactor, .transform() is an operator that lets you apply a reusable transformation function to a Flux or Mono. It's a higher-order function — meaning it takes a function as an argument and applies it to the entire reactive chain.
    // ✅ Purpose of .transform()
    // Reuse logic across multiple Flux/Mono pipelines
    // Improve code readability and modularity
    // Apply complex operators in a clean way
    public static void main(String[] args) {
        Function<Flux<String>, Flux<String>> filterAndMap = flux ->
                flux.filter(s -> s.length() > 3)
                        .map(String::toUpperCase);

        Flux.just("apple", "dog", "cat", "banana")
                .transform(filterAndMap)
                .subscribe(System.out::println);

        // Also we can control transform with boolean flag

        var flag = false;

        Flux.just("apple1", "dog1", "cat1", "banana1")
                .transform(flag ? filterAndMap : Function.identity())
                .subscribe(System.out::println);

    }
}
