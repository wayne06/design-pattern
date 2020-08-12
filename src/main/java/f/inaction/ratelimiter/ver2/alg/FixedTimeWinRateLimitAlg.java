package f.inaction.ratelimiter.ver2.alg;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FixedTimeWinRateLimitAlg implements RateLimitAlg {

    private static final long          TRY_LOCK_TIMEOUT = 200L;
    private              Stopwatch     stopwatch;
    private              AtomicInteger currentCount     = new AtomicInteger(0);
    private final        int           limit;
    private              Lock          lock             = new ReentrantLock();

    public FixedTimeWinRateLimitAlg(int limit) {
        this(Stopwatch.createStarted(), limit);
    }

    @VisibleForTesting
    protected FixedTimeWinRateLimitAlg(Stopwatch stopwatch, int limit) {
        this.stopwatch = stopwatch;
        this.limit = limit;
    }

    @Override
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
