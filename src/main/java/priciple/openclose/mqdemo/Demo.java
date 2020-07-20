package priciple.openclose.mqdemo;

import priciple.openclose.mqdemo.formatter.Formatter;
import priciple.openclose.mqdemo.mq.MessageQueue;
import priciple.openclose.v3.Notification;

public class Demo {

    /**
     * 基于接口而非实现编程
     */
    private MessageQueue messageQueue;


    /**
     * @param messageQueue
     *
     * 依赖注入
     */
    public Demo(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * @param notification
     * @param formatter
     * 多态、依赖注入
     */
    public void sendNotification(Notification notification, Formatter formatter) {

    }

}
