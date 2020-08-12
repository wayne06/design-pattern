package d.designpattern.creational.factory.f4didemo;

import java.util.List;

public interface BeanConfigParser {
    List<BeanDefinition> parse(String configLocation);
}
