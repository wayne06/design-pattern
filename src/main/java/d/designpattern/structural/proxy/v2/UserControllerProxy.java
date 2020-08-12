package d.designpattern.structural.proxy.v2;

import d.designpattern.structural.proxy.UserVo;
import f.inaction.metrics.ver3.MetricsCollector;
import f.inaction.metrics.ver3.RedisMetricsStorage;
import f.inaction.metrics.ver3.RequestInfo;

public class UserControllerProxy implements IUserController {

    private MetricsCollector metricsCollector;

    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector(new RedisMetricsStorage());
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        // 委托
        UserVo userVo = userController.login(telephone, password);

        long endTimestamp = System.currentTimeMillis();
        long responseTime = endTimestamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        // 委托
        UserVo userVo = userController.register(telephone, password);

        long endTimestamp = System.currentTimeMillis();
        long responseTime = endTimestamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }
}
