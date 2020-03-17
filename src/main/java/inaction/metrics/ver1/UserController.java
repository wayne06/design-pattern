package inaction.metrics.ver1;

/**
 * @author wayne
 */
public class UserController {

    private Metrics metrics = new Metrics();

    /**
     * @param telephone
     * @param password
     */
    public void register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register", startTimestamp);

        System.out.println("register");

        long responseTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", responseTime);
    }

    /**
     * @param telephone
     * @param password
     */
    public void login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);

        System.out.println("login");

        long responseTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", responseTime);
    }


}
