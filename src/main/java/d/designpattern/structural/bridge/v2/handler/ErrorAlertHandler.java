package d.designpattern.structural.bridge.v2.handler;

import d.designpattern.structural.bridge.v2.Notification;
import d.designpattern.structural.bridge.v2.NotificationEmergencyLevel;
import d.designpattern.structural.bridge.v2.AlertRule;
import d.designpattern.structural.bridge.v2.ApiStatInfo;

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
