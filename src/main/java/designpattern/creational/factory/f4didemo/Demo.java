package designpattern.creational.factory.f4didemo;

public class Demo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        RateLimiter ratelimiter = null;
        try {
            ratelimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
        ratelimiter.test();
    }

}
