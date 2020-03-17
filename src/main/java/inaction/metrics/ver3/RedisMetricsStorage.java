package inaction.metrics.ver3;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

public class RedisMetricsStorage implements MetricsStorage {

    private RedisTemplate redisTemplate;

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        redisTemplate.opsForValue().set(requestInfo.getApiName(), requestInfo);
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
