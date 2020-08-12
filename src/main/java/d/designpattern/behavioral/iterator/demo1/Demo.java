package d.designpattern.behavioral.iterator.demo1;

public class Demo {

    public static void main(String[] args) {
        MyList<String> names = new MyArrayList<>();
        names.add("abc");
        names.add("def");
        names.add("ghi");

        MyIterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }


}
