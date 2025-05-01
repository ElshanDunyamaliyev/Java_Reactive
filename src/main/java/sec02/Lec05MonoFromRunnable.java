package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec05MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(1).subscribe(Util.subscriber());
        getProductName(2).subscribe(Util.subscriber());
    }

    public static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.fromCallable(() -> Util.getFaker().commerce().productName());
        }
        // Supplier and callable returns some value
        // Runnable doesn't returns anything it is void
//        return Mono.empty(); // we can just return empty but it is best to notify business owner that you dont have that product restock it
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    public static void notifyBusiness(int productId){
        log.info("No product found with given id {}, maybe restock it", productId);
    }
}
