package sec12;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinksOne {
    private static final Logger log = LoggerFactory.getLogger(Lec01SinksOne.class);
    // Sinki processor kimi nezerde tuta bilerik. Beleki sink hec bir subscriber olmadan data emit ede biler
    //Purpose of Sink
    //The primary goal of sink is to connect imperative programming where data flow control is directly done by you with reactive programming that is more about responding to data as it comes in.
    // It allows you to go from reactive stream creation at imperative code level.

    public static void main(String[] args) {
        var sink = Sinks.one();

//        sink.tryEmitValue("test");  // fail fast
        // Burda mono olduguna gorer sadece 1 element emit (gondere) ede biler
        var mono = sink.asMono();
        sink.emitValue("hi",(signalType, emitResult) -> {
            log.info("hi");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        });
        sink.emitValue("hello",(signalType, emitResult) -> {
            log.info("hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return true; // if true try to emit data
        });
        mono.subscribe(Util.subscriber("sam"));
//        mono.subscribe(Util.subscriber("test"));
    }
}
