package b.priciple.theory.bOCP.alert4.handler;

import b.priciple.theory.bOCP.alert4.Notification;
import b.priciple.theory.bOCP.alert4.AlertRule;
import b.priciple.theory.bOCP.alert4.ApiStatInfo;

public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
