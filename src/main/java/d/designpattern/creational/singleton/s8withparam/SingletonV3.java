package d.designpattern.creational.singleton.s8withparam;

public class SingletonV3 {
    private static SingletonV3 instance = null;
    private final int paramA;
    private final int paramB;

    private SingletonV3() {
        this.paramA = Config.PARAM_A;
        this.paramB = Config.PARAM_B;
    }

    public synchronized static SingletonV3 getInstance() {
        if (instance == null) {
            instance = new SingletonV3();
        }
        return instance;
    }
}
