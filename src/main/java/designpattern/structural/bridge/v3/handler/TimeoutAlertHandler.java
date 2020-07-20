package designpattern.structural.bridge.v3.handler;

import designpattern.structural.bridge.v3.AlertRule;
import designpattern.structural.bridge.v3.ApiStatInfo;
import designpattern.structural.bridge.v3.notification.Notification;
import designpattern.structural.bridge.v3.NotificationEmergencyLevel;

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
            notification.notify("...");
        }
    }
}
