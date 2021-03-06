package d.designpattern.structural.decorator.demo3.decorator;

public class ADecorator implements IA {

    private IA a;

    public ADecorator(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 功能增强代码
        a.f();
        // 功能增强代码
    }
}
