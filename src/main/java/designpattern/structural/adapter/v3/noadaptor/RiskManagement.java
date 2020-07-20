package designpattern.structural.adapter.v3.noadaptor;

import designpattern.structural.adapter.v3.wordsfilter.ASensitiveWordsFilter;
import designpattern.structural.adapter.v3.wordsfilter.BSensitiveWordsFilter;
import designpattern.structural.adapter.v3.wordsfilter.CSensitiveWordsFilter;

/**
 * @author wayne
 *
 * 未使用适配器模式：代码的可测试性、扩展性不好
 */
public class RiskManagement {

    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
    private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();

    public String filterSensitiveWords(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        maskedText = bFilter.filter(maskedText);
        maskedText = cFilter.filter(maskedText, "***");
        return maskedText;
    }

}
