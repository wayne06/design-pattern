package priciple.openclose.v4.handler;

import priciple.openclose.v4.AlertRule;
import priciple.openclose.v4.ApiStatInfo;
import priciple.openclose.v4.Notification;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
