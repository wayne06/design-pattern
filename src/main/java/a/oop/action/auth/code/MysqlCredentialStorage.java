package a.oop.action.auth.code;

public class MysqlCredentialStorage implements CredentialStorage {
    @Override
    public String getPasswordByAppId(String appId) {
        return "123";
    }
}
