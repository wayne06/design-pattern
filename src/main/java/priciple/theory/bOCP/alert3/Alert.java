package priciple.theory.bOCP.alert3;

import priciple.theory.bOCP.alert3.handler.AlertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wayne
 *
 * api接口监控告警：
 *
 * 重构：
 * 1. 将 check() 函数的多个入参封装成 ApiStatInfo 类；
 * 2. 引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中
 *
 *
 * 基于重构之后的代码，如果再添加上面讲到的那个新功能，每秒钟接口超时请求个数超过某个最大阈值就告警，我们又该如何改动代码呢？
 * 主要的改动有下面四处。
 * 第一处改动是：在 ApiStatInfo 类中添加新的属性 timeoutCount。
 * 第二处改动是：添加新的 TimeoutAlertHander 类。
 * 第三处改动是：在 ApplicationContext 类的 initializeBeans() 方法中，往 alert 对象中注册新的 timeoutAlertHandler。
 * 第四处改动是：在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值。
 *
 * 具体代码见 v4
 *
 * 重构之后的代码更加灵活和易扩展。
 * 如果我们要想添加新的告警逻辑，只需要基于扩展的方式创建新的 handler 类即可，不需要改动原来的 check() 函数的逻辑。
 * 而且，我们只需要为新的 handler 类添加单元测试，老的单元测试都不会失败，也不用修改
 *
 */
public class Alert {

    private List<AlertHandler> alertHandlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}
