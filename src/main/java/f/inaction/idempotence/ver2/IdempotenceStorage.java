package f.inaction.idempotence.ver2;

public interface IdempotenceStorage {

    boolean saveIfAbsent(String idempotenceId);

    void delete(String idempotenceId);

}
