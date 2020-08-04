package priciple.theory.bOCP.alert4.handler;

import priciple.theory.bOCP.alert4.AlertRule;
import priciple.theory.bOCP.alert4.ApiStatInfo;
import priciple.theory.bOCP.alert4.Notification;
import priciple.theory.bOCP.alert4.NotificationEmergencyLevel;

/**
 * @author wayne
 */
public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }

}
