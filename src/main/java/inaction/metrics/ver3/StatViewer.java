package inaction.metrics.ver3;

import java.util.Map;

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMilliss, long endTimeInMillis);
}
