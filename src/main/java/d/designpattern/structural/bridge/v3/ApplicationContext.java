package d.designpattern.structural.bridge.v3;

import d.designpattern.structural.bridge.v3.handler.ErrorAlertHandler;
import d.designpattern.structural.bridge.v3.handler.TpsAlertHandler;
import d.designpattern.structural.bridge.v3.notification.ServereNotification;
import d.designpattern.structural.bridge.v3.notification.UrgencyNotification;
import d.designpattern.structural.bridge.v3.sender.TelephoneMessageSender;
import d.designpattern.structural.bridge.v3.sender.WechatMessageSender;
import d.designpattern.structural.bridge.v3.handler.TimeoutAlertHandler;

/**
 * @author wayne
 * <p>
 * ApplicationContext 是一个单例类，负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作
 */
public class ApplicationContext {

    private AlertRule alertRule;
    //private Notification notification;
    private Alert     alert;

    public void initializeBeans() {
        //alertRule = new AlertRule();
        //notification = new Notification();
        //alert = new Alert();
        //alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        //alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        //alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));

        alertRule = new AlertRule();
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, new UrgencyNotification(new WechatMessageSender())));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, new ServereNotification(new TelephoneMessageSender())));
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, new UrgencyNotification(new WechatMessageSender())));
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
