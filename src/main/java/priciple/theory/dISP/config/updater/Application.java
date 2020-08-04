package priciple.theory.dISP.config.updater;

/**
 * 假设我们的项目中用到了三个外部系统：Redis、MySQL、Kafka。每个系统都对应一系列配置信息，比如地址、端口、访问超时时间等。
 * 为了在内存中存储这些配置信息，供项目中的其他模块来使用，我们分别设计实现了三个类：RedisConfig、MysqlConfig、KafkaConfig
 *
 * 现在有一个新的功能需求，希望支持 Redis 和 Kafka 配置信息的热更新，不对 MySQL 的配置信息进行热更新。
 * 为了实现这样一个功能需求，我们设计实现了一个 ScheduledUpdater 类，
 * 以固定时间频率（periodInSeconds）来调用 RedisConfig、KafkaConfig 的 update() 方法更新配置信息
 *
 *
 */
public class Application {
    private static ConfigSource configSource = new ZookeeperConfigSource();
    public static final RedisConfig REDIS_CONFIG = new RedisConfig(configSource);
    public static final KafkaConfig KAFKA_CONFIG = new KafkaConfig(configSource);
    public static final MysqlConfig MYSQL_CONFIG = new MysqlConfig(configSource);

    public static void main(String[] args) {
        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(
                300, 300, REDIS_CONFIG);
        redisConfigUpdater.run();

        ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(
                60, 60, KAFKA_CONFIG);
        kafkaConfigUpdater.run();
    }
}
