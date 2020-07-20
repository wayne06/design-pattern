package designpattern.behavioral.strategy.demo7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 通过策略模式将策略的定义、创建、使用解耦，让每一部分都不至于太复杂。
 * 不过，Sorter 类中的 sortFile() 函数还是有一堆 if-else 逻辑。这里的 if-else 逻辑分支不多、也不复杂，这样写完全没问题。
 * 但如果特别想将 if-else 分支判断移除掉，那也是有办法的：基于查表法来解决的，其中的“algs”就是“表”
 *
 * 把可变的部分隔离到了策略工厂类和 Sorter 类中的静态代码段中。
 * 当要添加一个新的排序算法时，我们只需要修改策略工厂类和 Sort 类中的静态代码段，其他代码都不需要修改，这样就将代码改动最小化、集中化了。
 *
 * 对于 Java 语言来说，我们可以通过反射来避免对策略工厂类的修改。
 * 具体是这么做的：我们通过一个配置文件或者自定义的 annotation 来标注都有哪些策略类；
 * 策略工厂类读取配置文件或者搜索被 annotation 标注的策略类，然后通过反射动态地加载这些策略类、创建策略对象；
 * 当我们新添加一个策略的时候，只需要将这个新添加的策略类添加到配置文件或者用 annotation 标注即可。
 * 对于 Sorter 来说，可以通过将文件大小区间和算法之间的对应关系放到配置文件中。当添加新的排序算法时，只需要改动配置文件即可，不需要改动代码。
 *
 * @author wayne
 *
 */
public class Sorter {

    private static final long GB = 1000 * 1000 * 1000;

    private static final List<AlgRange> RANGES = new ArrayList<>();

    static {
        RANGES.add(new AlgRange(0, 6 * GB, SortAlgFactory.getAlg("QuickSort")));
        RANGES.add(new AlgRange(6, 10 * GB, SortAlgFactory.getAlg("ExternalSort")));
        RANGES.add(new AlgRange(10, 100 * GB, SortAlgFactory.getAlg("ConcurrentExternalSort")));
        RANGES.add(new AlgRange(100, Long.MAX_VALUE, SortAlgFactory.getAlg("MapReduceSort")));
    }

    public void sortFile(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg = null;
        for (AlgRange algRange : RANGES) {
            if (algRange.inRange(fileSize)) {
                sortAlg = algRange.getAlg();
                break;
            }
        }
        sortAlg.sort(filePath);
    }

    private static class AlgRange {

        private long start;
        private long end;
        private ISortAlg alg;

        public AlgRange(long start, long end, ISortAlg alg) {
            this.start = start;
            this.end = end;
            this.alg = alg;
        }

        public ISortAlg getAlg() {
            return alg;
        }

        public boolean inRange(long size) {
            return size >= start && size < end;
        }
    }

}
