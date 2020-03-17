package designpattern.creational.singleton.s10singletonincluster;

public class Demo {

    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        long id = idGenerator.getId();
        idGenerator.freeInstance();
    }

}
