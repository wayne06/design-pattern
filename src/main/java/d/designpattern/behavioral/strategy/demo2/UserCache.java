package d.designpattern.behavioral.strategy.demo2;

import java.util.HashMap;
import java.util.Map;

public class UserCache {

    private Map<String, User> cacheData = new HashMap<>();
    private EvictionStrategy eviction;

    public UserCache(EvictionStrategy eviction) {
        this.eviction = eviction;
    }

    //...
}
