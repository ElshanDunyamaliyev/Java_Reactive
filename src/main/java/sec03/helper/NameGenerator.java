package sec03.helper;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    public static List<String> getListOfNames(){
        var list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(generateRandomName());
        }
        return list;
    }

    public static Flux<String> getFluxOfNames(){
        return Flux.range(1,10).map(i -> generateRandomName());
    }

    public static String generateRandomName(){
        Util.sleep();
        return Util.getFaker().name().firstName();
    }
}
