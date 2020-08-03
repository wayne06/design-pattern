package priciple.theory.bOCP.v4.handler;

import priciple.theory.bOCP.v4.AlertRule;
import priciple.theory.bOCP.v4.ApiStatInfo;
import priciple.theory.bOCP.v4.Notification;
import priciple.theory.bOCP.v4.NotificationEmergencyLevel;

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
