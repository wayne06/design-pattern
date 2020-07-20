package opensourced.guava.demo3.wrapper;

public class WrapperClass implements Interf {

    private OriginalClass originalClass;

    public WrapperClass(OriginalClass originalClass) {
        this.originalClass = originalClass;
    }

    @Override
    public void f1() {
        //...附加功能...
        this.originalClass.f1();
        //...附加功能...
    }

    @Override
    public void f2() {
        this.originalClass.f2();
    }
}
