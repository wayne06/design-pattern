package b.priciple.theory.bOCP.alert4.handler;

import b.priciple.theory.bOCP.alert4.AlertRule;
import b.priciple.theory.bOCP.alert4.ApiStatInfo;
import b.priciple.theory.bOCP.alert4.Notification;
import b.priciple.theory.bOCP.alert4.NotificationEmergencyLevel;

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
