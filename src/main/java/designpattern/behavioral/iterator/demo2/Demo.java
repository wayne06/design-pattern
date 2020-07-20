package designpattern.behavioral.iterator.demo2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * for 循环遍历方式比起迭代器遍历方式，代码看起来更加简洁。那我们为什么还要用迭代器来遍历容器呢？为什么还要给容器设计对应的迭代器呢？原因有以下三个：
 *
 * 首先，
 * 对于类似数组和链表这样的数据结构，遍历方式比较简单，直接使用 for 循环来遍历就足够了。但是，对于复杂的数据结构（比如树、图）来说，有各种复杂的遍历方式。
 * 比如，树有前中后序、按层遍历，图有深度优先、广度优先遍历等等。如果由客户端代码来实现这些遍历算法，势必增加开发成本，而且容易写错。
 * 如果将这部分遍历的逻辑写到容器类中，也会导致容器类代码的复杂性。
 * 前面也多次提到，应对复杂性的方法就是拆分。我们可以将遍历操作拆分到迭代器类中。
 * 比如，针对图的遍历，我们就可以定义 DFSIterator、BFSIterator 两个迭代器类，让它们分别来实现深度优先遍历和广度优先遍历。
 *
 * 其次，
 * 将游标指向的当前位置等信息，存储在迭代器类中，每个迭代器独享游标信息。这样，我们就可以创建多个不同的迭代器，同时对同一个容器进行遍历而互不影响。
 *
 * 最后，
 * 容器和迭代器都提供了抽象的接口，方便我们在开发的时候，基于接口而非具体的实现编程。
 * 当需要切换新的遍历算法的时候，（如从前往后遍历链表切换成从后往前遍历链表，客户端代码只需要将迭代器类从 LinkedIterator 切换为 ReversedLinkedIterator 即可），其他代码都不需要修改。
 * 除此之外，添加新的遍历算法，我们只需要扩展新的迭代器类，也更符合开闭原则。
 *
 */
public class Demo {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("abc");
        names.add("def");
        names.add("ghi");

        //// 第一种遍历方式：for循环
        //for (int i = 0; i < names.size(); i++) {
        //    System.out.println(names.get(i) + ",");
        //}
        //
        //// 第二种遍历方式：foreach循环（只是一个语法糖，底层是基于地带器来实现的）
        //for (String name : names) {
        //    System.out.println(name + ",");
        //}

        // 第三种遍历方式：迭代器（Java中的迭代器接口中，next()既移动游标又返回数据}）
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + ",");
        }
    }

}
