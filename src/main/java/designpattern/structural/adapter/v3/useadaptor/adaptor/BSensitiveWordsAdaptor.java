package designpattern.structural.adapter.v3.useadaptor.adaptor;

import designpattern.structural.adapter.v3.useadaptor.ISensitiveWordsFilter;
import designpattern.structural.adapter.v3.wordsfilter.BSensitiveWordsFilter;

public class BSensitiveWordsAdaptor implements ISensitiveWordsFilter {

    private BSensitiveWordsFilter bFilter;

    @Override
    public String filter(String text) {
        String maskedText = bFilter.filter(text);
        return maskedText;
    }
}
