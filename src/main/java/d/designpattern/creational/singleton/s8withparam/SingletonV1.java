package d.designpattern.creational.singleton.s8withparam;

public class SingletonV1 {

    private static SingletonV1 instance = null;
    private final int paramA;
    private final int paramB;

    private SingletonV1(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public static SingletonV1 getInstance() {
        if (instance == null) {
            throw new RuntimeException("Run init() first.");
        }
        return instance;
    }

    public synchronized static SingletonV1 init(int paramA, int paramB) {
        if (instance != null) {
            throw new RuntimeException("Singleton has been created.");
        }
        instance = new SingletonV1(paramA, paramB);
        return instance;
    }
}
