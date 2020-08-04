package priciple.theory.bOCP.alert3.handler;

import priciple.theory.bOCP.alert3.AlertRule;
import priciple.theory.bOCP.alert3.ApiStatInfo;
import priciple.theory.bOCP.alert3.Notification;
import priciple.theory.bOCP.alert3.NotificationEmergencyLevel;

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
