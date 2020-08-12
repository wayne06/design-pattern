package b.priciple.theory.bOCP.alert4.handler;

import b.priciple.theory.bOCP.alert4.AlertRule;
import b.priciple.theory.bOCP.alert4.ApiStatInfo;
import b.priciple.theory.bOCP.alert4.Notification;
import b.priciple.theory.bOCP.alert4.NotificationEmergencyLevel;

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
