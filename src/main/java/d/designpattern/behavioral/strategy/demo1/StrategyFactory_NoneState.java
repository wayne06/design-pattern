package d.designpattern.behavioral.strategy.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * 因为策略模式会包含一组策略，在使用它们的时候，一般会通过类型（type）来判断创建哪个策略来使用。
 * 为了封装创建逻辑，我们需要对客户端代码屏蔽创建细节。我们可以把根据 type 创建策略的逻辑抽离出来，放到工厂类中
 *
 * 如果策略类是无状态的，不包含成员变量，只是纯粹的算法实现，这样的策略对象是可以被共享使用的，不需要在每次调用 getStrategy() 的时候，都创建一个新的策略对象。
 * 针对这种情况，我们可以使用上面这种工厂类的实现方式，事先创建好每个策略对象，缓存到工厂类中，用的时候直接返回
 */
public class StrategyFactory_NoneState {

    private static final Map<String, Strategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put("A", new StrategyA());
        STRATEGY_MAP.put("B", new StrategyB());
    }

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return STRATEGY_MAP.get(type);
    }

}
