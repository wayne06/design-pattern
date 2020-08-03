package priciple.theory.bOCP.v4;

/**
 * @author wayne
 */
public class Demo {

    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();

        // ...省略设置apiStatInfo的数据值代码

        // 改动4：使用Alert类时，给check()方法的入参apiStatInfo对象设置timeoutCount的值
        apiStatInfo.setTimeoutCount(289);

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);


    }
}
