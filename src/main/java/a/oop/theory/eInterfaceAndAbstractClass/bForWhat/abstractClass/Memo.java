package a.oop.theory.eInterfaceAndAbstractClass.bForWhat.abstractClass;

/**
 * 抽象类也是为代码复用而生的。多个子类可以继承抽象类中定义的属性和方法，避免在子类中，重复编写相同的代码。
 * 不过，既然继承本身就能达到代码复用的目的，而继承也并不要求父类一定是抽象类，那我们不使用抽象类，照样也可以实现继承和复用。
 * 从这个角度上来讲，我们貌似并不需要抽象类这种语法。那抽象类除了解决代码复用的问题，还有什么其他存在的意义吗
 *
 * 使用普通类而非抽象类：
 * 这个设计思路虽然达到了代码复用的目的，但是无法使用多态特性了。
 * 像下面这样编写代码，就会出现编译错误，因为 Logger 中并没有定义 log() 方法
 *
     Logger logger = new FileLogger("access-log", true, Level.WARN, "/users/wangzheng/access.log");
     logger.log(Level.ERROR, "This is a test log message.");
 *
 * 在 Logger 父类中，定义一个空的 log() 方法，让子类重写父类的 log() 方法，实现自己的记录日志的逻辑：
 * - 在 Logger 中定义一个空的方法，会影响代码的可读性
 * - 当创建一个新的子类继承 Logger 父类的时候，我们有可能会忘记重新实现 log() 方法
 * - Logger 可以被实例化，换句话说，我们可以 new 一个 Logger 出来，并且调用空的 log() 方法。这也增加了类被误用的风险
 *
 */
public class Memo {
}
