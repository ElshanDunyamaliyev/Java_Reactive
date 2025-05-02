package sec03.client;

import common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class FluxExternalClient extends AbstractHttpClient {
    public Flux<String> getProducts() {
        return this.httpClient.get().uri("/demo02/name/stream").responseContent().asString();
    }

    ;
}
