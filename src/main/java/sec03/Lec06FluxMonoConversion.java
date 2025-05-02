package sec03;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06FluxMonoConversion {
    public static void main(String[] args) {
        Flux.from(Mono.just(5)); // Converts from mono to flux
        Mono.from(Flux.just(5)); // Converts from flux to mono
        Flux.just(5).next() ; // Converts from flux to mono

    }
}
