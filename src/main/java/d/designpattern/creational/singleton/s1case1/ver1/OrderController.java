package d.designpattern.creational.singleton.s1case1.ver1;

import java.io.IOException;

public class OrderController {

    private Logger logger = new Logger();

    public void create(Object order) {
        try {
            logger.log("created an order: " + order.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
