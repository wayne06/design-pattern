package f.inaction.metrics.ver3;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleReporter {
    private ScheduledExecutorService executorService;
    private MetricsStorage           metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.metricsStorage = metricsStorage;
        this.viewer = viewer;
        this.aggregator = aggregator;
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executorService.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endTime = System.currentTimeMillis();
            long startTime = endTime - durationInMillis;
            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTime, endTime);

            Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);

            viewer.output(requestStats, startTime, endTime);
        }, 0, periodInSeconds, TimeUnit.SECONDS);

    }

}

