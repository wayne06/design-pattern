package inaction.idempotence.prepare.mode1;

import inaction.idempotence.prepare.Idempotence;
import inaction.idempotence.prepare.Order;

/**
 * 方式一：在业务代码中处理幂等
 * <p>
 * 接口调用方
 */
public class Demo {

    public static void main(String[] args) {
        Idempotence idempotence = new Idempotence();
        String idempotenceId = idempotence.createId();

        Object params = new Object();
        OrderController orderController = new OrderController();
        Order order = orderController.createOrderWithIdempotence(params, idempotenceId);

        System.out.println(order);
    }


}
