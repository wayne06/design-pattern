package priciple.theory.eDIP.di.nondi;

/**
 * 依赖注入跟控制反转恰恰相反，它是一种具体的编码技巧。依赖注入的英文翻译是 Dependency Injection，缩写为 DI
 *
 * 用一句话来概括就是：
 * 不通过 new 的方式在类内部创建依赖类对象，而是将依赖的类对象在外部创建好之后，通过构造函数、函数参数等方式传递（或注入）给类使用
 *
 * Notification 类负责消息推送，依赖 MessageSender 类实现推送商品促销、验证码等消息给用户
 * 下面是非依赖注入的实现方式：
 *
 */
public class Notification {

    private MessageSender messageSender;

    // 类似 hard-code
    public Notification() {
        this.messageSender = new MessageSender();
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等
        this.messageSender.send(cellphone, message);
    }
}
