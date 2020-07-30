package oop.theory.gCompositionBeforeInherit.demo1;

/**
 * 鸵鸟类属于鸟类，但不会飞；重写 fly() 使其抛出异常
 *
 * 这种设计思路虽然可以解决问题，但不够优美。因为除了鸵鸟之外，不会飞的鸟还有很多，比如企鹅。
 * 对于这些不会飞的鸟来说，我们都需要重写 fly() 方法，抛出异常。
 * 这样的设计，一方面，徒增了编码的工作量；
 * 另一方面，也违背了我们之后要讲的最小知识原则（Least Knowledge Principle，也叫最少知识原则或者迪米特法则），
 * 暴露不该暴露的接口给外部，增加了类使用过程中被误用的概率
 *
 * 继承最大的问题就在于：继承层次过深、继承关系过于复杂会影响到代码的可读性和可维护性
 *
 * 使用 组合 代替 继承，见 demo2
 *
 */
public class Ostrich extends AbstractBird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }
}
