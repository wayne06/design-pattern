package designpattern.behavioral.command.demo1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 命令模式：
 * 将请求（命令）封装为一个对象，这样可以使用不同的请求参数化其他对象（将不同请求依赖注入到其他对象），
 * 并且能够支持请求（命令）的排队执行、记录日志、撤销等（附加控制）功能
 *
 * 命令模式用的最核心的实现手段，是将函数封装成对象。C 语言支持函数指针，可以把函数当作变量传递来传递去。
 * 但是，在大部分编程语言中，函数没法作为参数传递给其他函数，也没法儿赋值给变量。借助命令模式，我们可以将函数封装成对象。
 * 具体就是，设计一个包含这个函数的类，实例化一个对象传来传去，这样就可以实现把函数像对象一样使用。从实现的角度来说，它类似前讲过的回调。
 *
 * 当我们把函数封装成对象之后，对象就可以存储下来，方便控制执行。所以，命令模式的主要作用和应用场景，是用来控制命令的执行，
 * 比如，异步、延迟、排队执行命令、撤销重做命令、存储命令、给命令记录日志等等，这才是命令模式能发挥独一无二作用的地方
 *
 * 案例：
 * 整个手游后端服务器轮询获取客户端发来的请求，获取到请求之后，借助命令模式，把请求包含的数据和处理逻辑封装为命令对象，并存储在内存队列中。
 * 然后，再从队列中取出一定数量的命令来执行。执行完成之后，再重新开始新的一轮轮询
 *
 *
 */
public class GameApplication {

    private static final int MAX_HANDLED_REQ_COUNT_PER_LOOP = 100;
    private Queue<Command> commandQueue = new LinkedList<>();

    public void mainloop() {
        while (true) {
            List<Request> requests = new ArrayList<>();

            // 省略从 epoll 或 select 中获取数据，并封装成 Request 的逻辑
            // 注意设置超时时间，如果很长时间没有接收到请求，就继续下面的逻辑处理

            for (Request request : requests) {
                Event event = request.getEvent();
                Command command = null;
                if (event.equals(Event.GOT_DIAMOND)) {
                    command = new GotDiamondCommand(/*数据*/);
                } else if (event.equals(Event.GOT_STAR)) {
                    command = new GotStarCommand(/*数据*/);
                } else if (event.equals(Event.HIT_OBSTACLE)) {
                    command = new HitObstacleCommand(/*数据*/);
                } else if (event.equals(Event.ARCHIVE)) {
                    command = new ArchiveCommand(/*数据*/);
                } // 省略一堆 else if

                commandQueue.add(command);
            }
            int handledCount = 0;
            while (handledCount < MAX_HANDLED_REQ_COUNT_PER_LOOP) {
                if (commandQueue.isEmpty()) {
                    break;
                }
                Command command = commandQueue.poll();
                command.execute();
            }
        }
    }

}
