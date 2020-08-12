package b.priciple.theory.cLSP;

/**
 * Liskov Substitution Principle:
 *
 * If S is a subtype of T, then objects of type T may be replaced with objects of type S, without breaking the program.
 *
 * Functions that use pointers of references to base classes must be able to use objects of derived classes without knowing it.
 *
 * 子类对象（object of subtype/derived class）能够替换程序（program）中父类对象（object of base/parent class）出现的任何地方，
 * 并且保证原来程序的逻辑行为（behavior）不变及正确性不被破坏
 *
 *
 * 那些代码明显违背了 LSP？
 *
 * 里式替换原则还有另外一个更加能落地、更有指导意义的描述，那就是“Design By Contract”，中文翻译就是“按照协议来设计”。
 * 即，子类在设计的时候，要遵守父类的行为约定（或者叫协议）。
 * 父类定义了函数的行为约定，那子类可以改变函数的内部实现逻辑，但不能改变函数原有的行为约定。
 * 这里的行为约定包括：函数声明要实现的功能；对输入、输出、异常的约定；甚至包括注释中所罗列的任何特殊说明。
 * 实际上，定义中父类和子类之间的关系，也可以替换成接口和实现类之间的关系
 *
 * 1. 子类违背父类声明要实现的功能
 * 2. 子类违背父类对输入、输出、异常的约定
 * 3. 子类违背父类注释中所罗列的任何特殊说明
 *
 * 除此之外，判断子类的设计实现是否违背里式替换原则，还有一个小窍门，那就是拿父类的单元测试去验证子类的代码。
 * 如果某些单元测试运行失败，就有可能说明，子类的设计实现没有完全地遵守父类的约定，子类有可能违背了里式替换原则
 *
 */
public class Memo {
}
