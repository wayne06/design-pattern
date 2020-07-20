package designpattern.structural.adapter.v3.useadaptor.adaptor;

import designpattern.structural.adapter.v3.useadaptor.ISensitiveWordsFilter;
import designpattern.structural.adapter.v3.wordsfilter.CSensitiveWordsFilter;

public class CSensitiveWordsAdaptor implements ISensitiveWordsFilter {

    private CSensitiveWordsFilter cFilter;

    @Override
    public String filter(String text) {
        String maskedText = cFilter.filter(text, "***");
        return maskedText;
    }
}
