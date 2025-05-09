package sec06.assignment;

import common.AbstractHttpClient;
import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import sec06.assignment.client.ExternalClientDemo04;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Lec06Assignment extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(Lec06Assignment.class);

    public static void main(String[] args) {

        var revenueMap = new HashMap<String, Integer>();
        var inventoryMap = new HashMap<String, Integer>();
        var client = new ExternalClientDemo04();
        var publisher = client.getProduct().
                map(str -> str.split(":"))
                .doOnNext(item -> {
                    handleRevenueService(revenueMap, inventoryMap, item);
                })
                .publish().refCount(1);



        // Trigger the data stream (we need at least 1 subscriber)
        publisher.subscribe(item -> {
            // No-op, just triggering the stream
        });

        // Poll revenue map every 2 seconds
        Flux.interval(Duration.ofSeconds(2))
                .doOnNext(i -> log.info("[Revenue Subscriber] revenueMap: {}", revenueMap))
                .subscribe();

        // Poll inventory map every 2 seconds
        Flux.interval(Duration.ofSeconds(2))
                .doOnNext(i -> log.info("[Inventory Subscriber] inventoryMap: {}", inventoryMap))
                .subscribe();

        Util.sleep(20); // Wait long enough to observe
    }

    private static void handleRevenueService(Map<String, Integer> revenueMap, Map<String, Integer> inventoryMap, String[] item){
        if(!revenueMap.containsKey(item[1])){
            revenueMap.put(item[1],Integer.parseInt(item[2]) * Integer.parseInt(item[3]));
            inventoryMap.put(item[1], 500 - Integer.parseInt(item[3]));
        }else{
            var existedValue = revenueMap.get(item[1]);
            revenueMap.put(item[1], Integer.parseInt(item[2]) * Integer.parseInt(item[3]) + existedValue);
            var quantityCount = inventoryMap.get(item[1]);
            inventoryMap.put(item[1], quantityCount - Integer.parseInt(item[3]));
        }
        log.info("revenueMap : {}", revenueMap);
        log.info("inventoryMap : {}", inventoryMap);
    }
}
