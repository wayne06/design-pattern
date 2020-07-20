package designpattern.structural.proxy.v3;

import designpattern.structural.proxy.UserVo;
import inaction.metrics.ver3.MetricsCollector;
import inaction.metrics.ver3.RedisMetricsStorage;
import inaction.metrics.ver3.RequestInfo;

/**
 * @author wayne
 *
 * 如果原始类没有定义接口，其不能直接修改
 *
 * 这种情况下：让代理类继承原始类，然后扩展附加功能
 */
public class UserControllerProxy extends UserController {

    private MetricsCollector metricsCollector;

    public UserControllerProxy() {
        this.metricsCollector = new MetricsCollector(new RedisMetricsStorage());
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.login(telephone, password);

        long endTimestamp = System.currentTimeMillis();
        long responseTime = endTimestamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.register(telephone, password);

        long endTimestamp = System.currentTimeMillis();
        long responseTime = endTimestamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }
}
