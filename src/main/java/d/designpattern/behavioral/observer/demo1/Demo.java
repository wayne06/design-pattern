package d.designpattern.behavioral.observer.demo1;

/**
 * 观察者模式的实现方法各式各样，函数、类的命名等会根据业务场景的不同有很大的差别，
 * 比如 register 函数还可以叫作 attach，remove 函数还可以叫作 detach 等等
 */
public class Demo {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new Message());

    }

}
