package f.inaction.darklaunch.v1;

import java.util.List;

/**
 * 用来映射配置到内存中
 *
 * DarkRuleConfig 类嵌套了一个内部类 DarkFeatureConfig。这两个类跟配置文件的两层嵌套结构完全对应
 */
public class DarkRuleConfig {

    private List<DarkFeatureConfig> features;

    public List<DarkFeatureConfig> getFeatures() {
        return features;
    }

    public void setFeatures(List<DarkFeatureConfig> features) {
        this.features = features;
    }

    public static class DarkFeatureConfig {
        private String key;
        private boolean enabled;
        private String rule;

        public DarkFeatureConfig(String key, boolean enabled, String rule) {
            this.key = key;
            this.enabled = enabled;
            this.rule = rule;
        }

        public DarkFeatureConfig() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }
    }
}
