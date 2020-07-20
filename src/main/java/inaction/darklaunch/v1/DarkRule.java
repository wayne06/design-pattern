package inaction.darklaunch.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 灰度规则
 *
 * DarkRule 包含所有要灰度的业务功能的灰度规则。它用来支持根据业务功能标识（feature key），快速查询灰度规则（DarkFeature）
 */
public class DarkRule {

    private Map<String, DarkFeature> darkFeatures = new HashMap<>();

    public DarkRule(DarkRuleConfig darkRuleConfig) {
        List<DarkRuleConfig.DarkFeatureConfig> darkFeatureConfigs = darkRuleConfig.getFeatures();
        for (DarkRuleConfig.DarkFeatureConfig darkFeatureConfig : darkFeatureConfigs) {
            darkFeatures.put(darkFeatureConfig.getKey(), new DarkFeature(darkFeatureConfig));
        }
    }

    public DarkFeature getDarkFeature(String featureKey) {
        return darkFeatures.get(featureKey);
    }
}
