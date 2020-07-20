package designpattern.structural.bridge.v2.handler;

import designpattern.structural.bridge.v2.AlertRule;
import designpattern.structural.bridge.v2.ApiStatInfo;
import designpattern.structural.bridge.v2.Notification;
import designpattern.structural.bridge.v2.NotificationEmergencyLevel;

/**
 * @author wayne
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
