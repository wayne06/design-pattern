package d.designpattern.structural.bridge.v2.handler;

import d.designpattern.structural.bridge.v2.ApiStatInfo;
import d.designpattern.structural.bridge.v2.Notification;
import d.designpattern.structural.bridge.v2.NotificationEmergencyLevel;
import d.designpattern.structural.bridge.v2.AlertRule;

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
