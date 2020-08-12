package d.designpattern.structural.bridge.v3.handler;

import d.designpattern.structural.bridge.v3.notification.Notification;
import d.designpattern.structural.bridge.v3.AlertRule;
import d.designpattern.structural.bridge.v3.ApiStatInfo;

/**
 * @author wayne
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify("...");
        }
    }
}
