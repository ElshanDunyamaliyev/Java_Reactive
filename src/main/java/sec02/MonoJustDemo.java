package sec02;

import reactor.core.publisher.Mono;

public class MonoJustDemo {
    public static void main(String[] args) {
        System.out.println(sendMessage());
    }

    public static String sendMessage(){
        Mono<String> monoJust = Mono.just("sa");
        return monoJust.block();
    }
}
