package priciple.theory.hLOD.serialize.demo3;

/**
 * 尽管我们还是要往 App1 的构造函数中，传入包含序列化和反序列化的 Serialization 实现类，
 * 但是，我们依赖的 Serializable 接口只包含序列化操作，App1 无法使用 Serialization 类中的反序列化接口，对反序列化操作无感知，
 * 这也就符合了迪米特法则后半部分所说的“依赖有限接口”的要求。
 *
 * 实际上，上面的的代码实现思路，也体现了“基于接口而非实现编程”的设计原则，
 * 结合迪米特法则，我们可以总结出一条新的设计原则，那就是“基于最小接口而非最大实现编程”。
 *
 */
public class App1 {

    private Serialiable serializer;

    public App1(Serialiable serializer) {
        this.serializer = serializer;
    }
}
