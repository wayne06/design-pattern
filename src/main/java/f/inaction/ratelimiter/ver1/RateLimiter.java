package f.inaction.ratelimiter.ver1;

import f.inaction.ratelimiter.ver1.alg.RateLimitAlg;
import f.inaction.ratelimiter.ver1.rule.ApiLimit;
import f.inaction.ratelimiter.ver1.rule.RateLimitRule;
import f.inaction.ratelimiter.ver1.rule.RuleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RateLimiter 类用来串联整个限流流程。
 *
 * 它先读取限流规则配置文件，映射为内存中的 Java 对象（RuleConfig），然后再将这个中间结构构建成一个支持快速查询的数据结构（RateLimitRule）。
 * 还提供了供用户直接使用的最顶层接口（limit() 接口）。
 */
public class RateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiter.class);

    // 为每个 api 在内存中存储限流计数器
    private ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimitRule                           rule;

    public RateLimiter() {
        // 将限流规则配置文件中的内容读取到 RuleConfig 中
        InputStream in = null;
        RuleConfig ruleConfig = null;
        try {
            in = this.getClass().getResourceAsStream("/ratelimiter-rule.yaml");
            if (in != null) {
                Yaml yaml = new Yaml();
                ruleConfig = yaml.loadAs(in, RuleConfig.class);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("close file error: {}", e);
                }
            }
        }
        // 将限流规则构建成支持快速查找的数据结构 RateLimitRule
        this.rule = new RateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) throws Exception {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null) {
            return true;
        }

        // 获取 api 对应在内存中的限流计数器 rateLimitCounter
        String counterKey = appId + " : " + apiLimit.getApi();
        RateLimitAlg rateLimitCounter = counters.get(counterKey);
        if (rateLimitCounter == null) {
            RateLimitAlg newRateLimitCounter = new RateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
            if (rateLimitCounter == null) {
                rateLimitCounter = newRateLimitCounter;
            }
        }
        // 判断是否限流
        return rateLimitCounter.tryAcquire();
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        try {
            rateLimiter.limit("app-1", "http://www.eudemon.com/v1/user/12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
