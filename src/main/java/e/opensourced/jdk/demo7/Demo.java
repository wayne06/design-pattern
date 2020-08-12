package e.opensourced.jdk.demo7;

/**
 * 单例模式在 Runtime 类中的应用
 *
 * JDK 中 java.lang.Runtime 类就是一个单例类。之前讲到 Callback 回调的时候，添加 shutdown hook 就是通过这个类来实现的。
 * 每个 Java 应用在运行时会启动一个 JVM 进程，每个 JVM 进程都只对应一个 Runtime 实例，用于查看 JVM 状态以及控制 JVM 行为。
 * 进程内唯一，所以比较适合设计为单例。在编程的时候，我们不能自己去实例化一个 Runtime 对象，只能通过 getRuntime() 静态方法来获得。
 *
 * Runtime 类的的代码中，可以看出，它使用了最简单的饿汉式的单例实现方式。
 */
public class Demo {

}
