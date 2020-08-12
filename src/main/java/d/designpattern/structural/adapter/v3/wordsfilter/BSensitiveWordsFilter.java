package d.designpattern.structural.adapter.v3.wordsfilter;

/**
 * @author wayne
 *
 * B敏感词过滤系统提供的接口
 */
public class BSensitiveWordsFilter {

    public String filter(String text) {
        return text.replace("???", "***");
    }

}
