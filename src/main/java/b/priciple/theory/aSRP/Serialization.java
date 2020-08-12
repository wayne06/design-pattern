package b.priciple.theory.aSRP;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Map;

/**
 * 类的职责是否设计的越单一越好？
 *
 * Serialization 类实现了一个简单协议的序列化和反序列功能
 * 如果想让类的职责更加单一，把 Serialization 类拆分成一个只负责序列化的 Serializer 类和另一个只负责反序列化的 Deserializer 类
 *
 * 虽然经过拆分之后，Serializer 类和 Deserializer 类的职责更加单一了，但也随之带来了新的问题。
 * 如果我们修改了协议的格式，数据标识从“UEUEUE”改为“DFDFDF”，或者序列化方式从 JSON 改为了 XML，
 * 那 Serializer 类和 Deserializer 类都需要做相应的修改，代码的内聚性显然没有原来 Serialization 高了。
 *
 * 而且，如果我们仅仅对 Serializer 类做了协议修改，而忘记了修改 Deserializer 类的代码，那就会导致序列化、反序列化不匹配，
 * 程序运行出错，也就是说，拆分之后，代码的可维护性变差了。
 *
 * 实际上，不管是应用设计原则还是设计模式，最终的目的还是提高代码的可读性、可扩展性、复用性、可维护性等。
 * 我们在考虑应用某一个设计原则是否合理的时候，也可以以此作为最终的考量标准。
 *
 */
public class Serialization {

    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Serialization() {
        this.gson = new Gson();
    }

    public String serialize(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(IDENTIFIER_STRING);
        textBuilder.append(gson.toJson(object));
        return textBuilder.toString();
    }

    public Map<String, String> deserialize(String text) {
        if (!text.startsWith(IDENTIFIER_STRING)) {
            return Collections.emptyMap();
        }
        String gsonStr = text.substring(IDENTIFIER_STRING.length());
        return gson.fromJson(gsonStr, Map.class);
    }

    public static void main(String[] args) {
        String text = "UEUEUE;{a:11,b:12}";
        Map<String, String> map = new Serialization().deserialize(text);
        System.out.println(map);
    }
}
