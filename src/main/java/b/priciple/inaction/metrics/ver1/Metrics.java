package b.priciple.inaction.metrics.ver1;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wayne
 */
public class Metrics {

    private Map<String, List<Double>> responseTimes = new HashMap<>();
    private Map<String, List<Double>> timestamps    = new HashMap<>();
    private ScheduledExecutorService  executor      = Executors.newSingleThreadScheduledExecutor();

    /**
     * 记录接口请求的响应时间
     * @param apiName
     * @param responseTime
     */
    public void recordResponseTime(String apiName, double responseTime) {
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    /**
     * 记录接口请求的访问时间
     * @param apiName
     * @param timestamp
     */
    public void recordTimestamp(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    /**
     * 以指定的频率统计数据并输出结果
     * @param period
     * @param unit
     */
    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(() -> {
            Gson gson = new Gson();
            Map<String, Map<String, Double>> stats = new HashMap<>();
            for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
                String apiName = entry.getKey();
                List<Double> apiRespTimes = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("max", max(apiRespTimes));
                stats.get(apiName).put("avg", avg(apiRespTimes));
            }

            for (Map.Entry<String, List<Double>> entry : timestamps.entrySet()) {
                String apiName = entry.getKey();
                List<Double> apiTimestamps = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("count", (double) apiTimestamps.size());
            }
            System.out.println(gson.toJson(stats));
        }, 0, period, unit);
    }

    private double max(List<Double> dataset) {
        return 0;
    }

    private double avg(List<Double> dataset) {
        return 0;
    }
}
