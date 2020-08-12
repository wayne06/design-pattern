package f.inaction.ratelimiter.ver2;

import f.inaction.ratelimiter.ver2.alg.FixedTimeWinRateLimitAlg;
import f.inaction.ratelimiter.ver2.alg.RateLimitAlg;
import f.inaction.ratelimiter.ver2.rule.datasource.FileRuleConfigSource;
import f.inaction.ratelimiter.ver2.rule.datasource.RuleConfigSource;
import f.inaction.ratelimiter.ver2.rule.ApiLimit;
import f.inaction.ratelimiter.ver2.rule.RateLimitRule;
import f.inaction.ratelimiter.ver2.rule.RuleConfig;
import f.inaction.ratelimiter.ver2.rule.TrieRateLimitRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 重构最小原型代码
 *
 * RuleConfig、ApiLimit、RateLimitRule 属于“限流规则”模块，负责限流规则的构建和查询。
 * RateLimitAlg 属于“限流算法”模块，提供了基于内存的单机固定时间窗口限流算法。
 * RateLimiter 类属于“集成使用”模块，作为最顶层类，组装其他类，提供执行入口（也就是调用入口）。
 *
 * 不过，RateLimiter 类作为执行入口，我们希望它只负责组装工作，而不应该包含具体的业务逻辑，
 * 所以，RateLimiter 类中，从配置文件中读取限流规则这块逻辑，应该拆分出来设计成独立的类
 *
 * 不易扩展的最大原因是，没有遵循基于接口而非实现的编程思想，没有接口抽象意识。
 * 比如，RateLimitAlg 类只是实现了固定时间窗口限流算法，也没有提炼出更加抽象的算法接口。如果我们要替换其他限流算法，就要改动比较多的代码。
 * 其他类的设计也有同样的问题，比如 RateLimitRule。
 *
 * 除此之外，在 RateLimiter 类中，配置文件的名称、路径，是硬编码在代码中的。
 * 尽管我们说约定优于配置，但也要兼顾灵活性，能够让用户在需要的时候，自定义配置文件名称、路径。
 * 而且，配置文件的格式只支持 Yaml，之后扩展其他格式，需要对这部分代码做很大的改动。
 *
 * 重构最小原型代码：
 *
 * 根据刚刚对 MVP 代码的剖析，我们发现，它的可读性没有太大问题，问题主要在于可扩展性。
 * 主要的修改点有两个：
 * 一个是将 RateLimiter 中的规则配置文件的读取解析逻辑拆出来，设计成独立的类；
 * 另一个是参照基于接口而非实现编程思想，对于 RateLimitRule、RateLimitAlg 类提炼抽象接口。
 *
 * RateLimiter 类代码的改动集中在构造函数中，通过调用 RuleConfigSource 来实现了限流规则配置文件的加载。
 *
 * 各个 Parser 和 RuleConfigSource 类的设计有点类似策略模式，
 * 如果要添加新的格式的解析，只需要实现对应的 Parser 类，并且添加到 FileRuleConfig 类的 PARSER_MAP 中就可以了。
 *
 */
public class RateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiter.class);

    private ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimitRule                           rule;

    public RateLimiter() {
        //改动主要在这里：调用RuleConfigSource类来实现配置加载
        RuleConfigSource configSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = configSource.load();
        this.rule = new TrieRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) throws Exception {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null) {
            return true;
        }

        String counterKey = appId + " : " + apiLimit.getApi();
        RateLimitAlg rateLimitCounter = counters.get(counterKey);
        if (rateLimitCounter == null) {
            RateLimitAlg newRateLimitCounter = new FixedTimeWinRateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
            if (rateLimitCounter == null) {
                rateLimitCounter = newRateLimitCounter;
            }
        }
        return rateLimitCounter.tryAcquire();
    }
}
