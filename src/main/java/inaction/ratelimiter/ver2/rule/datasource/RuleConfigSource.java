package inaction.ratelimiter.ver2.rule.datasource;


import inaction.ratelimiter.ver2.rule.RuleConfig;

/**
 * 抽象接口
 */
public interface RuleConfigSource {

    RuleConfig load();

}
