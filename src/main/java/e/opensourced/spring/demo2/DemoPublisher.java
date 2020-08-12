package e.opensourced.spring.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class DemoPublisher {

    @Autowired
    private ApplicationContext context;

    public void publishEvent(DemoEvent event) {
        this.context.publishEvent(event);
    }

}
