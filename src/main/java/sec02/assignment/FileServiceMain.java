package sec02.assignment;

import common.Util;

public class FileServiceMain {

    public static void main(String[] args) throws Exception {
        var fileService = new FileServiceImpl();
        fileService.read("src/main/java/sec02/assignment/resources/Output.txt").subscribe(Util.subscriber());
        fileService.write("src/main/java/sec02/assignment/resources/random.txt","STOP").subscribe(Util.subscriber("writer"));
//        fileService.delete("src/main/java/sec02/assignment/resources/random.txt").subscribe(Util.subscriber("writer"));

        Util.sleep();
    }
}
