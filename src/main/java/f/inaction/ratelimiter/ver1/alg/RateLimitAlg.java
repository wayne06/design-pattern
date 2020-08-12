package f.inaction.ratelimiter.ver1.alg;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限流算法实现类。
 * 它实现了最简单的固定时间窗口限流算法。每个接口都要在内存中对应一个 RateLimitAlg 对象，记录在当前时间窗口内已经被访问的次数
 *
 * 固定时间窗口限流算法最简单。
 * 只需要选定一个起始时间起点，之后每来一个接口请求，我们都给计数器（记录当前时间窗口内的访问次数）加一，
 * 如果在当前时间窗口内，根据限流规则（比如每秒钟最大允许 100 次接口请求），累加访问次数超过限流值（比如 100 次），就触发限流熔断，拒绝接口请求。
 * 当进入下一个时间窗口之后，计数器清零重新计数
 *
 * 这种算法的限流策略过于粗略，无法应对两个时间窗口临界时间内的突发流量。
 * 假设我们限流规则为每秒钟不超过 100 次接口请求。
 * 第一个 1 秒时间窗口内，100 次接口请求都集中在最后的 10 毫秒内，在第二个 1 秒时间窗口内，100 次接口请求都集中在最开始的 10 毫秒内。
 * 虽然两个时间窗口内流量都符合限流要求 (小于等于 100 个接口请求)，但在两个时间窗口临界的 20 毫秒内集中有 200 次接口请求，
 * 固定时间窗口限流算法没法对这种情况进行限流，集中在这 20 毫秒内的 200 次请求有可能会压垮系统。
 *
 * 尽管固定时间窗口限流算法没法做到让流量很平滑，但大部分情况下，它已经够用了。
 * 默认情况下，框架使用固定时间窗口限流算法做限流。不过，考虑到框架的扩展性，我们需要预先做好设计，预留好扩展点，方便今后扩展其他限流算法。
 * 除此之外，为了提高框架的易用性、灵活性，我们最好将其他几种常用的限流算法，也在框架中实现出来，供框架用户根据自己业务场景自由选择。
 */
public class RateLimitAlg {

    private static final long          TRY_LOCK_TIMEOUT = 200L;
    private              Stopwatch     stopwatch;
    private              AtomicInteger currentCount = new AtomicInteger(0);
    private final int limit;
    private Lock lock = new ReentrantLock();

    public RateLimitAlg(int limit) {
        this(Stopwatch.createStarted(), limit);
    }

    @VisibleForTesting
    protected RateLimitAlg(Stopwatch stopwatch, int limit) {
        this.stopwatch = stopwatch;
        this.limit = limit;
    }

    public boolean tryAcquire() throws Exception {
        int updatedCount = currentCount.incrementAndGet();
        if (updatedCount <= limit) {
            return true;
        }

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    updatedCount = currentCount.incrementAndGet();
                    return updatedCount <= limit;
                } finally {
                    lock.unlock();
                }
            } else {
                throw new Exception("tryAcquire() wait lock too long : " + TRY_LOCK_TIMEOUT + " ms");
            }
        } catch (InterruptedException e) {
            throw new Exception("tryAcquire() wait lock too long : " + TRY_LOCK_TIMEOUT + " ms");
        } catch (Exception e) {
            throw new Exception("tryAcquire() is interrupted by lock-time-out.", e);
        }
    }
}
