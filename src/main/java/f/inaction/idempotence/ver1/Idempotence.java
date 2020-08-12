package f.inaction.idempotence.ver1;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 最小原型代码存在的问题：
 *
 * 1. 代码可读性问题：
 * 有些函数的参数和返回值的格式和意义不够明确，需要注释补充解释一下。genId() 函数使用了缩写，全拼 generateId() 可能更好些！
 *
 * 2. 代码可扩展性问题：
 * 按照现在的代码实现方式，如果改变幂等号的存储方式和生成算法，代码修改起来会比较麻烦。
 * 除此之外，基于接口隔离原则，我们应该将 genId() 函数跟其他函数分离开来，放到两个类中。独立变化，隔离修改，更容易扩展！
 *
 * 3. 代码可测试性问题：
 * 解析 Redis Cluster 地址的代码逻辑较复杂，但因为放到了构造函数中，无法对它编写单元测试。
 *
 * 4. 代码灵活性问题：
 * 业务系统有可能希望幂等框架复用已经建立好的 jedisCluster，而不是单独给幂等框架创建一个 jedisCluster。
 *
 */
public class Idempotence {

    // comment-1: 如果要替换存储方式？
    private JedisCluster jedisCluster;

    // comment-2: 如果幂等框架要跟业务系统复用 jedisCluster 连接呢？
    // comment-3: 是否应该注释说明 redisClusterAddress 的格式，以及 config 是否可以传递进 null？
    public Idempotence(String redisClusterAddress, GenericObjectPoolConfig config) {
        // comment-4: 这段逻辑放到构造函数，不容易写单元测试
        String[] addressArray = redisClusterAddress.split(";");
        Set<HostAndPort> redisNodes = new HashSet<>();
        for (String address : addressArray) {
            String[] hostAndPort = address.split(";");
            redisNodes.add(new HostAndPort(hostAndPort[0], Integer.valueOf(hostAndPort[1])));
        }
        this.jedisCluster = new JedisCluster(redisNodes, config);
    }

    // comment-5: generateId() 是否比缩写要好
    // comment-6: 根据接口隔离原则，这个方法跟其他方法的使用场景完全不同，这个方法主要用在调用方，是否应该分别放到两个类中
    public String genId() {
        return UUID.randomUUID().toString();
    }

    // comment-7: 返回值的意义是否应该注释说明
    public boolean saveIfAbsent(String idempotenceId) {
        Long success = jedisCluster.setnx(idempotenceId, "1");
        return success == 1;
    }

    public void delete(String idempotenceId) {
        jedisCluster.del(idempotenceId);
    }

}
