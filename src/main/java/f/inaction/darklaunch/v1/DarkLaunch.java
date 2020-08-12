package f.inaction.darklaunch.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 框架的最顶层入口类
 */
public class DarkLaunch {

    private static final Logger                   LOGGER                            = LoggerFactory.getLogger(DarkLaunch.class);
    private static final int                      DEFAULT_RULE_UPDATE_TIME_INTERVAL = 10;
    private              DarkRule                 rule;
    private              ScheduledExecutorService executor;

    public DarkLaunch(int ruleUpdateTimeInterval) {
        loadRule();
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(() -> {
                    loadRule();
                    System.out.println("in constructor of DarkLaunch");
                },
                ruleUpdateTimeInterval, ruleUpdateTimeInterval, TimeUnit.SECONDS);
    }

    public DarkLaunch() {
        this(DEFAULT_RULE_UPDATE_TIME_INTERVAL);
    }

    /**
     * 将灰度规则配置文件 dark-rule.yaml 中的内容读取到 DarkRuleConfig 中
     */
    private void loadRule() {
        InputStream in = null;
        DarkRuleConfig ruleConfig = null;
        try {
            in = this.getClass().getResourceAsStream("/dark-rule.yaml");
            if (in != null) {
                Yaml yaml = new Yaml();
                ruleConfig = yaml.loadAs(in, DarkRuleConfig.class);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("close file error: {}", e);
                }
            }
        }
        if (ruleConfig == null) {
            throw new RuntimeException("can not load dark rule.");
        }
        // 更新规则并非直接在 this.rule 上进行，而是通过创建一个新的 DarkRule 然后赋值给 this.rule，来避免更新规则和规则查询的并发冲突问题
        DarkRule newRule = new DarkRule(ruleConfig);
        this.rule = newRule;
    }

    public DarkFeature getDarkFeature(String featureKey) {
        DarkFeature darkFeature = this.rule.getDarkFeature(featureKey);
        return darkFeature;
    }
}
