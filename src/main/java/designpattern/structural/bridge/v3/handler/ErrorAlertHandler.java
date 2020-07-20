package designpattern.structural.bridge.v3.handler;

import designpattern.structural.bridge.v3.AlertRule;
import designpattern.structural.bridge.v3.ApiStatInfo;
import designpattern.structural.bridge.v3.notification.Notification;
import designpattern.structural.bridge.v3.NotificationEmergencyLevel;

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
            notification.notify("...");
        }
    }
}
