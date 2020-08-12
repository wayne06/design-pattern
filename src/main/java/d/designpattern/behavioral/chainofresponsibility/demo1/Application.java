package d.designpattern.behavioral.chainofresponsibility.demo1;

/**
 * 职责链模式：
 *
 * 第一种实现方式：
 *
 * Handler 是所有处理器类的抽象父类，handle() 是抽象方法。每个具体的处理器类（HandlerA、HandlerB）的 handle() 函数的代码结构类似，
 * 如果它能处理该请求，就不继续往下传递；如果不能处理，则交由后面的处理器来处理（也就是调用 successor.handle()）
 * HandlerChain 是处理器链，从数据结构的角度来看，它就是一个记录了链头、链尾的链表。其中，记录链尾是为了方便添加处理器
 */
public class Application {

    public static void main(String[] args) {

        HandlerChain chain = new HandlerChain();

        chain.addHandler(new HandlerA());

        chain.addHandler(new HandlerB());

        chain.handle();
    }

}
