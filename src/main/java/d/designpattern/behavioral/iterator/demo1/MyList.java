package d.designpattern.behavioral.iterator.demo1;

public interface MyList<E> {

    MyIterator iterator();

    int size();

    E get(int index);

    void add(E e);

    // ..省略其他接口函数..

}
