package oop.theory.eInterfaceAndAbstractClass.aCompare.abstractClass;

import java.util.logging.Level;

/**
 * 抽象类的子类：输出日志到消息中间件，如 kafka
 */
public class MessageQueueLogger extends Logger {

    private MessageQueueClient messageQueueClient;

    public MessageQueueLogger(String name, boolean enabled, Level minPermittedLevel, MessageQueueClient messageQueueClient) {
        super(name, enabled, minPermittedLevel);
        this.messageQueueClient = messageQueueClient;
    }

    @Override
    protected void doLog(Level level, String message) {
        // 格式化 level 和 message，输出到消息中间件
        String result = level + " : " + message;
        messageQueueClient.send(result);
    }
}
