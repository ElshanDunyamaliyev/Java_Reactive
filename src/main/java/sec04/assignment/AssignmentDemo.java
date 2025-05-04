package sec04.assignment;

import common.Util;
import sec01.subscriber.SubscriberImpl;

import java.nio.file.Path;

public class AssignmentDemo {
    public static void main(String[] args) {
        var subscriber = new SubscriberImpl();
        var fileService = new FileReaderServiceImpl();
        fileService.read(Path.of("src/main/java/sec04/assignment/output.txt")).subscribe(subscriber);

        subscriber.getSubscription().request(2);
        Util.sleep();
        subscriber.getSubscription().request(4);
        Util.sleep();
        subscriber.getSubscription().request(3);
        Util.sleep();

    }
}
