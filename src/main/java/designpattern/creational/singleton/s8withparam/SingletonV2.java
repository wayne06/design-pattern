package designpattern.creational.singleton.s8withparam;

public class SingletonV2 {

    private static SingletonV2 instance = null;
    private final int paramA;
    private final int paramB;

    private SingletonV2(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public synchronized static SingletonV2 getInstance(int paramA, int paramB) {
        if (instance == null) {
            instance = new SingletonV2(paramA, paramB);
        }
        return instance;
    }
}
