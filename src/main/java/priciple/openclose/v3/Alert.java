package priciple.openclose.v3;

import priciple.openclose.v3.handler.AlertHandler;

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
