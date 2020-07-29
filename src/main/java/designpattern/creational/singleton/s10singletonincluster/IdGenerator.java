package designpattern.creational.singleton.s10singletonincluster;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private AtomicLong id = new AtomicLong(0);

    private static IdGenerator instance;

    private static SharedObjectsStorage storage =
            new FileSharedObjectStorage("/user/xxx/xx");

    private static DistributedLock lock = new DistributedLock();

    private IdGenerator() {
    }

    public synchronized static IdGenerator getInstance() {
        if (instance == null) {
            lock.lock();
            instance = storage.load(IdGenerator.class);
        }
        return instance;
    }

    public synchronized void freeInstance() {
        storage.save(this, IdGenerator.class);
        instance = null;
        lock.unlock();
    }

    public long getId() {
        return id.getAndIncrement();
    }
}
