package sec03.assignment;

import common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class FluxAssignmentClient extends AbstractHttpClient {

    public Flux<String> getStockPrices(){
      return this.httpClient.get().uri("/demo02/stock/stream").responseContent().asString();
    };
}
