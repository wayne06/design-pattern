package priciple.theory.fYAGNI;

/**
 * YAGNI：You Ain't Gonna Need It.
 *
 * 不要去设计当前用不到的功能；不要去编写当前用不到的代码。
 * 实际上，这条原则的核心思想就是：不要做过度设计
 *
 * 例如，我们的系统暂时只用 Redis 存储配置信息，以后可能会用到 ZooKeeper。
 * 根据 YAGNI 原则，在未用到 ZooKeeper 之前，我们没必要提前编写这部分代码。
 * 当然，这并不是说我们就不需要考虑代码的扩展性。我们还是要预留好扩展点，等到需要的时候，再去实现 ZooKeeper 存储配置信息这部分代码。
 *
 * 例如，我们不要在项目中提前引入不需要依赖的开发包。
 * 对于 Java 程序员来说，我们经常使用 Maven 或者 Gradle 来管理依赖的类库（library）。
 * 有些同事为了避免开发中 library 包缺失而频繁地修改 Maven 或者 Gradle 配置文件，提前往项目里引入大量常用的 library 包。
 * 实际上，这样的做法也是违背 YAGNI 原则的。
 *
 * KISS 原则讲的是“如何做”的问题（尽量保持简单），而 YAGNI 原则说的是“要不要做”的问题（当前不需要的就不要做）
 *
 */
public class Memo {

}
