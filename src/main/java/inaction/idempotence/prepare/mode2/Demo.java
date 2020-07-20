package inaction.idempotence.prepare.mode2;

import inaction.idempotence.prepare.Idempotence;

/**
 * 方式二：在框架层面处理幂等
 *
 * 接口调用方
 */
public class Demo {

    public static void main(String[] args) {
        Idempotence idempotence = new Idempotence();
        String idempotenceId = idempotence.createId();

        //...通过 feign 框架将幂等号添加到 http header 中...

    }

}
