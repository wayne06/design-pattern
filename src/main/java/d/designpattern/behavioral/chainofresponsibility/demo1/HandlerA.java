package d.designpattern.behavioral.chainofresponsibility.demo1;

public class HandlerA extends Handler {

    @Override
    public void handle() {
        boolean handled = false;
        //...
        System.out.println("handle() in HandlerA");
        if (!handled && successor != null) {
            successor.handle();
        }
    }

}
