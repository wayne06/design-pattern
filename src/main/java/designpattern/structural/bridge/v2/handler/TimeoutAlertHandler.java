package designpattern.structural.bridge.v2.handler;

import designpattern.structural.bridge.v2.AlertRule;
import designpattern.structural.bridge.v2.ApiStatInfo;
import designpattern.structural.bridge.v2.Notification;
import designpattern.structural.bridge.v2.NotificationEmergencyLevel;

/**
 *
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
