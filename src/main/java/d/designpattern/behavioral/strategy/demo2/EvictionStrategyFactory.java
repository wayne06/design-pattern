package d.designpattern.behavioral.strategy.demo2;

import java.util.HashMap;
import java.util.Map;

public class EvictionStrategyFactory {

    private static final Map<String, EvictionStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put("LRU", new LruEvictionStrategy());
        STRATEGY_MAP.put("FIFO", new FifoEvictionStrategy());
        STRATEGY_MAP.put("LFU", new LfuEvictionStrategy());
    }

    public static EvictionStrategy getEvictionStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type can not be empty");
        }
        return STRATEGY_MAP.get(type);
    }
}
