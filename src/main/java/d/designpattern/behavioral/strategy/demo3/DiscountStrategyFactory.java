package d.designpattern.behavioral.strategy.demo3;

/**
 * 策略的创建
 */
public class DiscountStrategyFactory {

    // v1.0 策略类是无状态的，不包含成员变量，只是纯粹的算法实现，这样的策略对象是可以被共享使用的，
    // 不需要在每次调用 getStrategy() 的时候，都创建一个新的策略对象

    //private static final Map<OrderType, DiscountStrategy> STRATEGY_MAP = new HashMap<>();
    //static {
    //    STRATEGY_MAP.put(OrderType.NORMAL, new NormalDiscountStrategy());
    //    STRATEGY_MAP.put(OrderType.GROUPON, new GrouponDiscountStrategy());
    //    STRATEGY_MAP.put(OrderType.PROMOTION, new PromotionDiscountStrategy());
    //}
    //public static DiscountStrategy getDiscountStrategy(OrderType type) {
    //    return STRATEGY_MAP.get(type);
    //}


    // v2.0 如果业务场景需要每次都创建不同的策略对象，我们就要用另外一种工厂类的实现方式了.
    // 这种实现方式相当于把原来的 if-else 分支逻辑，从 OrderService 类中转移到了工厂类中，实际上并没有真正将它移除

    public static DiscountStrategy getDiscountStrategy(OrderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type should not be null.");
        }
        if (type.equals(OrderType.NORMAL)) {
            return new NormalDiscountStrategy();
        } else if (type.equals(OrderType.GROUPON)) {
            return new GrouponDiscountStrategy();
        } else if (type.equals(OrderType.PROMOTION)) {
            return new PromotionDiscountStrategy();
        }
        return null;
    }

}
