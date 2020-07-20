package inaction.idempotence.prepare.mode2;

import inaction.idempotence.prepare.Order;

/**
 * 接口实现方
 */
public class OrderController {

    @IdempotenceRequired
    public Order creatOrder(Object params) {
        //...
        return null;
    }

}
