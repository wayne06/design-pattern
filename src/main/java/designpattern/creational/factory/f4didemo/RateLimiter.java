package designpattern.creational.factory.f4didemo;

public class RateLimiter {

    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public void test() {
        this.redisCounter.test();
    }
}
