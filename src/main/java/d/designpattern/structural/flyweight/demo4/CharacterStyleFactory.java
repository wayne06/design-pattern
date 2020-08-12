package d.designpattern.structural.flyweight.demo4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterStyleFactory {

    private static final List<CharacterStyle> STYLES = new ArrayList<>();

    public static CharacterStyle getStyle(Font font, int size, int colorRGB) {
        CharacterStyle newStyle = new CharacterStyle(font, size, colorRGB);
        for (CharacterStyle style : STYLES) {
            if (style.equals(newStyle)) {
                return style;
            }
        }
        STYLES.add(newStyle);
        return newStyle;
    }
}
