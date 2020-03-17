package designpattern.creational.factory.f4didemo;

public interface ApplicationContext {
    Object getBean(String beanId) throws NoSuchBeanDefinitionException;
}
