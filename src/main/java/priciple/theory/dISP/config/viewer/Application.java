package priciple.theory.dISP.config.viewer;

/**
 * 又有了一个新的监控功能需求：通过命令行来查看 Zookeeper 中的配置信息是比较麻烦的，希望能有一种更加方便的配置信息查看方式。
 *
 * 可以在项目中开发一个内嵌的 SimpleHttpServer，输出项目的配置信息到一个固定的 HTTP 地址如：http://127.0.0.1:2389/config 。
 * 只需要在浏览器中输入这个地址，就可以显示出系统的配置信息。
 * 不过，出于某些原因，我们只想暴露 MySQL 和 Redis 的配置信息，不想暴露 Kafka 的配置信息。
 *
 * 我们设计了两个功能非常单一的接口：Updater 和 Viewer。
 * ScheduledUpdater 只依赖 Updater 这个跟热更新相关的接口，不需要被强迫去依赖不需要的 Viewer 接口，满足接口隔离原则。
 * 同理，SimpleHttpServer 只依赖跟查看信息相关的 Viewer 接口，不依赖不需要的 Updater 接口，也满足接口隔离原则。
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

        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 2389);
        simpleHttpServer.addViewers("/config", REDIS_CONFIG);
        simpleHttpServer.addViewers("/config", MYSQL_CONFIG);
        simpleHttpServer.run();
    }
}
