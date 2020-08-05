package priciple.theory.eDIP.ioc.demo1;

/**
 * 控制反转的英文翻译是 Inversion Of Control，缩写为 IOC
 *
 * 下面的代码中，所有的流程都由程序员来控制。
 *
 * 如何利用框架来实现同样的功能？见 demo2
 *
 */
public class UserServiceTest {

    public static boolean doTest() {
        //...
        return false;
    }

    public static void main(String[] args) {
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }
    }

}
