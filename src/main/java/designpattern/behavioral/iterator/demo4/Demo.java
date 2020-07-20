package designpattern.behavioral.iterator.demo4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 如何实现一个支持“快照”功能的迭代器模式？
 *
 * 所谓“快照”，指为容器创建迭代器的时候，相当于给容器拍了一张快照（Snapshot）。之后即便我们增删容器中的元素，快照中的元素并不会做相应的改动。
 * 而迭代器遍历的对象是快照而非容器，这样就避免了在使用迭代器遍历的过程中，增删容器中的元素，导致的不可预期的结果或者报错
 *
 * 解决方案一：
 *
 * 在迭代器类中定义一个成员变量 snapshot 来存储快照。每当创建迭代器的时候，都拷贝一份容器中的元素到快照中，后续的遍历操作都基于这个迭代器自己持有的快照来进行
 *
 * 这个解决方案虽然简单，但代价也有点高。每次创建迭代器的时候，都要拷贝一份数据到快照中，会增加内存的消耗。
 * 如果一个容器同时有多个迭代器在遍历元素，就会导致数据在内存中重复存储多份。
 * 不过，庆幸的是，Java 中的拷贝属于浅拷贝，也就是说，容器中的对象并非真的拷贝了多份，而只是拷贝了对象的引用而已
 *
 *
 * 解决方案二：
 *
 * 可以在容器中，为每个元素保存两个时间戳，一个是添加时间戳 addTimestamp，一个是删除时间戳 delTimestamp。
 * 当元素被加入到集合中的时候，我们将 addTimestamp 设置为当前时间，将 delTimestamp 设置成最大长整型值（Long.MAX_VALUE）。
 * 当元素被删除时，我们将 delTimestamp 更新为当前时间，表示已经被删除。注意，这里只是标记删除，而非真正将它从容器中删除。
 * 同时，每个迭代器也保存一个迭代器创建时间戳 snapshotTimestamp，也就是迭代器对应的快照的创建时间戳。
 * 当使用迭代器来遍历容器的时候，只有满足 addTimestamp<snapshotTimestamp<delTimestamp 的元素，才是属于这个迭代器的快照。
 * 如果元素的 addTimestamp>snapshotTimestamp，说明元素在创建了迭代器之后才加入的，不属于这个迭代器的快照；
 * 如果元素的 delTimestamp<snapshotTimestamp，说明元素在创建迭代器之前就被删除掉了，也不属于这个迭代器的快照。
 * 这样就在不拷贝容器的情况下，在容器本身上借助时间戳实现了快照功能。
 *
 * 上面的解决方案相当于解决了一个问题，又引入了另外一个问题。
 * ArrayList 底层依赖数组这种数据结构，原本可以支持快速的随机访问，在 O(1) 时间复杂度内获取下标为 i 的元素，
 * 但现在，删除数据并非真正的删除，只是通过时间戳来标记删除，这就导致无法支持按照下标快速随机访问了。
 *
 * 解决的方法也不难，可以在 ArrayList 中存储两个数组。
 * 一个支持标记删除的，用来实现快照遍历功能；一个不支持标记删除的（也就是将要删除的数据直接从数组中移除），用来支持随机访问。
 */
public class Demo {

    public static void main(String[] args) {
        snapshotTest();
    }

    private static void snapshotTest() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(2);

        Iterator<Integer> iter1 = list.iterator();
        list.remove(new Integer(2));

        Iterator<Integer> iter2 = list.iterator();
        list.remove(new Integer(3));

        Iterator<Integer> iter3 = list.iterator();

        while (iter1.hasNext()) {
            System.out.println(iter1.next() + " ");
        }
        System.out.println();

        while (iter2.hasNext()) {
            System.out.println(iter1.next() + " ");
        }
        System.out.println();

        while (iter3.hasNext()) {
            System.out.println(iter1.next() + " ");
        }
        System.out.println();

    }


}
