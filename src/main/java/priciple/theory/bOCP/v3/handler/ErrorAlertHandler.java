package priciple.theory.bOCP.v3.handler;

import priciple.theory.bOCP.v3.AlertRule;
import priciple.theory.bOCP.v3.ApiStatInfo;
import priciple.theory.bOCP.v3.Notification;
import priciple.theory.bOCP.v3.NotificationEmergencyLevel;

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
