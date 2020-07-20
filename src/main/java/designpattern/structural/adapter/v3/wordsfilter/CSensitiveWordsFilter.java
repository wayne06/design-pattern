package designpattern.structural.adapter.v3.wordsfilter;

/**
 * @author wayne
 *
 * C敏感词过滤系统提供的接口
 */
public class CSensitiveWordsFilter {

    public String filter(String text, String mask) {
        return text.replace("???", mask);
    }

}
