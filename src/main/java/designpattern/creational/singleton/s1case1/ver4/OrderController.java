package designpattern.creational.singleton.s1case1.ver4;

import java.io.IOException;

public class OrderController {

    public void create(Object order) {
        try {
            Logger.getInstance().log("created an order: " + order.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
