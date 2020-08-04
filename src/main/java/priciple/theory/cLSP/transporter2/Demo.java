package priciple.theory.cLSP.transporter2;

/**
 * 父类 Transporter 使用 HttpClient 类来传输网络数据。
 * 子类 SecurityTransporter 继承父类 Transporter，增加了额外的功能，支持传输 appId 和 appToken 安全认证信息。
 *
 * 子类 SecurityTransporter 的设计完全符合里式替换原则，可以替换父类出现的任何位置，并且原来代码的逻辑行为不变且正确性也没有被破坏
 *
 * 里式替换原则跟多态看起来确实有点类似，但实际上它们完全是两回事
 */
public class Demo {

    public void demoF(Transporter transporter) {
        Request request = new Request();
        //...省略设置 request 中数据值的代码...

        Response response = transporter.sendRequest(request);
        //...省略其他逻辑...

    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.demoF(new SecurityTransporter(new HttpClient(), "a", "b"));
    }

}
