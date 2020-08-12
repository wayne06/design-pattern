package b.priciple.theory.bOCP.alert3.handler;

import b.priciple.theory.bOCP.alert3.AlertRule;
import b.priciple.theory.bOCP.alert3.ApiStatInfo;
import b.priciple.theory.bOCP.alert3.Notification;
import b.priciple.theory.bOCP.alert3.NotificationEmergencyLevel;

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
