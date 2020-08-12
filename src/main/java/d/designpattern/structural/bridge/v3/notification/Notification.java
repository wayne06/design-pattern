package d.designpattern.structural.bridge.v3.notification;

import d.designpattern.structural.bridge.v3.sender.MessageSender;

/**
 * @author wayne
 *
 * 告警通知类
 */
public abstract class Notification {

    protected MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public abstract void notify(String message);

    //private List<String> emailAddress;
    //
    //private List<String> telephones;
    //
    //private List<String> wechatIds;
    //
    //public Notification() {
    //}
    //
    //public void setEmailAddress(List<String> emailAddress) {
    //    this.emailAddress = emailAddress;
    //}
    //
    //public void setTelephones(List<String> telephones) {
    //    this.telephones = telephones;
    //}
    //
    //public void setWechatIds(List<String> wechatIds) {
    //    this.wechatIds = wechatIds;
    //}
    //
    //public void notify(NotificationEmergencyLevel level, String message) {
    //    if (level.equals(NotificationEmergencyLevel.SEVERE)) {
    //    // ...打电话
    //    } else if (level.equals(NotificationEmergencyLevel.URGENCY)) {
    //    // ...发微信
    //    } else if (level.equals(NotificationEmergencyLevel.NORMAL)) {
    //    // ...发邮件
    //    } else if (level.equals(NotificationEmergencyLevel.TRIVIAL)) {
    //    // ...发邮件
    //    }
    //}

}
