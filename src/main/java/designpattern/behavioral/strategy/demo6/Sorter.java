package designpattern.behavioral.strategy.demo6;

import java.io.File;

/**
 * 经过拆分之后，每个类的代码都不会太多，每个类的逻辑都不会太复杂，代码的可读性、可维护性提高了。
 * 除此之外，我们将排序算法设计成独立的类，跟具体的业务逻辑（代码中的 if-else 那部分逻辑）解耦，也让排序算法能够复用。
 * 这一步实际上就是策略模式的第一步，也就是将策略的定义分离出来。
 *
 * 每种排序类都是无状态的，我们没必要在每次使用的时候，都重新创建一个新的对象。
 * 所以，我们可以使用工厂模式对对象的创建进行封装
 */
public class Sorter {

    private static final long GB = 1000 * 1000 * 1000;

    public void sortFile(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg;
        if (fileSize < 6 * GB) {
            sortAlg = SortAlgFactory.getAlg("QuickSort");
        } else if (fileSize < 10 * GB) {
            sortAlg = SortAlgFactory.getAlg("ExternalSort");
        } else if (fileSize < 100 * GB) {
            sortAlg = SortAlgFactory.getAlg("ConcurrentExternalSort");
        } else {
            sortAlg = SortAlgFactory.getAlg("MapReduceSort");
        }
        sortAlg.sort(filePath);
    }

}
