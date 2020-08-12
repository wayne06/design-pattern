package f.inaction.ratelimiter.ver2.rule;

public interface RateLimitRule {

    ApiLimit getLimit(String appId, String api);

}
