package priciple.theory.eDIP.ioc.demo2;

/**
 * demo2 的例子，就是典型的通过框架来实现“控制反转”的例子。
 * 框架提供了一个可扩展的代码骨架，用来组装对象、管理整个执行流程。
 * 程序员利用框架进行开发的时候，只需要往预留的扩展点上，添加跟自己业务相关的代码，就可以利用框架来驱动整个程序流程的执行。
 *
 * 这里的“控制”指的是对程序执行流程的控制，
 * 而“反转”指的是在没有使用框架之前，程序员自己控制整个程序的执行。在使用框架之后，整个程序的执行流程可以通过框架来控制。
 * 流程的控制权从程序员“反转”到了框架。
 *
 * 实际上，实现控制反转的方法有很多，除了刚才例子中所示的类似于模板设计模式的方法之外，还有 依赖注入 等方法。
 * 所以，控制反转并不是一种具体的实现技巧，而是一个比较笼统的设计思想，一般用来指导框架层面的设计
 *
 */
public class UserServiceTest extends TestCase {

    @Override
    public boolean doTest() {
        //...
        return false;
    }

    public static void main(String[] args) {
        // 注册操作还可以通过配置的方式来实现，不需要程序员显示调用register()
        JunitApplication.register(new UserServiceTest());
    }

}
