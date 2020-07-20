package designpattern.structural.decorator.demo3.proxy;

public class AProxy implements IA {

    private IA a;

    public AProxy(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 新添加的代理逻辑
        a.f();
        // 新添加的代理逻辑
    }

}
