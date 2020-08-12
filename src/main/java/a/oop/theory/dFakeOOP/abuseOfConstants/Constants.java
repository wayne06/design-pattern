package a.oop.theory.dFakeOOP.abuseOfConstants;

/**
 * 在面向对象编程中，常见的全局变量有单例类对象、静态成员变量、常量等，常见的全局方法有静态方法。
 * - 单例类对象在全局代码中只有一份，所以，它相当于一个全局变量。
 * - 静态成员变量归属于类上的数据，被所有的实例化对象所共享，也相当于一定程度上的全局变量。
 * - 而常量是一种非常常见的全局变量，比如一些代码中的配置参数，一般都设置为常量，放到一个 Constants 类中。
 * - 静态方法一般用来操作静态变量或者外部数据。常用的各种 Utils 类，里面的方法一般都会定义成静态方法，可以在不用创建对象的情况下，
 *   直接拿来使用。静态方法将方法与数据分离，破坏了封装特性，是典型的面向过程风格。
 *
 * 定义一个如此大而全的 Constants 类，并不是一种很好的设计思路。
 * - 首先，这样的设计会影响代码的可维护性。
 *   如果参与开发同一个项目的工程师有很多，在开发过程中，可能都要涉及修改这个类，比如往这个类里添加常量，那这个类就会变得越来越大，
 *   成百上千行都有可能，查找修改某个常量也会变得比较费时，而且还会增加提交代码冲突的概率。
 * - 其次，这样的设计还会增加代码的编译时间。
 *   当 Constants 类中包含很多常量定义的时候，依赖这个类的代码就会很多。那每次修改 Constants 类，都会导致依赖它的类文件重新编译，
 *   因此会浪费很多不必要的编译时间。不要小看编译花费的时间，对于一个非常大的工程项目来说，编译一次项目花费的时间可能是几分钟，
 *   甚至几十分钟。而我们在开发过程中，每次运行单元测试，都会触发一次编译的过程，这个编译时间就有可能会影响到我们的开发效率。
 * - 最后，这样的设计还会影响代码的复用性。
 *   如果我们要在另一个项目中，复用本项目开发的某个类，而这个类又依赖 Constants 类。
 *   即便这个类只依赖 Constants 类中的一小部分常量，我们仍然需要把整个 Constants 类也一并引入，也就引入了很多无关的常量到新的项目中。
 *
 * 改进方式：
 * - 第一种是将 Constants 类拆解为功能更加单一的多个类：
 *   比如跟 MySQL 配置相关的常量，我们放到 MysqlConstants 类中；跟 Redis 配置相关的常量，我们放到 RedisConstants 类中。
 * - 第二种是并不单独地设计 Constants 常量类，而是哪个类用到了某个常量，我们就把这个常量定义到这个类中：
 *   比如，RedisConfig 类用到了 Redis 配置相关的常量，那就直接将这些常量定义在 RedisConfig 中，提高了类设计的内聚性和代码的复用性。
 *
 */
public class Constants {
    public static final String MYSQL_ADDR_KEY = "mysql_addr";
    public static final String MYSQL_DB_NAME_KEY = "db_name";
    public static final String MYSQL_USERNAME_KEY = "mysql_username";
    public static final String MYSQL_PASSWORD_KEY = "mysql_password";

    public static final String REDIS_DEFAULT_ADDR = "192.168.7.2:7234";
    public static final int REDIS_DEFAULT_TOTAL = 50;
    public static final int REDIS_DEFAULT_MAX_IDLE = 50;
    public static final int REDIS_DEFAULT_MIN_IDLE = 50;
    public static final String REDIS_DEFAULT_KEY_PREFIX = "rt:";

    // ...
}
