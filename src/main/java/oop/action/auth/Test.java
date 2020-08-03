package oop.action.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ApiAuthenticator apiAuthenticator = new DefaultApiAuthenticator();
        long timestamp = System.currentTimeMillis();
        apiAuthenticator.auth("http://www.wz.com/user?id=123&appid=aa&token="
                + EncryptUtil.encrypt("http://www.wz.com/user?id=123" + "aa" + "123" + timestamp)
                + "&ts=" + timestamp);
    }

}
