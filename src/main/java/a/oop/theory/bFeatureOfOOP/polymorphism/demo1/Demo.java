package a.oop.theory.bFeatureOfOOP.polymorphism.demo1;

/**
 * 多态是指，子类可以替换父类，在实际的代码运行过程中，调用子类的方法实现
 *
 * 用到了三个语法机制来实现多态。
 * 第一个语法机制是编程语言要支持父类对象可以引用子类对象，也就是可以将 SortedDynamicArray 传递给 DynamicArray。
 * 第二个语法机制是编程语言要支持继承，也就是 SortedDynamicArray 继承了 DynamicArray，才能将 SortedDyamicArray 传递给 DynamicArray。
 * 第三个语法机制是编程语言要支持子类可以重写（override）父类中的方法，也就是 SortedDyamicArray 重写了 DynamicArray 中的 add() 方法。
 *
 * 通过这三种语法机制配合在一起，我们就实现了在 test() 方法中，子类 SortedDyamicArray 替换父类 DynamicArray，
 * 执行子类 SortedDyamicArray 的 add() 方法，也就是实现了多态特性。
 *
 * 对于多态特性的实现方式，除了利用“继承加方法重写”这种实现方式之外，我们还有其他两种比较常见的的实现方式，
 * 一个是利用接口类语法（demo2），另一个是利用 duck-typing 语法。
 * 不过，并不是每种编程语言都支持接口类或者 duck-typing 这两种语法机制，比如 C++ 就不支持接口类语法，
 * 而 duck-typing 只有一些动态语言才支持，比如 Python、JavaScript 等。
 *
 *
 *
 */
public class Demo {

    public static void main(String[] args) {
        DynamicArray dynamicArray = new SortedDynamicArray();
        test(dynamicArray);
    }

    private static void test(DynamicArray dynamicArray) {
        dynamicArray.add(5);
        dynamicArray.add(1);
        dynamicArray.add(3);
        for (int i = 0; i < dynamicArray.size(); i++) {
            System.out.println(dynamicArray.get(i));
        }
    }

}
