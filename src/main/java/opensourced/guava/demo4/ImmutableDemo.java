package opensourced.guava.demo4;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Google Guava 提供的不变集合类属于普通不变模式，也就是说，集合中的对象不会增删，但是对象的成员变量（或叫属性值）是可以改变的。
 *
 * 实际上，Java JDK 也提供了不变集合类（UnmodifiableCollection、UnmodifiableList、UnmodifiableSet、UnmodifiableMap…）。
 * 那它跟 Google Guava 提供的不变集合类的区别在哪里呢？代码如下所示：
 */
public class ImmutableDemo {

    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>();
        originalList.add("a");
        originalList.add("b");
        originalList.add("c");

        List<String> jdkUnmodifiableList = Collections.unmodifiableList(originalList);
        List<String> guavaImmutableList = ImmutableList.copyOf(originalList);

        //jdkUnmodifiableList.add("d");  // UnsupportedOperationException
        //guavaImmutableList.add("d");  // UnsupportedOperationException
        originalList.add("d");

        print(originalList);  // a b c d
        print(jdkUnmodifiableList);  // a b c d
        print(guavaImmutableList);  // a b c
    }

    private static void print(List<String> list) {
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

}
