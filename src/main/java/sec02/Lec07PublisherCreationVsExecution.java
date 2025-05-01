package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07PublisherCreationVsExecution {
    private static final Logger log = LoggerFactory.getLogger(Lec07PublisherCreationVsExecution.class);

    public static void main(String[] args) {
        getName().subscribe();
//        Just for clarification when we are using supplier, callable, runnable we create publisher we don't execute it immediately
//        because creating producer is lightweight task and it will only executed when subscriber subscribe to producer
    }

    public static Mono<String> getName(){
        return Mono.fromSupplier(() -> {
            log.info("Just logging");
            Util.sleep();
            return Util.getFaker().animal().name();
        });
    }
}
