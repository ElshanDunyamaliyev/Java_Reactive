package sec02;

import common.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoErrorDemo {
    public static void main(String[] args) {
//        monoError(1).subscribe(Util.subscriber());
//        monoError(2).subscribe(Util.subscriber());
//        monoError(3).subscribe(Util.subscriber());
//        monoError(4).subscribe(s -> System.out.println(s)); // onErrorDropped - Here if we just print to console and dont provide error handler reactor just throw Error
        monoError(4).subscribe(s -> System.out.println(s), e -> System.out.println(e)); // onErrorDropped - Here if we just print to console and dont provide error handler reactor just throw Error
    }

    public static Mono<String> monoError(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("elshan");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found with this id"));
        };
    }
}
