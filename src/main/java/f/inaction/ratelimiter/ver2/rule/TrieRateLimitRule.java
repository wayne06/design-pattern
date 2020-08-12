package f.inaction.ratelimiter.ver2.rule;

public class TrieRateLimitRule implements RateLimitRule {

    public TrieRateLimitRule(RuleConfig ruleConfig) {
        //...
    }

    @Override
    public ApiLimit getLimit(String appId, String api) {
        //...
        return null;
    }
}
