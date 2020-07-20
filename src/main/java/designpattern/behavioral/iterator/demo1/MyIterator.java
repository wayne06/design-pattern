package designpattern.behavioral.iterator.demo1;

/**
 * 接口定义方式一:
 *
 * next() 函数用来将游标后移一位元素，currentItem() 函数用来返回当前游标指向的元素
 *
 * 第一种定义方式更加灵活一些，比如我们可以多次调用 currentItem() 查询当前元素，而不移动游标
 *
 */
public interface MyIterator<E> {

    boolean hasNext();

    void next();

    E currentItem();
}

/**
 * 接口定义方式二:
 *
 * 返回当前元素与后移一位这两个操作，要放到同一个函数 next() 中完成
 *
 */
// public interface Iterator<E> {
//
//     boolean hasNext();
//
//     E next();
// }
