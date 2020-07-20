package priciple.openclose.v4;

import priciple.openclose.v4.handler.ErrorAlertHandler;
import priciple.openclose.v4.handler.TimeoutAlertHandler;
import priciple.openclose.v4.handler.TpsAlertHandler;

/**
 * @author wayne
 *
 * ApplicationContext 是一个单例类，负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作
 */
public class ApplicationContext {

    private AlertRule    alertRule;
    private Notification notification;
    private Alert        alert;

    public void initializeBeans() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));

        // 改动3：往alert对象中注册新的timeoutAlertHandler
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
    }

    public Alert getAlert() {
        return alert;
    }

    private static final ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {
        instance.initializeBeans();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }
}
