package f.inaction.idempotence.prepare;

public class Idempotence {
    public boolean check(String idempotenceId) {
        return false;
    }

    public void record(String idempotenceId) {
        //...
    }

    public String createId() {
        return null;
    }
}
