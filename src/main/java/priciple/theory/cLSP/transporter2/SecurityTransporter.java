package priciple.theory.cLSP.transporter2;

import org.apache.commons.lang3.StringUtils;

/**
 * 改造前，如果 appId 或者 appToken 没有设置，我们不做校验；
 * 改造后，如果 appId 或者 appToken 没有设置，则直接抛出 UnauthorizedException 未授权异常
 *
 * 在改造之后的代码中，如果传递进 demoFunction() 函数的是父类 Transporter 对象，那 demoFunction() 函数并不会有异常抛出，
 * 但如果传递给 demoFunction() 函数的是子类 SecurityTransporter 对象，那 demoFunction() 有可能会有异常抛出。
 *
 * 尽管代码中抛出的是运行时异常（Runtime Exception），我们可以不在代码中显式地捕获处理，
 * 但子类替换父类传递进 demoFunction 函数之后，整个程序的逻辑行为有了改变。
 *
 * 虽然改造之后的代码仍然可以通过 Java 的多态语法，动态地用子类 SecurityTransporter 来替换父类 Transporter，
 * 也并不会导致程序编译或者运行报错。但是，从设计思路上来讲，SecurityTransporter 的设计是不符合里式替换原则的
 *
 * 虽然从定义描述和代码实现上来看，多态和里式替换有点类似，但它们关注的角度是不一样的。
 * 多态是面向对象编程的一大特性，也是面向对象编程语言的一种语法。它是一种代码实现的思路。
 * 而里式替换是一种设计原则，是用来指导继承关系中子类该如何设计的，子类的设计要保证在替换父类的时候，
 * 不改变原有程序的逻辑以及不破坏原有程序的正确性
 *
 */
public class SecurityTransporter extends Transporter {

    private String appId;

    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public Response sendRequest(Request request) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(appToken)) {
            throw new UnauthorizedException("...");
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);
        return super.sendRequest(request);
    }
}
