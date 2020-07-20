package designpattern.structural.flyweight.demo3;

import java.awt.*;

/**
 * 对于简化的文本编辑器，我们要在内存中表示一个文本文件，只需要记录文字和格式两部分信息就可以了，其中，格式又包括文字的字体、大小、颜色等信息。
 * 从理论上讲，可以给文本文件中的每个文字都设置不同的格式。
 * 为了实现如此灵活的格式设置，并且代码实现又不过于太复杂，我们把每个文字都当作一个独立的对象来看待，并且在其中包含它的格式信息。
 *
 * 代码实例如下：
 */
public class Character {

    private char c;

    private Font font;
    private int size;
    private int colorRGB;

    public Character(char c, Font font, int size, int colorRGB) {
        this.c = c;
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }

}
