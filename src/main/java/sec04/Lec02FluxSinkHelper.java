package sec04;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import sec04.helper.NameGenerator;

public class Lec02FluxSinkHelper {
    public static void main(String[] args) {
        var generator = new NameGenerator();
        var fluxSink = Flux.create(generator);

        fluxSink.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}

