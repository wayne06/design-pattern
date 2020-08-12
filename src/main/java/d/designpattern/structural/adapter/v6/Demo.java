package d.designpattern.structural.adapter.v6;

import java.util.Arrays;
import java.util.List;

/**
 * 适配器模式应用场景五：适配不同格式的数据
 *
 * 如1：把从不同征信系统拉取的不同格式的征信数据，统一为相同的格式，以方便存储和使用
 *
 * 如2：Java 中的 Arrays.asList() 也可以看作一种数据适配器，将数组类型的数据转化为集合容器类型
 */
public class Demo {

    public static void main(String[] args) {
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
        System.out.println(stooges);
    }

}
