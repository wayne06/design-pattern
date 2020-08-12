package b.priciple.theory.bOCP.alert4.handler;

import b.priciple.theory.bOCP.alert4.AlertRule;
import b.priciple.theory.bOCP.alert4.ApiStatInfo;
import b.priciple.theory.bOCP.alert4.Notification;
import b.priciple.theory.bOCP.alert4.NotificationEmergencyLevel;

/**
 * 改动2：添加TimeoutAlertHandler类
 */
public class TimeoutAlertHandler extends AlertHandler {

    public TimeoutAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (timeoutTps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
