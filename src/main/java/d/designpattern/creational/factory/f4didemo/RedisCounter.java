package d.designpattern.creational.factory.f4didemo;

public class RedisCounter {

    private String ip;

    private String port;

    public RedisCounter(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public void test() {
        System.out.println("test -> " + ip + ":" + port );
    }
}
