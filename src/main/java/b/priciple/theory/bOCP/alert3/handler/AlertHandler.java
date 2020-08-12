package b.priciple.theory.bOCP.alert3.handler;

import b.priciple.theory.bOCP.alert3.AlertRule;
import b.priciple.theory.bOCP.alert3.ApiStatInfo;
import b.priciple.theory.bOCP.alert3.Notification;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
