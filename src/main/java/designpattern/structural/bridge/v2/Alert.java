package designpattern.structural.bridge.v2;

import designpattern.structural.bridge.v2.handler.AlertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wayne
 *
 * api接口监控告警：
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
