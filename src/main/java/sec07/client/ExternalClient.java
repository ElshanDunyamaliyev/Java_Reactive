package sec07.client;

import common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ExternalClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalClient.class);

    public Mono<String> getProduct(int productId) {
        return this.httpClient.get().uri("/demo01/product/" + productId).responseContent().asString().doOnNext(m -> log.info("next: {}",m)).next();
    }
}
