package inaction.idempotence.ver2;

/**
 * 1. 在代码可读性方面，
 *
 * 对构造函数、saveIfAbsense() 函数的参数和返回值做了注释，并且将 genId() 函数改为全拼 generateId()。
 *
 * 2. 在代码可扩展性方面，
 *
 * 2.1 按照基于接口而非实现的编程原则，将幂等号的读写独立出来，设计成 IdempotenceStorage 接口和 RedisClusterIdempotenceStorage 实现类。
 * RedisClusterIdempotenceStorage 实现了基于 Redis Cluster 的幂等号读写。
 * 如果我们需要替换新的幂等号读写方式，比如基于单个 Redis 而非 Redis Cluster，
 * 我们就可以再定义一个实现了 IdempotenceStorage 接口的实现类：RedisIdempotenceStorage。
 *
 * 2.2 按照接口隔离原则，我们将生成幂等号的代码抽离出来，放到 IdempotenceIdGenerator 类中。
 * 这样，调用方只需要依赖这个类的代码就可以了。幂等号生成算法的修改，跟幂等号存储逻辑的修改，两者完全独立，一个修改不会影响另外一个。
 *
 * 3. 在代码可测试性方面，
 *
 * 把原本放在构造函数中的逻辑抽离出来，放到了 parseHostAndPorts() 函数中。
 * 该函数本应是 Private 访问权限，但为了方便编写单元测试，把它设置为成 Protected 访问权限，并通过注解 @VisibleForTesting 做了标明。
 *
 * 4. 在代码灵活性方面，
 * 为了方便复用业务系统已经建立好的 jedisCluster，提供了一个新的构造函数，支持业务系统直接传递 jedisCluster 来创建 Idempotence 对象。
 *
 */
public class Idempotence {

    private IdempotenceStorage storage;

    public Idempotence(IdempotenceStorage storage) {
        this.storage = storage;
    }

    public boolean saveIfAbsent(String idempotenceId) {
        return storage.saveIfAbsent(idempotenceId);
    }

    public void delete(String idempotenceId) {
        storage.delete(idempotenceId);
    }
}
