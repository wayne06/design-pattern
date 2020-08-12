package d.designpattern.structural.adapter.v1.classadaptor;

/**
 * 类适配器：基于继承
 *
 * 将Adaptee转化成一组符合ITarget接口定义的接口
 */
public class Adaptor extends Adaptee implements ITarget {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        // ...重新实现f2()...
    }

    // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点

}
