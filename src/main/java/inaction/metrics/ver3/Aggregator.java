package inaction.metrics.ver3;

import java.util.*;

public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfosPerApi, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfosPerApi) {
            respTimes.add(requestInfo.getResponseTime());
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));

        return requestStat;
    }

    private double tps(int count, double duration) {
        return count / duration;
    }


    private double percentile999(List<Double> dataset) {
        Collections.sort(dataset, (o1, o2) -> {
            if (o1 - o2 < 0) {
                return -1;
            } else if (o1 - o2 > 0) {
                return 1;
            } else {
                return 0;
            }
        });
        return dataset.get((int) (dataset.size() * 0.999));
    }

    private double percentile99(List<Double> dataset) {
        Collections.sort(dataset, (o1, o2) -> {
            if (o1 - o2 < 0) {
                return -1;
            } else if (o1 - o2 > 0) {
                return 1;
            } else {
                return 0;
            }
        });
        return dataset.get((int) (dataset.size() * 0.99));
    }

    private double avg(List<Double> dataset) {
        double sum = 0;
        for (Double e : dataset) {
            sum += e;
        }
        return sum / dataset.size();
    }

    private double min(List<Double> dataset) {
        double min = Double.MAX_VALUE;
        for (Double e : dataset) {
            if (e < min) {
                min = e;
            }
        }
        return min;
    }

    private double max(List<Double> dataset) {
        double max = Double.MIN_VALUE;
        for (Double e : dataset) {
            if (e > max) {
                max = e;
            }
        }
        return max;
    }

}
