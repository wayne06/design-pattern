package d.designpattern.structural.bridge.v3.notification;

import d.designpattern.structural.bridge.v3.sender.MessageSender;

public class NormalNotification extends Notification {

    public NormalNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void notify(String message) {
        messageSender.send(message);
    }
}
