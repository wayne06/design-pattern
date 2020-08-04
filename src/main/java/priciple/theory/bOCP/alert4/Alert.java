package priciple.theory.bOCP.alert4;

import priciple.theory.bOCP.alert4.handler.AlertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wayne
 *
 * api接口监控告警：
 *
 * 重构：
 * 1. 将 check() 函数的多个入参封装成 ApiStatInfo 类；
 * 2. 引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中
 *
 * 重构后的改动：一共改动四处
 * 第一处改动是：在 ApiStatInfo 类中添加新的属性 timeoutCount。
 * 第二处改动是：添加新的 TimeoutAlertHander 类。
 * 第三处改动是：在 ApplicationContext 类的 initializeBeans() 方法中，往 alert 对象中注册新的 timeoutAlertHandler。
 * 第四处改动是：在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值。
 *
 * 重构之后的代码更加灵活和易扩展。
 * 如果我们要想添加新的告警逻辑，只需要基于扩展的方式创建新的 handler 类即可，不需要改动原来的 check() 函数的逻辑。
 * 而且，我们只需要为新的 handler 类添加单元测试，老的单元测试都不会失败，也不用修改
 *
 *
 * 修改代码就意味着违背开闭原则吗？
 *
 * 同样一个代码改动，在粗代码粒度下，被认定为“修改”，在细代码粒度下，又可以被认定为“扩展”。
 * 比如，改动一，添加属性和方法相当于修改类，在类这个层面，这个代码改动可以被认定为“修改”；
 * 但这个代码改动并没有修改已有的属性和方法，在方法（及其属性）这一层面，它又可以被认定为“扩展”。
 * 实际上，回到这条原则的设计初衷：只要它没有破坏原有的代码的正常运行，没有破坏原有的单元测试，就可以说这是一个合格的代码改动。
 *
 * 改动三和改动四：在 ApplicationContext 类的 initializeBeans() 方法中，往 alert 对象中注册新的 timeoutAlertHandler；
 * 在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值。
 * 这两处改动都是在方法内部进行的，不管从哪个层面（模块、类、方法）来讲，都不能算是“扩展”，而是地地道道的“修改”。
 * 不过，有些修改是在所难免的，是可以被接受的。为什么这么说呢？
 * 在重构之后的 Alert 代码中，核心逻辑集中在 Alert 类及其各个 handler 中，当我们在添加新的告警逻辑的时候，Alert 类完全不需要修改，
 * 而只需要扩展一个新 handler 类。如果我们把 Alert 类及各个 handler 类合起来看作一个“模块”，那模块本身在添加新的功能的时候，
 * 完全满足开闭原则。
 *
 * 而且，我们要认识到，添加一个新功能，不可能任何模块、类、方法的代码都不“修改”，这个是做不到的。
 * 类需要创建、组装、并且做一些初始化操作，才能构建成可运行的的程序，这部分代码的修改是在所难免的。
 * 我们要做的是尽量让修改操作更集中、更少、更上层，尽量让最核心、最复杂的那部分逻辑代码满足开闭原则。
 *
 */
public class Alert {

    private List<AlertHandler> alertHandlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}
