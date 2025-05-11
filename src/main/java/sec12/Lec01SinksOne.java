package sec12;

import common.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinksOne {
    public static void main(String[] args) {
        var sink = Sinks.one();

        sink.tryEmitValue("test");
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber());
    }
}
