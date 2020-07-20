package inaction.ratelimiter.ver1.rule;

/**
 * 有了 RuleConfig 来存储限流规则，为什么还要 RateLimitRule 类呢？
 * 这是因为，限流过程中会频繁地查询接口对应的限流规则，为了尽可能地提高查询速度，我们需要将限流规则组织成一种支持按照 URL 快速查询的数据结构。
 * 考虑到 URL 的重复度比较高，且需要按照前缀来匹配，我们这里选择使用 Trie 树这种数据结构
 */
public class RateLimitRule {

    public RateLimitRule(RuleConfig ruleConfig) {
        //...
    }

    public ApiLimit getLimit(String appId, String api) {
        //...
        return null;
    }
}
