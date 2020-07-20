package designpattern.behavioral.strategy.demo3;

/**
 * v1.0：if-else的分支判断
 *
 * v2.0：策略模式的使用
 * 重构之后的代码就没有了 if-else 分支判断语句了。实际上，这得益于策略工厂类。
 * 在工厂类中，我们用 Map 来缓存策略，根据 type 直接从 Map 中获取对应的策略，从而避免 if-else 分支判断逻辑。
 * 使用状态模式来避免分支判断逻辑的时候，使用的是同样的套路。
 * 本质上都是借助“查表法”，根据 type 查表（代码中的 strategies 就是表）替代根据 type 分支判断
 *
 */
public class OrderService {

    public double discount(Order order) {

        // v1.0：if-else的分支判断
        //double discount = 0.0;
        //OrderType type = order.getType();
        //if (type.equals(OrderType.NORMAL)) {
        //    //...省略折扣计算算法代码
        //} else if (type.equals(OrderType.GROUPON)) {
        //    //...省略折扣计算算法代码
        //} else if (type.equals(OrderType.PROMOTION)) {
        //    //...省略折扣计算算法代码
        //}
        //return discount;


        // v2.0：策略模式的使用
        OrderType type = order.getType();
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(type);
        return discountStrategy.calDiscount(order);
    }

}
