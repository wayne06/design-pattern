package designpattern.structural.adapter.v3.useadaptor.adaptor;

import designpattern.structural.adapter.v3.useadaptor.ISensitiveWordsFilter;
import designpattern.structural.adapter.v3.wordsfilter.ASensitiveWordsFilter;

public class ASensitiveWordsAdaptor implements ISensitiveWordsFilter {

    private ASensitiveWordsFilter aFilter;

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        return maskedText;
    }
}
