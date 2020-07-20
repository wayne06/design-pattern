package designpattern.behavioral.template.demo6;

public class BClass {

    public void process(ICallback callback) {
        //...
        callback.methodToCallback();
        //...
    }

}
