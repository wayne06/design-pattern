package a.oop.theory.gCompositionBeforeInherit.demo3;

/**
 * 可以针对三个接口再定义三个实现类，它们分别是：
 * 实现了 fly() 方法的 FlyAbility 类、实现了 tweet() 方法的 TweetAbility 类、实现了 layEgg() 方法的 EggLayAbility 类。
 *
 * 然后，通过组合和委托技术来消除代码重复
 *
 * 继承主要有三个作用：表示 is-a 关系，支持多态特性，代码复用。而这三个作用都可以通过其他技术手段来达成。
 * 比如 is-a 关系，我们可以通过组合和接口的 has-a 关系来替代；多态特性我们可以利用接口来实现；代码复用我们可以通过组合和委托来实现。
 * 所以，从理论上讲，通过组合、接口、委托三个技术手段，我们完全可以替换掉继承，
 * 在项目中不用或者少用继承关系，特别是一些复杂的继承关系。
 *
 */
public class Ostrich implements Tweetable, EggLayable {

    /**
     * 组合
     */
    private TweetAbility tweetAbility = new TweetAbility();

    /**
     * 组合
     */
    private EggLayAbility eggLayAbility = new EggLayAbility();


    /**
     * 委托
     */
    @Override
    public void layEgg() {
        eggLayAbility.layEgg();
    }

    /**
     * 委托
     */
    @Override
    public void tweet() {
        tweetAbility.tweet();
    }
}
