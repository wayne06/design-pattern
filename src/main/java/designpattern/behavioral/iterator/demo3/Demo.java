package designpattern.behavioral.iterator.demo3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 在通过迭代器来遍历集合元素的同时，增加或者删除集合中的元素，有可能会导致某个元素被重复遍历或遍历不到。
 * 不过，并不是所有情况下都会遍历出错，有的时候也可以正常遍历，所以，这种行为称为结果不可预期行为或者未决行为
 *
 */
public class Demo {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        iteratorRemoveTest(names);
    }

    /**
     * 在遍历的过程中删除集合元素，会发生什么？
     *
     * ArrayList 底层对应的是数组这种数据结构，在执行完 Iterator<String> iterator = names.iterator() 的时候，数组中存储的是 a、b、c、d 四个元素，
     * 迭代器的游标 cursor 指向元素 a；当执行完 iterator.next() 的时候，游标指向元素 b，到这里都没有问题。
     * 为了保持数组存储数据的连续性，数组的删除操作会涉及元素的搬移，当执行到 names.remove("a") 的时候，从数组中将元素 a 删除掉，b、c、d 三个元素会依次往前搬移一位，
     * 这就会导致游标本来指向元素 b，现在变成了指向元素 c。
     * 原本在执行完 iterator.next() 之后，还可以遍历到 b、c、d 三个元素，但在执行完 names.remove("a") 之后，只能遍历到 c、d 两个元素，b 遍历不到了。
     */
    public static void removeTest(List<String> names) {
        Iterator<String> iterator = names.iterator();
        iterator.next();
        names.remove("a");
    }

    /**
     * 在遍历的过程中添加集合元素，会发生什么？
     *
     * 如果在游标的前面添加元素，就会存在元素被重复遍历的情况
     * 如果在游标的后面添加元素，就不会存在任何问题。所以，在遍历的同时添加集合元素也是一种不可预期行为
     */
    public static void addTest(List<String> names) {
        Iterator<String> iterator = names.iterator();
        iterator.next();
        names.add(0, "x");
    }

    /**
     * 如何应对遍历时改变集合导致的未决行为：增删元素之后，让遍历报错
     *
     * 在 ArrayList 中定义一个成员变量 modCount，记录集合被修改的次数，集合每调用一次增加或删除元素的函数，就会给 modCount 加 1。
     * 当通过调用集合上的 iterator() 函数来创建迭代器的时候，把 modCount 值传递给迭代器的 expectedModCount 成员变量，
     * 之后每次调用迭代器上的 hasNext()、next()、currentItem() 函数，我们都会检查集合上的 modCount 是否等于 expectedModCount，
     * 也就是看，在创建完迭代器之后，modCount 是否改变过。
     * 如果两个值不相同，那就说明集合存储的元素已经改变了，要么增加了元素，要么删除了元素，之前创建的迭代器已经不能正确运行了，
     * 再继续使用就会产生不可预期的结果，所以我们选择 fail-fast 解决方式，抛出运行时异常，结束掉程序，让程序员尽快修复这个因为不正确使用迭代器而产生的 bug
     */
    public static void failFastTest(List<String> names) {
        Iterator<String> iterator = names.iterator();
        iterator.next();
        names.remove("a");
        iterator.next();  // 会抛出ConcurrentModificationException异常
    }

    /**
     * 如何在遍历的同时安全地删除集合元素：
     *
     * 迭代器类中定义了一个 remove() 方法，能够在遍历集合的同时，安全地删除集合中的元素。
     * 它并没有提供添加元素的方法，毕竟迭代器的主要作用是遍历，添加元素放到迭代器里本身就不合适
     * 迭代器的 remove() 方法只能删除游标指向的前一个元素，而且一个 next() 函数之后，只能跟着最多一个 remove() 操作，多次调用 remove() 操作会报错
     *
     *
     * 为什么通过迭代器就能安全的删除集合中的元素呢？
     *
     * 在 Java 实现中，迭代器类是容器类的内部类，并且 next() 函数不仅将游标后移一位，还会返回当前的元素
     * 迭代器类 Itr 新增了一个 lastRet 成员变量，用来记录游标指向的前一个元素。
     * 通过迭代器去删除这个元素的时候，我们可以更新迭代器中的游标和 lastRet 值，来保证不会因为删除元素而导致某个元素遍历不到。
     * 如果通过容器来删除元素，并且希望更新迭代器中的游标值来保证遍历不出错，就要维护这个容器都创建了哪些迭代器，每个迭代器是否还在使用等信息，代码实现就变得比较复杂了。
     */
    public static void iteratorRemoveTest(List<String> names) {
        Iterator<String> iterator = names.iterator();
        iterator.next();
        iterator.remove();  // [b, c, d]
        System.out.println(names);
        iterator.remove();  // java.lang.IllegalStateException
        System.out.println(names);
    }

}
