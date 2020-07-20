package designpattern.behavioral.iterator.demo1;

import java.util.NoSuchElementException;

public class MyArrayIterator<E> implements MyIterator<E> {

    private int cursor;

    private MyArrayList<E> arrayList;

    public MyArrayIterator(MyArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        //注意这里，cursor在指向最后一个元素的时候，hasNext()仍旧返回true
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }
}
