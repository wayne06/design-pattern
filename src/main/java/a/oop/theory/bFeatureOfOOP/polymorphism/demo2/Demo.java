package a.oop.theory.bFeatureOfOOP.polymorphism.demo2;

/**
 * 利用接口类来实现多态特性
 *
 * 在下面的代码中，Iterator 是一个接口类，定义了一个可以遍历集合数据的迭代器。Array 和 LinkedList 都实现了接口类 Iterator。
 * 通过传递不同类型的实现类（Array、LinkedList）到 print(Iterator iterator) 函数中，支持动态的调用不同的 next()、hasNext() 实现。
 *
 * 具体点讲就是，当往 print(Iterator iterator) 函数传递 Array 类型的对象的时候，
 * print(Iterator iterator) 函数就会调用 Array 的 next()、hasNext() 的实现逻辑；
 * 当往 print(Iterator iterator) 函数传递 LinkedList 类型的对象的时候，
 * print(Iterator iterator) 函数就会调用 LinkedList 的 next()、hasNext() 的实现逻辑
 */
public class Demo {

    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);

        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }

    private static void print(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
