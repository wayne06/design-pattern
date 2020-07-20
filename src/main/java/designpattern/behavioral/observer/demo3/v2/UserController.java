package designpattern.behavioral.observer.demo3.v2;

import designpattern.behavioral.observer.demo3.RegObserver;
import designpattern.behavioral.observer.demo3.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 异步非阻塞观察者模式的简易实现
 *
 * 第二种方式：在 UserController 的 register() 函数中使用线程池来执行每个观察者的 handleRegSuccess() 函数
 *
 * 尽管利用了线程池解决了第一种实现方式的问题，但线程池、异步执行逻辑都耦合在了 register() 函数中，增加了这部分业务代码的维护成本
 */
public class UserController {

    private UserService userService;

    private List<RegObserver> regObservers = new ArrayList<>();

    private Executor executor;

    public UserController(Executor executor) {
        this.executor = executor;
    }

    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }

    public Long register(String telephone, String password) {
        long userId = userService.register(telephone, password);

        for (RegObserver observer : regObservers) {
            //observer.handleRegSuccess(userId);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observer.handleRegSuccess(userId);
                }
            });
        }

        return userId;
    }

}
