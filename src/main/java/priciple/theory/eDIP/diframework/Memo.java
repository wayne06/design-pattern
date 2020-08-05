package priciple.theory.eDIP.diframework;

/**
 * 在采用依赖注入实现的 Notification 类中，虽然我们不需要用类似 hard code 的方式，在类内部通过 new 来创建 MessageSender 对象，
 * 但是，这个创建对象、组装（或注入）对象的工作仅仅是被移动到了更上层代码而已，还是需要我们程序员自己来实现
 *
 * 对象创建和依赖注入的工作，本身跟具体的业务无关，我们完全可以抽象成框架来自动完成。
 *
 * 这个框架就是“依赖注入框架”。
 * 我们只需要通过依赖注入框架提供的扩展点，简单配置一下所有需要创建的类对象、类与类之间的依赖关系，
 * 就可以实现由框架来自动创建对象、管理对象的生命周期、依赖注入等原本需要程序员来做的事情。
 *
 * 实际上，现成的依赖注入框架有很多，比如 Google Guice、Java Spring、Pico Container、Butterfly Container 等。
 * Java Spring 框架自己声称是控制反转容器（Inversion Of Control Container）。
 * 实际上，这两种说法都没错。只是控制反转容器这种表述是一种非常宽泛的描述，DI 依赖注入框架的表述更具体、更有针对性。
 * 因为我们前面讲到实现控制反转的方式有很多，除了依赖注入，还有模板模式等，而 Spring 框架的控制反转主要是通过依赖注入来实现的
 *
 */
public class Memo {
}
