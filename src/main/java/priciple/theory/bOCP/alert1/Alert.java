package priciple.theory.bOCP.alert1;

/**
 * @author wayne
 *
 * api接口监控告警
 *
 * 业务逻辑主要集中在 check() 函数中。
 * 当接口的 TPS 超过某个预先设置的最大值时，以及当接口请求出错数大于某个最大允许值时，就会触发告警，通知接口的相关负责人或者团队。
 *
 */
public class Alert {

    private AlertRule rule;

    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public void check(String api, long requestCount, long errorCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;

        // 当接口的 TPS 超过某个预先设置的最大值时
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }

        // 当接口请求出错数大于某个最大允许值时
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
