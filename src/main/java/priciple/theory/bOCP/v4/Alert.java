package priciple.theory.bOCP.v4;

import priciple.theory.bOCP.v4.handler.AlertHandler;

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
 * 重构后的改动：一共改动四处
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
