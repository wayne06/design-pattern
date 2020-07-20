package designpattern.structural.bridge.v2.handler;

import designpattern.structural.bridge.v2.AlertRule;
import designpattern.structural.bridge.v2.ApiStatInfo;
import designpattern.structural.bridge.v2.Notification;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
