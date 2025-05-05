package sec06;

import common.Util;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

public class Lec01FluxCreateWithCold {
    // Flux create works with one subscriber only
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();
        var fluxCreate = Flux.create(nameGenerator);

        fluxCreate.subscribe(Util.subscriber("sub1"));
        fluxCreate.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 2; i++) {
            nameGenerator.generate();
        }
    }

}
