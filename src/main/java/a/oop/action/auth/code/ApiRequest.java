package a.oop.action.auth.code;

/**
 * 将 token、AppID、时间戳拼接到 URL 中，形成 ApiRequest；
 * 解析 ApiRequest，得到 token、AppID、时间戳等信息。
 */
public class ApiRequest {

    private String originalUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String originalUrl, String token, String appId, long timestamp) {
        this.originalUrl = originalUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public static ApiRequest buildFromUrl(String url) {
        String originalUrl = url;
        String appid = "";
        String token = "";
        String timestamp = "";
        String[] params = url.split("&");
        for (String param : params) {
            if (param.startsWith("appid=")) {
                appid = param.substring(param.indexOf("appid=") + 6);
            } else if (param.startsWith("token=")) {
                token = param.substring(param.indexOf("token=") + 6);
            } else if (param.startsWith("ts=")) {
                timestamp = param.substring(param.indexOf("ts=") + 3);
            }
        }
        url = url.replace("&appid=" + appid, "");
        url = url.replace("&token=" + token, "");
        url = url.replace("&ts=" + timestamp, "");
        return new ApiRequest(url, token, appid, Long.valueOf(timestamp));
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
