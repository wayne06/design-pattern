package a.oop.action.auth.code;

/**
 * 从存储中取出 AppID 和对应的密码
 */
public interface CredentialStorage {

    String getPasswordByAppId(String appId);

}
