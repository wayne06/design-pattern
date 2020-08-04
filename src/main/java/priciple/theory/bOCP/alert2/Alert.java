package priciple.theory.bOCP.alert2;

/**
 * @author wayne
 *
 * api接口监控告警：
 *
 * 添加一个功能，当每秒钟接口超时请求个数，超过某个预先设置的最大阈值时，我们也要触发告警发送通知
 * 1. 修改 check() 函数的入参，添加一个新的统计数据 timeoutCount，表示超时接口请求数；
 * 2. 在 check() 函数中添加新的告警逻辑
 *
 * 存在的问题：
 * 1. 对接口进行了修改，意味着调用这个接口的代码都要做相应的修改
 * 2. 修改了 check() 函数，相应的单元测试都需要修改
 *
 * 解决方案：
 * 1. 将 check() 函数的多个入参封装成 ApiStatInfo 类
 * 2. 引入 handler 概念，将 if 判断逻辑分散在各个 handler 中
 *
 * 见 v3
 *
 */
public class Alert {

    private AlertRule    rule;
    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public void check(String api, long requestCount, long errorCount, long timeoutCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;

        // 当接口的 TPS 超过某个预先设置的最大值时
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }

        // 当接口请求出错数大于某个最大允许值时
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }

        long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
