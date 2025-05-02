package sec03;

import common.Util;
import sec03.helper.NameGenerator;

public class Lec03FluxVsList {
    public static void main(String[] args) {
//        NameGenerator.getFluxOfNames().subscribe(Util.subscriber());
        System.out.println(NameGenerator.getListOfNames());
    }
}
