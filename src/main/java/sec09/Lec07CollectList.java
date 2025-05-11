package sec09;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec07CollectList {
//    In Project Reactor, collectList() is a terminal operation that
//    collects all items emitted by a Flux into a List and emits that list as a Mono<List<T>>.
    public static void main(String[] args) {
        var flux1 = Flux.just(1,2,3,4);
        Mono<List<Integer>> listMono = flux1.collectList();
        listMono.subscribe(Util.subscriber());
    }
}
