package f.inaction.ratelimiter.ver2.rule.parser;

import f.inaction.ratelimiter.ver2.rule.RuleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

/**
 * Yaml 格式配置文件解析类
 */
public class YamlRuleConfigParser implements RuleConfigParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(YamlRuleConfigParser.class);

    @Override
    public RuleConfig parse(String configText) {
        return null;
    }

    @Override
    public RuleConfig parse(InputStream in) {
        // 将限流规则配置文件中的内容读取到 RuleConfig 中
        RuleConfig ruleConfig = null;
        try {
            if (in != null) {
                Yaml yaml = new Yaml();
                ruleConfig = yaml.loadAs(in, RuleConfig.class);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("close file error: {}", e);
                }
            }
        }
        return ruleConfig;
    }
}
