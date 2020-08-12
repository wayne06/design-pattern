package a.oop.action.auth.code;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class DefaultApiAuthenticator implements ApiAuthenticator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthenticator() {
        this.credentialStorage = new MysqlCredentialStorage();
    }

    public DefaultApiAuthenticator(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String url = apiRequest.getOriginalUrl();
        long timestamp = apiRequest.getTimestamp();
        String token = apiRequest.getToken();

        AuthToken clientToken = new AuthToken(token, timestamp);
        if (clientToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }

        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverToken = null;
        try {
            serverToken = AuthToken.generate(appId, url, password, timestamp);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!clientToken.match(serverToken)) {
            throw new RuntimeException("Token verification failed.");
        }
        System.out.println("Verification success.");

    }
}
