package designpattern.behavioral.iterator.demo1;

public class MyArrayList<E> implements MyList<E> {

    @Override
    public MyIterator iterator() {
        return new MyArrayIterator(this);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void add(E e) {

    }

}
