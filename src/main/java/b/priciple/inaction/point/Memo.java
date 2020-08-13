package b.priciple.inaction.point;

/**
 * 需求分析：
 * 1. 积分赚取和兑换规则：积分的赚取渠道包括：下订单、每日签到、评论等
 * 2. 积分消费和兑换规则：积分的消费渠道包括：抵扣订单金额、兑换优惠券、积分换购、参与活动扣积分等
 * 3. 积分及其明细查询：查询用户的总积分，以及赚取积分和消费积分的历史记录
 *
 * 系统设计：
 * 1. 合理地将功能划分到不同模块：
 * 如果一个功能的修改或添加，经常要跨团队、跨项目、跨系统才能完成，那说明模块划分的不够合理，职责不够清晰，耦合过于严重
 * 积分赚取渠道及兑换规则、消费渠道及兑换规则的管理和维护（增删改查），不划分到积分系统中，而是放到更上层的营销系统中。
 * 这样积分系统就会变得非常简单，只需要负责增加积分、减少积分、查询积分、查询积分明细等这几个工作
 * 2. 设计模块与模块之间的交互关系：
 * 比较常见的系统之间的交互方式有两种，一种是同步接口调用，另一种是利用消息中间件异步调用。第一种简单直接，第二种的解耦效果更好
 * 上下层系统之间的调用倾向于通过同步接口，同层之间的调用倾向于异步消息调用。如营销系统和积分系统是上下层关系，就推荐使用同步接口调用
 * 3. 设计模块的接口、数据库、业务模型：
 * 数据库和接口的设计非常重要，一旦设计好并投入使用之后，这两部分都不能轻易改动。
 * 改动数据库表结构，需要涉及数据的迁移和适配；改动接口，需要推动接口的使用者作相应的代码修改
 *
 * 数据库设计：只需要一张记录积分流水明细的表就可以
 *   id             明细ID
 *   user_id        用户ID
 *   channel_id     赚取或消费渠道ID
 *   event_id       相关事件ID，如订单ID、评论ID、优惠券换购交易ID
 *   credit         积分：赚取为正、消费为负
 *   create_time    积分赚取或消费时间
 *   expired_time   积分过期时间
 *
 * 接口设计：
 * 接口设计要符合单一职责原则，粒度越小通用性就越好。但是，接口粒度太小也会带来：若远程调用影响性能；涉及分布式事务的数据一致性问题
 * 为了兼顾易用性和性能，我们可以借鉴 facade（外观）设计模式，在职责单一的细粒度接口之上，再封装一层粗粒度的接口给外部使用
 *   接口            参数                                            返回
 *   赚取积分         userId,channelId,eventId,credit,expiredTime    积分明细ID
 *   消费积分         userId,channelId,eventId,credit,expiredTime    积分明细ID
 *   查询积分         userId                                         总可用积分
 *   查询总积分明细    userId+分页参数                                 id,userId,channelId,eventId,credit,expiredTime,expiredTime
 *   查询赚取积分明细  userId+分页参数                                 id,userId,channelId,eventId,credit,expiredTime,expiredTime
 *   查询消费积分明细  userId+分页参数                                 id,userId,channelId,eventId,credit,expiredTime,expiredTime
 *
 * 业务模型设计：
 * 对于我们要开发的积分系统来说，因为业务相对比较简单，所以，选择简单的基于贫血模型的传统开发模式就足够
 * 从开发的角度来说，可以把积分系统作为一个独立的项目，来独立开发，也可以跟其他业务代码（比如营销系统）放到同一个项目中进行开发。
 * 从运维的角度来说，可以将它跟其他业务一块部署，也可以作为一个微服务独立部署
 *
 * 为什么要分 MVC 三层开发：
 * 1. 分层能起到代码复用的作用
 * 同一个 Repository 可能会被多个 Service 来调用，同一个 Service 可能会被多个 Controller 调用。
 * 比如，UserService 中的 getUserById() 接口封装了通过 ID 获取用户信息的逻辑，
 * 这部分逻辑可能会被 UserController 和 AdminController 等多个 Controller 使用。
 * 如果没有 Service 层，每个 Controller 都要重复实现这部分逻辑，显然会违反 DRY 原则。
 * 2. 分层能起到隔离变化的作用
 * 分层体现了一种抽象和封装的设计思想。比如，Repository 层封装了对数据库访问的操作，提供了抽象的数据访问接口。
 * 基于接口而非实现编程的设计思想，Service 层使用 Repository 层提供的接口，并不关心其底层依赖的是哪种具体的数据库。
 * 当我们需要替换数据库的时候，比如从 MySQL 到 Oracle，从 Oracle 到 Redis，只需要改动 Repository 层的代码，Service 层不需要修改。
 * 除此之外，Controller、Service、Repository 三层代码的稳定程度不同、引起变化的原因不同，所以分成三层来组织代码，能有效地隔离变化。
 * 比如，Repository 层基于数据库表，而数据库表改动的可能性很小，所以 Repository 层的代码最稳定，
 * 而 Controller 层提供适配给外部使用的接口，代码经常会变动。
 * 分层之后，Controller 层中代码的频繁改动并不会影响到稳定的 Repository 层。
 * 3. 分层能起到隔离关注点的作用
 * Repository 层只关注数据的读写。
 * Service 层只关注业务逻辑，不关注数据的来源。
 * Controller 层只关注与外界打交道，数据校验、封装、格式转换，并不关心业务逻辑。
 * 三层之间的关注点不同，分层之后，职责分明，更加符合单一职责原则，代码的内聚性更好。
 * 4. 分层能提高代码的可测试性
 * 单元测试不依赖不可控的外部组件，比如数据库。
 * 分层之后，Repository 层的代码通过依赖注入的方式供 Service 层使用，
 * 当要测试包含核心业务逻辑的 Service 层代码的时候，我们可以用 mock 的数据源替代真实的数据库，注入到 Service 层代码中。
 * 5. 分层能应对系统的复杂性
 * 所有的代码都放到一个类中，那这个类的代码就会因为需求的迭代而无限膨胀。
 * 当一个类或一个函数的代码过多之后，可读性、可维护性就会变差。那我们就要想办法拆分。拆分有垂直和水平两个方向。
 * 水平方向基于业务来做拆分，就是模块化；垂直方向基于流程来做拆分，就是这里说的分层。
 *
 * BO、VO、Entity 存在的意义是什么？
 * 1. VO、BO、Entity 并非完全一样。比如可以在 UserEntity、UserBo 中定义 Password 字段，但不能在 UserVo 中定义 Password 字段
 * 2. VO、BO、Entity 三个类虽然代码重复，但功能语义不重复，从职责上讲是不一样的。所以，也并不能算违背 DRY 原则。
 * 在前面讲到 DRY 原则的时候，针对这种情况，如果合并为同一个类，那也会存在后期因为需求的变化而需要再拆分的问题。
 * 3. 为了尽量减少每层之间的耦合，把职责边界划分明确，每层都会维护自己的数据对象，层与层之间通过接口交互。
 * 数据从下一层传递到上一层的时候，将下一层的数据对象转化成上一层的数据对象，再继续处理。
 * 虽然这样的设计稍微有些繁琐，每层都需要定义各自的数据对象，需要做数据对象之间的转化，但是分层清晰。
 * 对于非常大的项目来说，结构清晰是第一位的！
 *
 * 既然 VO、BO、Entity 不能合并，那如何解决代码重复的问题呢？
 * 1. 可以将公共的字段定义在父类中，让 VO、BO、Entity 都继承这个父类，各自只定义特有的字段。
 * 因为这里的继承层次很浅，也不复杂，所以使用继承并不会影响代码的可读性和可维护性。
 * 后期如果因为业务的需要，有些字段需要从父类移动到子类，或者从子类提取到父类，代码改起来也并不复杂。
 * 2. 前面在讲“多用组合，少用继承”设计思想的时候，我们提到，组合也可以解决代码重复的问题，
 * 所以，这里我们还可以将公共的字段抽取到公共的类中，VO、BO、Entity 通过组合关系来复用这个类的代码
 *
 * 不同分层之间的数据对象该如何互相转化呢？
 * 1. 最简单的转化方式是手动复制。自己写代码在两个对象之间，一个字段一个字段的赋值。但这样的做法显然是没有技术含量的低级劳动。
 * Java 中提供了多种数据对象转化工具，比如 BeanUtils、Dozer 等，可以大大简化繁琐的对象转化工作。
 * 如果你是用其他编程语言来做开发，也可以借鉴 Java 这些工具类的设计思路，自己在项目中实现对象转化工具类。
 * 2. VO、BO、Entity 都是基于贫血模型的，而且为了兼容框架或开发库（比如 MyBatis、Dozer、BeanUtils），还需要定义每个字段的 set 方法。
 * 这些都违背 OOP 的封装特性，会导致数据被随意修改。那到底该怎么办好呢？
 * 3. Entity 和 VO 的生命周期是有限的，都仅限在本层范围内。
 * 而对应的 Repository 层和 Controller 层也都不包含太多业务逻辑，所以也不会有太多代码随意修改数据，
 * 即便设计成贫血、定义每个字段的 set 方法，相对来说也是安全的。
 * 不过，Service 层包含比较多的业务逻辑代码，所以 BO 就存在被任意修改的风险了。
 * 但是，设计的问题本身就没有最优解，只有权衡。
 * 为了使用方便，我们只能做一些妥协，放弃 BO 的封装特性，由程序员自己来负责这些数据对象的不被错误使用。
 *
 * 总结用到的设计原则和思想：
 * 1. 高内聚、松耦合：
 *    将不同的功能划分到不同的模块，遵从的划分原则就是尽量让模块本身高内聚，让模块之间松耦合
 * 2. 单一职责原则：
 *    分层的一个目的也是为了更加符合单一职责原则
 * 3. 依赖注入：
 *    MVC三层结构中，下一层的类通过依赖注入的方式注入到上一层代码中
 * 4. 依赖反转原则：
 *    业务系统开发中，通过类似Spring IOC这样的容器来管理对象的创建、生命周期，就用到了依赖反转
 * 5. 基于接口而非实现编程：
 *    MVC三层结构中，service层使用repository层提供的接口，并不关心其底层是依赖的那种数据库，尊崇基于接口而非实现编程
 * 6. 封装、抽象：
 *    分层体现了抽象和封装的设计思想，能够隔离变化、隔离关注点
 * 7. DRY与继承和组合：
 *    尽管VO、BO、Entity存在代码重复，但功能语义不同，不违反DRY；为解决三者重复的问题，用到了继承或组合
 * 8. 面向对象设计：
 *    面向对象设计的本质是将合适的代码放到合适的类中
 *
 */
public class Memo {
}
