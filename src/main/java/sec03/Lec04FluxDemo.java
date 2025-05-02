package sec03;

import common.AbstractHttpClient;
import common.Util;
import reactor.core.publisher.Flux;
import sec03.client.FluxExternalClient;

import java.time.Duration;

public class Lec04FluxDemo extends AbstractHttpClient {

    public static void main(String[] args) throws InterruptedException {
        var client = new FluxExternalClient();
        client.getProducts().subscribe(Util.subscriber("sub1"));
        client.getProducts().subscribe(Util.subscriber("sub2"));
        Thread.sleep(Duration.ofSeconds(10));
    }
}
