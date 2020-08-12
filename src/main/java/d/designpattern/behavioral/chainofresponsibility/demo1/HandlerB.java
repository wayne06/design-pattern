package d.designpattern.behavioral.chainofresponsibility.demo1;

public class HandlerB extends Handler {

    @Override
    public void handle() {
        boolean handled = false;
        //...
        System.out.println("handle() in HandlerB");
        if (!handled && successor != null) {
            successor.handle();
        }
    }

}
