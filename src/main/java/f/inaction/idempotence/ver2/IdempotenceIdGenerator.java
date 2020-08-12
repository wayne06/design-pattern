package f.inaction.idempotence.ver2;

import java.util.UUID;

public class IdempotenceIdGenerator {

    public String generateId() {
        return UUID.randomUUID().toString();
    }

}
