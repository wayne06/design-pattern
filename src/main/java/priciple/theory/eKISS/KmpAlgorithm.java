package priciple.theory.eKISS;

/**
 * KMP 字符串匹配算法
 *
 * 这段代码完全符合我们刚提到的逻辑复杂、实现难度大、可读性差的特点，但它并不违反 KISS 原则
 *
 * KMP 算法以快速高效著称。
 * 当我们需要处理长文本字符串匹配问题（几百 MB 大小文本内容的匹配），或者字符串匹配是某个产品的核心功能（比如 Vim、Word 等文本编辑器），
 * 又或者字符串匹配算法是系统性能瓶颈的时候，我们就应该选择尽可能高效的 KMP 算法。
 * 而 KMP 算法本身具有逻辑复杂、实现难度大、可读性差的特点。
 * 本身就复杂的问题，用复杂的方法解决，并不违背 KISS 原则。
 *
 * 不过，平时的项目开发中涉及的字符串匹配问题，大部分都是针对比较小的文本。
 * 在这种情况下，直接调用编程语言提供的现成的字符串匹配函数就足够了。
 * 如果非得用 KMP 算法、BM 算法来实现字符串匹配，那就真的违背 KISS 原则了。
 * 也就是说，同样的代码，在某个业务场景下满足 KISS 原则，换一个应用场景可能就不满足了
 *
 *
 * KISS 原则方法论：
 * 1. 不要使用同事可能不懂的技术来实现代码，如正则、高级语法等
 * 2. 不要重复造轮子，要善用已经有的工具类库
 * 3. 不要过度优化，过度使用一些奇技淫巧，如位运算代替算术运算、复杂的条件语句代替 if-else、使用一些过于底层的函数等）来优化代码
 *
 * 越是能用简单的方法解决复杂的问题，越能体现一个人的能力
 *
 */
public class KmpAlgorithm {

    /**
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 一直找到 a[i] 和 b[j]
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + i;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到了匹配模式串
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * @param b 模式串
     * @param m 模式串的长度
     * @return
     */
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 0; i < m; i++) {
            while (k != -1 && b[k+1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

}
