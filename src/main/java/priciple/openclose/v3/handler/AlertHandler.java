package priciple.openclose.v3.handler;

import priciple.openclose.v3.AlertRule;
import priciple.openclose.v3.ApiStatInfo;
import priciple.openclose.v3.Notification;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
