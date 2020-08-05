package priciple.theory.eDIP.ioc.demo2;

public abstract class TestCase {

    public void run() {
        if (doTest()) {
            System.out.println("test succeed.");
        } else {
            System.out.println("test failed.");
        }
    }

    public abstract boolean doTest();

}
