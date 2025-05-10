package sec07;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06Parallel {
    // Publish on ile ferqi ondadirki cpu sayi qeder thread yaradir ve parallel isleyir
    public static void main(String[] args) {
        Flux.range(1,10)
                .parallel(3)// 3 is optional it says that only do 3 task in same time if not specified will do all task in same time
                .runOn(Schedulers.parallel())
                .map(i -> tranform(i))
                .sequential()
                .subscribe(Util.subscriber());

        Util.sleep(30);
    }

    private static int tranform(int i){
        Util.sleep(1);
        return i * 2;
    }
}
