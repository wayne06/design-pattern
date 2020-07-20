package designpattern.behavioral.observer.demo2.useobserver;

import designpattern.behavioral.observer.demo2.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 当我们需要添加新的观察者的时候，比如，用户注册成功之后，推送用户注册信息给大数据征信系统，
 * 基于观察者模式的代码实现，UserController 类的 register() 函数完全不需要修改，只需要再添加一个实现了 RegObserver 接口的类，
 * 并且通过 setRegObservers() 函数将它注册到 UserController 类中即可。
 */
public class UserController {

    private UserService userService;

    private List<RegObserver> regObservers = new ArrayList<>();

    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }

    public Long register(String telephone, String password) {
        long userId = userService.register(telephone, password);

        for (RegObserver observer : regObservers) {
            observer.handleRegSuccess(userId);
        }

        return userId;
    }

}
