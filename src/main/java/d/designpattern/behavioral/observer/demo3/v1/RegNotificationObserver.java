package d.designpattern.behavioral.observer.demo3.v1;

import d.designpattern.behavioral.observer.demo3.NotificationService;
import d.designpattern.behavioral.observer.demo3.RegObserver;

public class RegNotificationObserver implements RegObserver {

    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notificationService.sendInboxMessage(userId, "Welcome..");
            }
        });
        thread.start();
    }

    //@Override
    //public void handleRegSuccess(long userId) {
    //    notificationService.sendInboxMessage(userId, "Welcome..");
    //}
}
