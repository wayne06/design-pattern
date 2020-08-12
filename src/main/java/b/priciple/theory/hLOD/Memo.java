package b.priciple.theory.hLOD;

/**
 * 高内聚、松耦合
 *
 * “高内聚、松耦合”是一个非常重要的设计思想，能够有效地提高代码的可读性和可维护性，缩小功能改动导致的代码改动范围。
 * 很多设计原则都以实现代码的“高内聚、松耦合”为目的，比如单一职责原则、基于接口而非实现编程等。
 * 实际上，“高内聚、松耦合”是一个比较通用的设计思想，可以用来指导不同粒度代码的设计与开发，比如系统、模块、类，甚至是函数，
 * 也可以应用到不同的开发场景中，比如微服务、框架、组件、类库等。
 * 在这个设计思想中，“高内聚”用来指导类本身的设计，“松耦合”用来指导类与类之间依赖关系的设计。
 * 不过，这两者并非完全独立不相干。高内聚有助于松耦合，松耦合又需要高内聚的支持
 *
 * 所谓高内聚，就是指相近的功能应该放到同一个类中，不相近的功能不要放到同一个类中。
 * 相近的功能往往会被同时修改，放到同一个类中，修改会比较集中，代码容易维护。
 * 实际上，我们前面讲过的单一职责原则是实现代码高内聚非常有效的设计原则
 *
 * 所谓松耦合是说，在代码中，类与类之间的依赖关系简单清晰。
 * 即使两个类有依赖关系，一个类的代码改动不会或者很少导致依赖类的代码改动。
 * 实际上，我们前面讲的依赖注入、接口隔离、基于接口而非实现编程，以及今天讲的迪米特法则，都是为了实现代码的松耦合
 *
 *
 * Law Of Demeter:
 * Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.
 * Or: Each unit should only talk to its friends; don't talk to strangers.
 *
 * 每个模块只应该了解那些与它关系密切的模块的有限知识。
 * 或者说，每个模块只和自己的朋友“说话”，不和陌生人“说话”。
 *
 * - 不该有直接依赖关系的类之间，不要有依赖；
 * - 有依赖关系的类之间，尽量只依赖必要的接口（也就是定义中的“有限知识”）
 *
 */
public class Memo {
}