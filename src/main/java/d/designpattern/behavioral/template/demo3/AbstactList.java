package d.designpattern.behavioral.template.demo3;

import java.util.Collection;

/**
 * 在 Java AbstractList 类中，addAll() 函数可以看作模板方法，add() 是子类需要重写的方法，
 *
 * 尽管没有声明为 abstract 的，但函数实现直接抛出了 UnsupportedOperationException 异常。
 *
 * 前提是，如果子类不重写是不能使用的
 *
 * @param <E>
 */
public abstract class AbstactList<E> {

    public abstract int size();

    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

}
