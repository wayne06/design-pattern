package e.opensourced.spring.demo2;

import org.springframework.context.ApplicationEvent;

/**
 * 观察者模式在 Spring 中的应用
 *
 * Java 提供的观察者模式实现框架比较简单，只包含 java.util.Observable 和 java.util.Observer 两个类。
 * Google Guava 提供的框架功能比较完善和强大：通过 EventBus 事件总线来实现观察者模式。
 * Spring 中实现的观察者模式包含三部分：Event 事件（相当于消息）、Listener 监听者（相当于观察者）、Publisher 发送者（相当于被观察者）
 *
 * 框架使用起来并不复杂，主要包含三部分工作：
 * 定义一个继承 ApplicationEvent 的事件（DemoEvent）；
 * 定义一个实现了 ApplicationListener 的监听器（DemoListener）；
 * 定义一个发送者（DemoPublisher），发送者调用 ApplicationContext 来发送事件消息。
 * 其中，ApplicationEvent 和 ApplicationListener 的代码实现都非常简单，内部并不包含太多属性和方法。
 * 实际上，它们最大的作用是做类型标识之用（继承自 ApplicationEvent 的类是事件，实现 ApplicationListener 的类是监听器）。
 *
 * 在前面讲到观察者模式的时候，我们提到，观察者需要事先注册到被观察者（JDK 的实现方式）或者事件总线（EventBus 的实现方式）中。
 * 那在 Spring 的实现中，观察者注册到了哪里呢？又是如何注册的呢？
 * 我们把观察者注册到了 ApplicationContext 对象中。这里的 ApplicationContext 就相当于 Google EventBus 框架中的“事件总线”。
 * 不过，稍微提醒一下，ApplicationContext 这个类并不只是为观察者模式服务的。
 * 它底层依赖 BeanFactory（IOC 的主要实现类），提供应用启动、运行时的上下文信息，是访问这些信息的最顶层接口。
 * 实际上，具体到源码来说，ApplicationContext 只是一个接口，具体的代码实现包含在它的实现类 AbstractApplicationContext 中
 *
 * 真正的消息发送，实际上是通过 ApplicationEventMulticaster 这个类来完成的。
 * 最关键的一部分，也就是 multicastEvent() 这个消息发送函数，它通过线程池，支持异步非阻塞、同步阻塞这两种类型的观察者模式。
 *
 * 借助 Spring 提供的观察者模式的骨架代码，如果我们要在 Spring 下实现某个事件的发送和监听，只需要做很少的工作，
 * 定义事件、定义监听器、往 ApplicationContext 中发送事件就可以了，剩下的工作都由 Spring 框架来完成。
 * 实际上，这也体现了 Spring 框架的扩展性，也就是在不需要修改任何代码的情况下，扩展新的事件和监听。
 *
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source) {
        super(source);
    }
}
