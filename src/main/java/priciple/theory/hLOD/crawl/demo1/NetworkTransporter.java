package priciple.theory.hLOD.crawl.demo1;

/**
 * 负责底层网络通信，根据请求获取数据
 *
 * 作为一个底层网络通信类，我们希望它的功能尽可能通用，而不只是服务于下载 HTML，所以不应该直接依赖太具体的发送对象 HtmlRequest。
 *
 * 从这一点上讲，NetworkTransporter 类的设计违背迪米特法则，依赖了不该有直接依赖关系的 HtmlRequest 类。
 * 应该把 address 和 content 交给 NetworkTransporter，而非是直接把 HtmlRequest 交给 NetworkTransporter
 *
 */
public class NetworkTransporter {

    // 省略属性和其他方法

    public Byte[] send(HtmlRequest htmlRequest) {
        // ...
        return null;
    };


}
