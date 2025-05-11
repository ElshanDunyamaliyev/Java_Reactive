package sec09;

import reactor.core.publisher.Mono;

public class Lec08Then {
    //In Project Reactor, the then() operator is used to ignore the result of a Mono or Flux and switch to another Mono that you specify.
    // It's typically used for sequencing — ensuring one reactive operation completes before starting the next.

    public static void main(String[] args) {
        Mono<Void> saveUser = Mono.fromRunnable(() -> System.out.println("User saved"));
        Mono<String> sendEmail = Mono.fromSupplier(() -> "Email sent");

        saveUser
                .then(sendEmail)
                .subscribe(System.out::println);

        Mono<String> first = Mono.just("First");
        Mono<String> second = Mono.just("Second");

        first
                .then(second)
                .subscribe(System.out::println);

        // Output //Second first will be ignored

        Mono.just("A")
                .then()                      // Ignores the result, returns Mono<Void>
                .thenReturn("Done")          // Emits "Done"
                .subscribe(System.out::println);

//        ⚠️ Summary
//          Operator	Description
//          then()	Ignores source, signals completion
//          then(Mono)	Sequences next Mono after current
//          thenReturn(x)	Emits a constant value after completion
    }
}
