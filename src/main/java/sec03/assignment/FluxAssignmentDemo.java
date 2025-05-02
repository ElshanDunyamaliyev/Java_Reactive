package sec03.assignment;

import common.Util;

import java.time.Duration;

public class FluxAssignmentDemo {
    public static void main(String[] args) throws InterruptedException {
        var client = new FluxAssignmentClient();
        client.getStockPrices().map(Integer::valueOf).subscribe(new StockSubscriber());
        Thread.sleep(Duration.ofSeconds(21));
    }
}
