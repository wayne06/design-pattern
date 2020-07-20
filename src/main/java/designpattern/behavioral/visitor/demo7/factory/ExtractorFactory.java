package designpattern.behavioral.visitor.demo7.factory;

import designpattern.behavioral.visitor.demo7.extractor.Extractor;
import designpattern.behavioral.visitor.demo7.extractor.PdfExtractor;
import designpattern.behavioral.visitor.demo7.extractor.PptExtractor;
import designpattern.behavioral.visitor.demo7.extractor.WordExtractor;
import designpattern.behavioral.visitor.demo7.file.ResourceFileType;

import java.util.HashMap;
import java.util.Map;

public class ExtractorFactory {

    private static final Map<ResourceFileType, Extractor> FILE_TYPE_EXTRACTOR_MAP = new HashMap<>();

    static {
        FILE_TYPE_EXTRACTOR_MAP.put(ResourceFileType.PDF, new PdfExtractor());
        FILE_TYPE_EXTRACTOR_MAP.put(ResourceFileType.PPT, new PptExtractor());
        FILE_TYPE_EXTRACTOR_MAP.put(ResourceFileType.WORD, new WordExtractor());
    }

    public static Extractor getExtractor(ResourceFileType type) {
        return FILE_TYPE_EXTRACTOR_MAP.get(type);
    }

}
