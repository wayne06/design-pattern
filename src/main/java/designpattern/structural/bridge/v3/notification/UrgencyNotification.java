package designpattern.structural.bridge.v3.notification;

import designpattern.structural.bridge.v3.sender.MessageSender;

public class UrgencyNotification extends Notification {

    public UrgencyNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void notify(String message) {
        messageSender.send(message);
    }
}
