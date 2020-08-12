package a.oop.theory.gCompositionBeforeInherit.demo2;

/**
 * 利用组合（composition）、接口、委托（delegation）三个技术手段，一块儿来解决刚刚继承存在的问题
 *
 * 不过，接口只声明方法，不定义实现。
 * 也就是说，每个会下蛋的鸟都要实现一遍 layEgg() 方法，并且实现逻辑是一样的，这就会导致代码重复的问题。解决方案见 demo3
 *
 */
public class Ostrich implements Tweetable, EggLayable {
    @Override
    public void layEgg() {

    }

    @Override
    public void tweet() {

    }
}
