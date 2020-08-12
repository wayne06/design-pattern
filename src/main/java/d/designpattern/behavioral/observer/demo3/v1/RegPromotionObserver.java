package d.designpattern.behavioral.observer.demo3.v1;

import d.designpattern.behavioral.observer.demo3.PromotionService;
import d.designpattern.behavioral.observer.demo3.RegObserver;

/**
 * 异步非阻塞观察者模式的简易实现
 *
 * 第一种方式：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑
 *
 * 频繁地创建和销毁线程比较耗时，并且并发线程数无法控制，创建过多的线程会导致堆栈溢出
 */
public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService;

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }

    //@Override
    //public void handleRegSuccess(long userId) {
    //    promotionService.issueNewUserExperienceCash(userId);
    //}


}
