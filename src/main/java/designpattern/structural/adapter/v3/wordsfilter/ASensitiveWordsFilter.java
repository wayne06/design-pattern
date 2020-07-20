package designpattern.structural.adapter.v3.wordsfilter;

/**
 * @author wayne
 *
 * A敏感词过滤系统提供的接口：text是原始文本，函数输出用***替换敏感词之后的文本
 */
public class ASensitiveWordsFilter {

    public String filterSexyWords(String text) {
        return text.replace("sexy", "***");
    }

    public String filterPoliticalWords(String text) {
        return text.replace("political", "***");
    }

}
