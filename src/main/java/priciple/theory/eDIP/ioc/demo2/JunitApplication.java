package priciple.theory.eDIP.ioc.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象出一个下面这样一个框架:
 *
 * 把这个简化版本的测试框架引入到工程中之后，只需要在框架预留的扩展点，也就是 TestCase 类中的 doTest() 抽象函数中，
 * 填充具体的测试代码就可以实现之前的功能了，完全不需要写负责执行流程的 main() 函数了
 *
 */
public class JunitApplication {

    private static final List<TestCase> TEST_CASES = new ArrayList<>();

    public static void register(TestCase testCase) {
        TEST_CASES.add(testCase);
    }

    public static void main(String[] args) {
        for (TestCase testCase : TEST_CASES) {
            testCase.run();
        }
    }

}
