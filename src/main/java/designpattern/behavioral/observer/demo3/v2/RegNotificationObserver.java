package designpattern.behavioral.observer.demo3.v2;

import designpattern.behavioral.observer.demo3.NotificationService;
import designpattern.behavioral.observer.demo3.RegObserver;

public class RegNotificationObserver implements RegObserver {

    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome..");
    }
}
