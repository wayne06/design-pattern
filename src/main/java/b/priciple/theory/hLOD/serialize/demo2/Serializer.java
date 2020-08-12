package b.priciple.theory.hLOD.serialize.demo2;

/**
 * 尽管拆分之后的代码更能满足迪米特法则，但却违背了高内聚的设计思想。
 * 高内聚要求相近的功能要放到同一个类中，这样可以方便功能修改的时候，修改的地方不至于过于分散。
 *
 * 如果我们修改了序列化的实现方式，比如从 JSON 换成了 XML，那反序列化的实现逻辑也需要一并修改。
 * 在未拆分的情况下，我们只需要修改一个类即可。在拆分之后，我们需要修改两个类。
 * 显然，这种设计思路的代码改动范围变大了。
 *
 * 如果我们既不想违背高内聚的设计思想，也不想违背迪米特法则，那我们该如何解决这个问题呢？
 * 实际上，通过引入两个接口就能轻松解决这个问题
 *
 */
public class Serializer {

    public String serialize(Object object) {
        String serializedResult = "";
        //...
        return serializedResult;
    }

}
