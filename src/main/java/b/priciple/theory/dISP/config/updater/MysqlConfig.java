package b.priciple.theory.dISP.config.updater;

public class MysqlConfig implements Updater {

    private ConfigSource configSource;  // 配置中心（比如 zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;

    //...省略其他配置：maxWaitMillis, maxIdle, minIdle...


    public MysqlConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return this.address;
    }

    //...省略其他 get、init 方法

    @Override
    public void update() {
        // 从 configSource 加载配置到 address/timeout/maxTotal...
    }

}
