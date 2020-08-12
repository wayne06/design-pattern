package d.designpattern.structural.bridge.v3.handler;

import d.designpattern.structural.bridge.v3.notification.Notification;
import d.designpattern.structural.bridge.v3.AlertRule;
import d.designpattern.structural.bridge.v3.ApiStatInfo;

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
