package designpattern.behavioral.visitor.demo6;

public class SingleDispatchClass {

    /**
     * polymorphism：多态性
     */
    public void polymorphismFunction(ParentClass p) {
        p.f();
    }

    public void overloadFunction(ChildClass c) {
        System.out.println("overloadFunction(ChildClass c)");
    }

    public void overloadFunction(ParentClass p) {
        System.out.println("overloadFunction(ParentClass p)");
    }

}
