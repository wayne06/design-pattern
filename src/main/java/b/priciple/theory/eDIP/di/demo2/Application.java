package b.priciple.theory.eDIP.di.demo2;

public class Application {

    public static void main(String[] args) {
        MessageSender messageSender = new SmsMessageSender();
        Notification notification = new Notification(messageSender);
        notification.sendMessage("13232323232", "短信验证码：2345");
    }

}
