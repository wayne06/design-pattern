package priciple.openclose.v4.handler;

import priciple.openclose.v4.AlertRule;
import priciple.openclose.v4.ApiStatInfo;
import priciple.openclose.v4.Notification;
import priciple.openclose.v4.NotificationEmergencyLevel;

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
