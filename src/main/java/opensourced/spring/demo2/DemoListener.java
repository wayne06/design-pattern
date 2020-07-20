package opensourced.spring.demo2;

import org.springframework.context.ApplicationListener;

public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String message = event.getMessage();
        System.out.println(message);
    }
}
