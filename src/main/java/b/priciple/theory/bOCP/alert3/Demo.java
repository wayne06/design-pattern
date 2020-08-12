package b.priciple.theory.bOCP.alert3;

/**
 * @author wayne
 */
public class Demo {

    public static void main(String[] args) {

        ApiStatInfo apiStatInfo = new ApiStatInfo();

        // ...省略设置 apiStatInfo 的数据值代码

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);


    }
}
