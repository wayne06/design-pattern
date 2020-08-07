package priciple.theory.eKISS;

import org.apache.commons.lang3.StringUtils;

/**
 * 检查输入的字符串 ipAddress 是否是合法的 IP 地址：
 * 一个合法的 IP 地址由四个数字组成，并且通过“.”来进行分割。每组数字的取值范围是 0~255。第一组数字比较特殊，不允许为 0。
 *
 * 第三种要比第二种更加有难度，更容易写出 bug。
 * 从可读性上来说，第二种实现方式的代码逻辑更清晰、更好理解。
 * 所以，在这两种实现方式中，第二种实现方式更加“简单”，更加符合 KISS 原则
 *
 * 一般来说，工具类的功能都比较通用和全面，所以，在代码实现上，需要考虑和处理更多的细节，执行效率就会有所影响。
 * 而第三种实现方式，完全是自己操作底层字符，只针对 IP 地址这一种格式的数据输入来做处理，没有太多多余的函数调用和其他不必要的处理逻辑，
 * 所以，在执行效率上，这种类似定制化的处理代码方式肯定比通用的工具类要高些。
 *
 * 尽管第三种实现方式性能更高些，但我还是更倾向于选择第二种实现方法。那是因为第三种实现方式实际上是一种过度优化。
 * 除非 isValidIpAddress() 函数是影响系统性能的瓶颈代码，否则，这样优化的投入产出比并不高，增加了代码实现的难度、牺牲了代码的可读性，
 * 性能上的提升却并不明显。
 *
 */
public class IpAddress {

    /**
     * 第一种实现方式利用的是正则表达式
     *
     * @param ipAddress
     * @return
     */
    public boolean isValidIpAddressV1(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    /**
     * 第二种实现方式使用了 StringUtils 类、Integer 类提供的一些现成的工具函数，来处理 IP 地址字符串
     *
     * @param ipAddress
     * @return
     */
    public boolean isValidIpAddressV2(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第三种实现方式，不使用任何工具函数，而是通过逐一处理 IP 地址中的字符，来判断是否合法。
     *
     * @param ipAddress
     * @return
     */
    public boolean isValidIpAddressV3(String ipAddress) {
        char[] ipChars = ipAddress.toCharArray();
        int length = ipChars.length;
        int ipUnitIntValue = -1;
        boolean isFirstUnit = true;
        int unitsCount = 0;
        for (int i = 0; i < length; i++) {
            char c = ipChars[i];
            if (c == '.') {
                if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                    return false;
                }
                if (isFirstUnit && ipUnitIntValue == 0) {
                    return false;
                }
                if (isFirstUnit) {
                    isFirstUnit = false;
                }
                ipUnitIntValue = -1;
                unitsCount++;
                continue;
            }
            if (c < '0' || c > '9') {
                return false;
            }
            if (ipUnitIntValue == -1) {
                ipUnitIntValue = 0;
            }
            ipUnitIntValue = ipUnitIntValue * 10 + (c - '0');
        }
        if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
            return false;
        }
        if (unitsCount != 3) {
            return false;
        }
        return true;
    }

}
