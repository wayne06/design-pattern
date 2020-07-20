package designpattern.structural.bridge.v3.handler;

import designpattern.structural.bridge.v3.AlertRule;
import designpattern.structural.bridge.v3.ApiStatInfo;
import designpattern.structural.bridge.v3.notification.Notification;
import designpattern.structural.bridge.v3.NotificationEmergencyLevel;

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
            notification.notify("...");
        }
    }

}
