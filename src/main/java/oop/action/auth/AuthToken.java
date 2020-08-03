package oop.action.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 把 URL、AppID、密码、时间戳拼接为一个字符串；
 * 对字符串通过加密算法加密生成 token；
 * 根据时间戳判断 token 是否过期失效；
 * 验证两个 token 是否匹配
 */
public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this(token, createTime, DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken generate(String appId, String baseUrl, String password, long createTime) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return new AuthToken(EncryptUtil.encrypt(baseUrl + appId + password + createTime), createTime);
    }

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
        return (createTime + expiredTimeInterval) < System.currentTimeMillis();
    }

    public boolean match(AuthToken authToken) {
        System.out.println(authToken.getToken());
        System.out.println(this.getToken());
        return authToken.getToken().equals(this.getToken());
    }

}
