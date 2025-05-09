package sec06.assignment.client;

import common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalClientDemo04 extends AbstractHttpClient {
    public Flux<String> getProduct() {
        return this.httpClient.get().uri("/demo04/orders/stream").responseContent().asString();
    }
}
