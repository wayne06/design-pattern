package designpattern.behavioral.template.demo1;

/**
 * 模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现。
 *
 * 模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤
 */
public class Demo {

    public static void main(String[] args) {
        AbstractClass demo = new ConcreteClass1();
        demo.templateMethod();
    }
}
