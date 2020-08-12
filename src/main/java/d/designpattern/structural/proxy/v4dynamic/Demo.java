package d.designpattern.structural.proxy.v4dynamic;

public class Demo {

    public static void main(String[] args) {

        // MetricsCollectorProxy使用举例
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
    }
}
