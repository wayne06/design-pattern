package priciple.theory.bOCP.mqdemo;

import priciple.theory.bOCP.alert3.Notification;
import priciple.theory.bOCP.mqdemo.formatter.MessageFormatter;
import priciple.theory.bOCP.mqdemo.mq.MessageQueue;

/**
 * 指导思想：
 *
 * 为了尽量写出扩展性好的代码，要时刻具备扩展意识、抽象意识、封装意识。这些“潜意识”可能比任何开发技巧都重要。
 * 在写代码的时候，要花点时间往前多思考一下，这段代码未来可能有哪些需求变更、如何设计代码结构，事先留好扩展点，
 * 以便在未来需求变更的时候，不需要改动代码整体结构、做到最小代码改动的情况下，新的代码能够很灵活地插入到扩展点上，
 * 做到“对扩展开放、对修改关闭”。
 *
 * 还有，在识别出代码可变部分和不可变部分之后，我们要将可变部分封装起来，隔离变化，提供抽象化的不可变接口，给上层系统使用。
 * 当具体的实现发生变化的时候，我们只需要基于相同的抽象接口，扩展一个新的实现，替换掉老的实现即可，上游系统的代码几乎不需要修改。
 *
 *
 * 方法论：
 *
 * 多态、依赖注入、基于接口而非实现编程，以及大部分的设计模式（比如，装饰、策略、模板、职责链、状态等）
 *
 * 比如代码中通过 Kafka 来发送异步消息。对于这样一个功能的开发，我们要学会将其抽象成一组跟具体消息队列（Kafka）无关的异步消息接口。
 * 所有上层系统都依赖这组抽象的接口编程，并且通过依赖注入的方式来调用。
 * 当我们要替换新的消息队列的时候，比如将 Kafka 替换成 RocketMQ，可以很方便地拔掉老的消息队列实现，插入新的消息队列实现
 *
 */
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
     * @param messageFormatter
     * 多态、依赖注入
     */
    public void sendNotification(Notification notification, MessageFormatter messageFormatter) {

    }

}
