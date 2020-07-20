package designpattern.behavioral.template.demo9;

/**
 * JVM 提供了 Runtime.addShutdownHook(Thread hook) 方法，可以注册一个 JVM 关闭的 Hook。当应用程序关闭的时候，JVM 会自动调用 Hook 代码
 */
public class ShutdownHookDemo {

    private static class ShutdownHook extends Thread {
        @Override
        public void run() {
            System.out.println("has been called during shutting down");
        }
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

}
