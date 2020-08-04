package priciple.theory.bOCP.alert3.handler;

import priciple.theory.bOCP.alert3.AlertRule;
import priciple.theory.bOCP.alert3.ApiStatInfo;
import priciple.theory.bOCP.alert3.Notification;
import priciple.theory.bOCP.alert3.NotificationEmergencyLevel;

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
