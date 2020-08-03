package priciple.theory.bOCP.mqdemo;

import priciple.theory.bOCP.mqdemo.formatter.Formatter;
import priciple.theory.bOCP.mqdemo.mq.MessageQueue;
import priciple.theory.bOCP.v3.Notification;

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
