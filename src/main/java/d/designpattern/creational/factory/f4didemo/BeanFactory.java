package d.designpattern.creational.factory.f4didemo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonMap = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            beanDefinitionMap.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && singletonMap.containsKey(beanDefinition.getId())) {
            return singletonMap.get(beanDefinition.getId());
        }
        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());

            List<BeanDefinition.ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            if (constructorArgs.isEmpty()) {
                bean = beanClass.newInstance();
            } else {
                Class[] argClasses = new Class[constructorArgs.size()];
                Object[] argObjects = new Object[constructorArgs.size()];

                for (int i = 0; i < constructorArgs.size(); i++) {
                    BeanDefinition.ConstructorArg arg = constructorArgs.get(i);
                    if (!arg.isRef()) {
                        argClasses[i] = arg.getType();
                        argObjects[i] = arg.getArg();
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitionMap.get(arg.getArg());
                        if (beanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.getArg());
                        }
                        argObjects[i] = createBean(refBeanDefinition);
                        argClasses[i] = argObjects[i].getClass();
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | NoSuchBeanDefinitionException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (bean != null && beanDefinition.isSingleton()) {
            singletonMap.putIfAbsent(beanDefinition.getId(), bean);
            return singletonMap.get(beanDefinition.getId());
        }
        return bean;
    }

    public Object getBean(String beanId) throws NoSuchBeanDefinitionException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("Bean is not defined: " + beanId);
        }
        return createBean(beanDefinition);
    }
}
