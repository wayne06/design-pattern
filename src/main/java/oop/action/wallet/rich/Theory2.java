package oop.action.wallet.rich;

/**
 * 第一个要讨论的问题：
 *
 * 在基于充血模型的 DDD 开发模式中，将业务逻辑移动到 Domain 中，Service 类变得很薄，但在我们的代码设计与实现中，
 * 并没有完全将 Service 类去掉，这是为什么？或者说，Service 类在这种情况下担当的职责是什么？哪些功能逻辑会放到 Service 类中？
 *
 * 区别于 Domain 的职责，Service 类主要有下面这样几个职责。
 *
 * 1.Service 类负责与 Repository 交流。
 * 在我的设计与代码实现中，VirtualWalletService 类负责与 Repository 层打交道，调用 Respository 类的方法，获取数据库中的数据，
 * 转化成领域模型 VirtualWallet，然后由领域模型 VirtualWallet 来完成业务逻辑，最后调用 Repository 类的方法，将数据存回数据库。
 * 这里我再稍微解释一下，之所以让 VirtualWalletService 类与 Repository 打交道，
 * 而不是让领域模型 VirtualWallet 与 Repository 打交道，那是因为我们想保持领域模型的独立性，
 * 不与任何其他层的代码（Repository 层的代码）或开发框架（比如 Spring、MyBatis）耦合在一起，
 * 将流程性的代码逻辑（比如从 DB 中取数据、映射数据）与领域模型的业务逻辑解耦，让领域模型更加可复用。
 *
 * 2.Service 类负责跨领域模型的业务聚合功能。VirtualWalletService 类中的 transfer() 转账函数会涉及两个钱包的操作，
 * 因此这部分业务逻辑无法放到 VirtualWallet 类中，所以，我们暂且把转账业务放到 VirtualWalletService 类中了。
 * 当然，虽然功能演进，使得转账业务变得复杂起来之后，我们也可以将转账业务抽取出来，设计成一个独立的领域模型。
 *
 * 3.Service 类负责一些非功能性及与三方系统交互的工作。
 * 比如幂等、事务、发邮件、发消息、记录日志、调用其他系统的 RPC 接口等，都可以放到 Service 类中。
 *
 *
 * 第二个要讨论问题：
 *
 * 在基于充血模型的 DDD 开发模式中，尽管 Service 层被改造成了充血模型，但是 Controller 层和 Repository 层还是贫血模型，
 * 是否有必要也进行充血领域建模呢？
 *
 * 答案是没有必要。
 *
 * Controller 层主要负责接口的暴露，Repository 层主要负责与数据库打交道，这两层包含的业务逻辑并不多，
 * 如果业务逻辑比较简单，就没必要做充血建模，即便设计成充血模型，类也非常单薄，看起来也很奇怪。
 *
 * 尽管这样的设计是一种面向过程的编程风格，但我们只要控制好面向过程编程风格的副作用，照样可以开发出优秀的软件。
 * 那这里的副作用怎么控制呢？就拿 Repository 的 Entity 来说，即便它被设计成贫血模型，违反面向对象编程的封装特性，
 * 有被任意代码修改数据的风险，但 Entity 的生命周期是有限的。
 * 一般来讲，我们把它传递到 Service 层之后，就会转化成 BO 或者 Domain 来继续后面的业务逻辑。
 * Entity 的生命周期到此就结束了，所以也并不会被到处任意修改。
 *
 * 再来说说 Controller 层的 VO。实际上 VO 是一种 DTO（Data Transfer Object，数据传输对象）。
 * 它主要是作为接口的数据传输承载体，将数据发送给其他系统。从功能上来讲，它理应不包含业务逻辑、只包含数据。
 * 所以，我们将它设计成贫血模型也是比较合理的。
 */
public class Theory2 {


}
