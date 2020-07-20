package designpattern.structural.bridge.v2;

import designpattern.structural.bridge.v2.handler.ErrorAlertHandler;
import designpattern.structural.bridge.v2.handler.TimeoutAlertHandler;
import designpattern.structural.bridge.v2.handler.TpsAlertHandler;

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
