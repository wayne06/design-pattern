package f.inaction.ratelimiter.ver2.alg;

public interface RateLimitAlg {

    boolean tryAcquire() throws Exception;

}
