package designpattern.structural.bridge.v2.handler;

import designpattern.structural.bridge.v2.AlertRule;
import designpattern.structural.bridge.v2.ApiStatInfo;
import designpattern.structural.bridge.v2.Notification;
import designpattern.structural.bridge.v2.NotificationEmergencyLevel;

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
