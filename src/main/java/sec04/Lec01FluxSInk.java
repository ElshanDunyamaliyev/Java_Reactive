package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxSInk {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
           while (!Util.getFaker().country().name().equalsIgnoreCase("canada")){
               fluxSink.next(Util.getFaker().country().name());
           }
           fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
