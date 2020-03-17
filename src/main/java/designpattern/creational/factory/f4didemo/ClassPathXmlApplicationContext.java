package designpattern.creational.factory.f4didemo;

import java.util.List;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeanFactory      beanFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beanFactory = new BeanFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinition(configLocation);
    }

    private void loadBeanDefinition(String configLocation) {
        List<BeanDefinition> beanDefinitions = beanConfigParser.parse(configLocation);
        beanFactory.addBeanDefinitions(beanDefinitions);
    }

    @Override
    public Object getBean(String beanId) throws NoSuchBeanDefinitionException {
        return beanFactory.getBean(beanId);
    }
}
