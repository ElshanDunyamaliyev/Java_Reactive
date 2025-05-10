package sec07;

import common.Util;
import reactor.core.scheduler.Schedulers;
import sec06.assignment.client.ExternalClientDemo04;
import sec07.client.ExternalClient;

public class Lec04EventLoopDemo {
    public static void main(String[] args) {
        var client = new ExternalClient();
        for (int i = 0; i < 5; i++) {
            client.getProduct(i)
                    .publishOn(Schedulers.boundedElastic())
                    .map(Lec04EventLoopDemo::transform)
                    .subscribe(Util.subscriber());
        }

        Util.sleep(5);

    }

    private static String transform(String value){
        Util.sleep(1);
        return value + " transformed";
    }
}
