package d.designpattern.structural.adapter.v1.objectadaptor;

/**
 * 对象适配器：基于组合
 *
 * 将Adaptee转化成一组符合ITarget接口定义的接口
 */
public class Adaptor implements ITarget {

    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        // 委托给Adaptee
        adaptee.fa();
    }

    @Override
    public void f2() {
        // ...重新实现f2()...
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}
