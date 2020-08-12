package d.designpattern.behavioral.strategy.demo1;

/**
 * 如果策略类是有状态的，根据业务场景的需要，我们希望每次从工厂方法中，获得的都是新创建的策略对象，而不是缓存好可共享的策略对象，
 * 那我们就需要按照如下方式来实现策略工厂类
 */
public class StrategyFactory_State {

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }

        if ("A".equals(type)) {
            return new StrategyA();
        } else if ("B".equals(type)) {
            return new StrategyB();
        }
        return null;
    }

}
