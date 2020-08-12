package b.priciple.theory.dISP.stat;

import java.util.Collection;

/**
 * 把“接口”理解为单个 API 接口或函数：函数的设计要功能单一，不要将多个不同的功能逻辑在一个函数中实现
 *
 * 实际上，判定功能是否单一，除了很强的主观性，还需要结合具体的场景
 *
 * 单一职责原则针对的是模块、类、接口的设计。而接口隔离原则相对于单一职责原则，一方面它更侧重于接口的设计，另一方面它的思考的角度不同。
 * 它提供了一种判断接口是否职责单一的标准：通过调用者如何使用接口来间接地判定。
 * 如果调用者只使用部分接口或接口的部分功能，那接口的设计就不够职责单一
 *
 */
public class Statistics {

    private Long max;
    private Long min;
    private Long average;
    private Long sum;
    private Long percentile99;
    private Long percentile999;

    //...

    public Statistics count(Collection<Long> dataSet) {
        Statistics statistics = new Statistics();
        //...
        return statistics;
    }


    public Long max(Collection<Long> dataSet) {
        //...
        return 0L;
    }

    public Long min(Collection<Long> dataSet) {
        //
        return 0L;
    }

    public Long average(Collection<Long> dataSet) {
        //...
        return 0L;
    }

    // ...省略其他统计函数...
}
