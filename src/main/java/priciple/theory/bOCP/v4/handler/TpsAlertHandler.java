package priciple.theory.bOCP.v4.handler;

import priciple.theory.bOCP.v4.AlertRule;
import priciple.theory.bOCP.v4.ApiStatInfo;
import priciple.theory.bOCP.v4.Notification;
import priciple.theory.bOCP.v4.NotificationEmergencyLevel;

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
