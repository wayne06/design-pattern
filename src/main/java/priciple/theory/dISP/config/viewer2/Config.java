package priciple.theory.dISP.config.viewer2;

import java.util.Map;

/**
 * 如果不遵守接口隔离原则，不设计 Updater 和 Viewer 两个小接口，
 * 而是设计一个大而全的 Config 接口，让 RedisConfig、KafkaConfig、MysqlConfig 都实现这个 Config 接口，
 * 并且将原来传递给 ScheduledUpdater 的 Updater 和传递给 SimpleHttpServer 的 Viewer，都替换为 Config
 * 按这个思路实现的代码如下：
 *
 * 对比前后两个设计思路，在同样的代码量、实现复杂度、同等可读性的情况下，第一种设计思路显然要比第二种好很多
 *
 * 首先，第一种设计思路更加灵活、易扩展、易复用。
 * 因为 Updater、Viewer 职责更加单一，单一就意味了通用、复用性好。
 * 比如现在又有一个新的需求，开发一个 Metrics 性能统计模块，并且希望将 Metrics 也通过 SimpleHttpServer 显示在网页上，以方便查看。
 * 这个时候尽管 Metrics 跟 RedisConfig 等没有任何关系，但我们仍然可以让 Metrics 类实现非常通用的 Viewer 接口，复用 SimpleHttpServer 的代码实现
 *
     public class ApiMetrics implements Viewer {//...}
     public class DbMetrics implements Viewer {//...}

     public class Application {
         ConfigSource configSource = new ZookeeperConfigSource();
         public static final RedisConfig redisConfig = new RedisConfig(configSource);
         public static final KafkaConfig kafkaConfig = new KakfaConfig(configSource);
         public static final MySqlConfig mySqlConfig = new MySqlConfig(configSource);
         public static final ApiMetrics apiMetrics = new ApiMetrics();
         public static final DbMetrics dbMetrics = new DbMetrics();

         public static void main(String[] args) {
             SimpleHttpServer simpleHttpServer = new SimpleHttpServer(“127.0.0.1”, 2389);
             simpleHttpServer.addViewer("/config", redisConfig);
             simpleHttpServer.addViewer("/config", mySqlConfig);
             simpleHttpServer.addViewer("/metrics", apiMetrics);
             simpleHttpServer.addViewer("/metrics", dbMetrics);
             simpleHttpServer.run();
         }
     }
 *
 * 其次，第二种设计思路在代码实现上做了一些无用功。
 * 因为 Config 接口中包含两类不相关的接口，一类是 update()，一类是 output() 和 outputInPlainText()。
 * 理论上，KafkaConfig 只需要实现 update() 接口，并不需要实现 output() 相关的接口。
 * 同理，MysqlConfig 只需要实现 output() 相关接口，并需要实现 update() 接口。
 * 但第二种设计思路要求 RedisConfig、KafkaConfig、MySqlConfig 必须同时实现 Config 的所有接口函数（update、output、outputInPlainText）。
 * 除此之外，如果我们要往 Config 中继续添加一个新的接口，那所有的实现类都要改动。
 * 相反，如果我们的接口粒度比较小，那涉及改动的类就比较少。
 *
 */
public interface Config {

    void update();
    String outputInPlainText();
    Map<String, String> output();

}
