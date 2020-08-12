package d.designpattern.behavioral.template.demo3;

import java.util.Collections;

public class Demo {

    public static void main(String[] args) {
        AbstactList list = new ImplList();
        list.addAll(1, Collections.singleton(new int[]{1, 2, 3}));
    }
}
