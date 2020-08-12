package d.designpattern.behavioral.observer.demo5;

import java.util.concurrent.Executor;

public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
