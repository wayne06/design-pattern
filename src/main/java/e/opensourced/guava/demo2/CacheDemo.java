package e.opensourced.guava.demo2;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Builder 模式在 Guava 中的应用：
 *
 *
 * 为什么要由 Builder 类来创建 Cache 对象呢？
 *
 * 构建一个缓存，需要配置 n 多参数，比如过期时间、淘汰策略、最大缓存大小等等。相应地，Cache 类就会包含 n 多成员变量。
 * 我们需要在构造函数中，设置这些成员变量的值，但又不是所有的值都必须设置，设置哪些值由用户来决定。
 * 为了满足这个需求，就需要定义多个包含不同参数列表的构造函数。为了避免构造函数的参数列表过长、不同的构造函数过多，一般有两种解决方案。
 * 其中，一个解决方案是使用 Builder 模式；另一个方案是先通过无参构造函数创建对象，然后再通过 setXXX() 方法来逐一设置需要的设置的成员变量。
 *
 * 为什么 Guava 选择第一种而不是第二种解决方案呢？使用第二种解决方案是否也可以呢？答案是不行的
 *
 *
 * public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
 *   this.checkWeightWithWeigher();
 *   this.checkNonLoadingCache();
 *   return new LocalManualCache(this);
 * }
 *
 * private void checkNonLoadingCache() {
 *   Preconditions.checkState(this.refreshNanos == -1L, "refreshAfterWrite requires a LoadingCache");
 * }
 *
 * private void checkWeightWithWeigher() {
 *   if (this.weigher == null) {
 *     Preconditions.checkState(this.maximumWeight == -1L, "maximumWeight requires weigher");
 *   } else if (this.strictParsing) {
 *     Preconditions.checkState(this.maximumWeight != -1L, "weigher requires maximumWeight");
 *   } else if (this.maximumWeight == -1L) {
 *     logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
 *   }
 * }
 *
 * 必须使用 Builder 模式的主要原因是，在真正构造 Cache 对象的时候，必须做一些必要的参数校验，
 * 也就是 build() 函数中前两行代码要做的工作。如果采用无参默认构造函数加 setXXX() 方法的方案，这两个校验就无处安放了。
 * 而不经过校验，创建的 Cache 对象有可能是不合法、不可用的。
 *
 */
public class CacheDemo {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        cache.put("key1", "value1");
        String value = cache.getIfPresent("key1");
        System.out.println(value);
    }

}

