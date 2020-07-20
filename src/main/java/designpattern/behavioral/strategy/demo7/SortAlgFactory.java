package designpattern.behavioral.strategy.demo7;

import java.util.HashMap;
import java.util.Map;

public class SortAlgFactory {

    private static final Map<String, ISortAlg> ALG_MAP = new HashMap<>();

    static {
        ALG_MAP.put("QuickSort", new QuickSort());
        ALG_MAP.put("ExternalSort", new ExternalSort());
        ALG_MAP.put("ConcurrentExternalSort", new ConcurrentExternalSort());
        ALG_MAP.put("MapReduceSort", new MapReduceSort());
    }

    public static ISortAlg getAlg(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return ALG_MAP.get(type);
    }

}
