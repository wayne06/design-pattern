package inaction.ratelimiter.ver2.rule.parser;

import inaction.ratelimiter.ver2.rule.RuleConfig;

import java.io.InputStream;

/**
 * 抽象接口
 */
public interface RuleConfigParser {

    RuleConfig parse(String configText);

    RuleConfig parse(InputStream in);

}
