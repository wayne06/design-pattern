package priciple.theory.bOCP.v3;

import java.util.List;

/**
 * @author wayne
 *
 * 告警通知类
 */
public class Notification {

    private List<String> emailAddress;

    private List<String> telephones;

    private List<String> wechatIds;

    public Notification() {
    }

    public void setEmailAddress(List<String> emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void notify(NotificationEmergencyLevel level, String message) {
        if (level.equals(NotificationEmergencyLevel.SEVERE)) {

        } else if (level.equals(NotificationEmergencyLevel.URGENCY)) {

        } else if (level.equals(NotificationEmergencyLevel.NORMAL)) {

        } else if (level.equals(NotificationEmergencyLevel.TRIVIAL)) {

        }
    }

}
