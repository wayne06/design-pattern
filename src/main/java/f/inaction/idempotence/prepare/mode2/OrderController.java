package f.inaction.idempotence.prepare.mode2;

import f.inaction.idempotence.prepare.Order;

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
