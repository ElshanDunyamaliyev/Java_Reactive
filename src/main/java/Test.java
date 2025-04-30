import reactor.core.publisher.Mono;

public class Test {
    public static void main(String[] args) {
    }

    public static Mono<String> getsA(){

        return Mono.just("sa");
    }
}
