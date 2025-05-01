package sec02;

import common.Util;
import sec02.client.ExternalServiceClient;

public class Lec09NonBlockingIODemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 1; i <= 100; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber()); // Will send 100 request in same time
        }
        Util.sleep();
    }

}
