package priciple.theory.eDIP.di.demo1;

public class Application {

    public static void main(String[] args) {
        MessageSender messageSender = new MessageSender();
        Notification notification = new Notification(messageSender);
    }

}
