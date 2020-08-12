package f.inaction.idempotence.prepare.mode1;

import f.inaction.idempotence.prepare.Idempotence;
import f.inaction.idempotence.prepare.Order;

/**
 * 接口实现方
 */
public class OrderController {

    private Idempotence idempotence;

    public Order createOrderWithIdempotence(Object params, String idempotenceId) {
        // 前置操作
        boolean existed = idempotence.check(idempotenceId);
        if (existed) {
            // 两种处理方式：
            // 1. 查询 order，并且返回
            // 2. 返回 duplication operation Exception
        }
        idempotence.record(idempotenceId);

        // ...执行正常业务逻辑

        return null;
    }

    public Order creatOrder(Object params) {
        // ...
        return null;
    }

}
