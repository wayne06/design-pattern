package d.designpattern.creational.factory.f4didemo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parse(String configLocation) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        File file = new File("src/main/resources/beans.xml");
        try {
            Document document = new SAXReader().read(file);
            Element rootElement = document.getRootElement();
            List<Element> beans = rootElement.elements("bean");
            for (int i = 0; i < beans.size(); i++) {
                Element bean = beans.get(i);
                String beanId = bean.attributeValue("id");
                String beanClass = bean.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClass);
                if ("singleton".equals(bean.attributeValue("scope"))) {
                    beanDefinition.setScope(BeanDefinition.Scope.SINGLETON);
                }
                if ("true".equals(bean.attributeValue("lazy-init"))) {
                    beanDefinition.setLazyInit(true);
                }
                List<Element> args = bean.elements("constructor-arg");
                loadConstructorArgs(beanDefinition, args);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return beanDefinitions;
    }

    private void loadConstructorArgs(BeanDefinition beanDefinition, List<Element> args) {
        for (int i = 0; i < args.size(); i++) {
            Element arg = args.get(i);
            BeanDefinition.ConstructorArg constructorArg = null;
            try {
                if ((arg.attributeValue("type") != null) && (!arg.attributeValue("type").isEmpty())) {
                    constructorArg = new BeanDefinition.ConstructorArg();
                    constructorArg.setType(Class.forName(arg.attributeValue("type")));
                    constructorArg.setArg(arg.attributeValue("value"));
                }
                if ((arg.attributeValue("ref") != null) && !arg.attributeValue("ref").isEmpty()) {
                    constructorArg = new BeanDefinition.ConstructorArg();
                    constructorArg.setRef(true);
                    constructorArg.setArg(arg.attributeValue("ref"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            beanDefinition.addConstructorArg(constructorArg);
        }
    }

}
