package b.priciple.theory.dISP.config.viewer;

import java.util.Map;

public class MysqlConfig implements Viewer {

    private ConfigSource configSource;  // 配置中心（比如 zookeeper）
    private String       address;
    private int          timeout;
    private int          maxTotal;

    //...省略其他配置：maxWaitMillis, maxIdle, minIdle...


    public MysqlConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public String outputInPlainText() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }

    //...省略其他 get、init 方法


}
