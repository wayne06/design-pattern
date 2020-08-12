package d.designpattern.structural.bridge.v2.handler;

import d.designpattern.structural.bridge.v2.Notification;
import d.designpattern.structural.bridge.v2.AlertRule;
import d.designpattern.structural.bridge.v2.ApiStatInfo;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
