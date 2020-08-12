package f.inaction.metrics.ver2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleReporter {
    private ScheduledExecutorService executorService;
    private MetricsStorage           metricsStorage;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.metricsStorage = metricsStorage;
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executorService.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endTime = System.currentTimeMillis();
            long startTime = endTime - durationInMillis;
            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTime, endTime);

            Map<String, RequestStat> stats = new HashMap<>();
            for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                String apiName = entry.getKey();
                List<RequestInfo> requestInfosPerApi = entry.getValue();

                RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                stats.put(apiName, requestStat);
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }

}

