package f.inaction.darklaunch.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为了避免配置文件中的灰度规则热更新时，覆盖掉编程实现的灰度规则，在 DarkRule 中，我们对从配置文件中加载的灰度规则和编程实现的灰度规则分开存储
 */
public class DarkRule {


    /**
     * 从配置文件中加载的灰度规则
     */
    private Map<String, IDarkFeature> darkFeatures = new HashMap<>();

    /**
     * 编程实现的灰度规则
     */
    private ConcurrentHashMap<String, IDarkFeature> programmedDarkFeatures = new ConcurrentHashMap<>();

    public void addProgrammedDarkFeature(String featureKey, IDarkFeature darkFeature) {
        programmedDarkFeatures.putIfAbsent(featureKey, darkFeature);
    }

    public void setDarkFeatures(Map<String, IDarkFeature> darkFeatures) {
        this.darkFeatures = darkFeatures;
    }

    public IDarkFeature getDarkFeature(String featureKey) {
        IDarkFeature darkFeature = programmedDarkFeatures.get(featureKey);
        if (darkFeature != null) {
            return darkFeature;
        }
        return darkFeatures.get(featureKey);
    }
}
