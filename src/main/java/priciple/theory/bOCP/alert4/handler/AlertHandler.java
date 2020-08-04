package priciple.theory.bOCP.alert4.handler;

import priciple.theory.bOCP.alert4.AlertRule;
import priciple.theory.bOCP.alert4.ApiStatInfo;
import priciple.theory.bOCP.alert4.Notification;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
