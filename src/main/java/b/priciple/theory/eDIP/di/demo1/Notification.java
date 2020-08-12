package b.priciple.theory.eDIP.di.demo1;

/**
 * 依赖注入跟控制反转恰恰相反，它是一种具体的编码技巧。依赖注入的英文翻译是 Dependency Injection，缩写为 DI
 *
 * 用一句话来概括就是：
 * 不通过 new 的方式在类内部创建依赖类对象，而是将依赖的类对象在外部创建好之后，通过构造函数、函数参数等方式传递（或注入）给类使用
 *
 * Notification 类负责消息推送，依赖 MessageSender 类实现推送商品促销、验证码等消息给用户
 * 下面是依赖注入的实现方式：
 *
 *
 * 通过依赖注入的方式来将依赖的类对象传递进来，这样就提高了代码的扩展性，我们可以灵活地替换依赖的类。
 * 这一点在我们之前讲“开闭原则”的时候也提到过。
 * 当然，上面代码还有继续优化的空间，我们还可以把 MessageSender 定义成接口，基于接口而非实现编程，见 demo2
 *
 */
public class Notification {

    private MessageSender messageSender;

    // 通过构造函数将 messageSender 传递进来
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }
}
