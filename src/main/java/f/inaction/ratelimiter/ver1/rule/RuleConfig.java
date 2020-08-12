package f.inaction.ratelimiter.ver1.rule;

import java.util.List;

/**
 * RuleConfig 类嵌套了另外两个类 AppRuleConfig 和 ApiLimit。
 *
 * 这三个类跟配置文件的三层嵌套结构完全对应。我把对应关系标注在了下面的示例中
 */
public class RuleConfig {

    private List<AppRuleConfig> configs;

    public List<AppRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppRuleConfig> configs) {
        this.configs = configs;
    }


    public static class AppRuleConfig {
        private String appId;
        private List<ApiLimit> limits;

        public AppRuleConfig() {
        }

        public AppRuleConfig(String appId, List<ApiLimit> limits) {
            this.appId = appId;
            this.limits = limits;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<ApiLimit> getLimits() {
            return limits;
        }

        public void setLimits(List<ApiLimit> limits) {
            this.limits = limits;
        }
    }
}
