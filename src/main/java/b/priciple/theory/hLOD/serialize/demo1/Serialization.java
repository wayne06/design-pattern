package b.priciple.theory.hLOD.serialize.demo1;

/**
 * 假设在我们的项目中，有些类只用到了序列化操作，而另一些类只用到反序列化操作。
 * 那基于迪米特法则后半部分“有依赖关系的类之间，尽量只依赖必要的接口”，只用到序列化操作的那部分类不应该依赖反序列化接口。
 * 同理，只用到反序列化操作的那部分类不应该依赖序列化接口。
 * 根据这个思路，应该将 Serialization 类拆分为两个更小粒度的类，一个只负责序列化，一个只负责反序列化。
 * 拆分之后，使用序列化操作的类只需要依赖 Serializer 类，使用反序列化操作的类只需要依赖 Deserializer 类
 *
 */
public class Serialization {

    public String serialize(Object object) {
        String serializedResult = "";
        //...
        return serializedResult;
    }

    public Object deserialize(String string) {
        Object deserializedResult = null;
        //...
        return deserializedResult;
    }

}
