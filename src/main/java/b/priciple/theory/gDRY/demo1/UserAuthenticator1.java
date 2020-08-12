package b.priciple.theory.gDRY.demo1;

import org.apache.commons.lang3.StringUtils;

/**
 * demo1: 实现逻辑重复
 *
 * 在下面代码中，有两处非常明显的重复的代码片段：isValidUserName() 函数和 isValidPassword() 函数。
 * 重复的代码被敲了两遍，或者简单 copy-paste 了一下，看起来明显违反 DRY 原则。
 * 为了移除重复的代码，对其进行重构，将 isValidUserName() 函数和 isValidPassword() 函数，
 * 合并为一个更通用的函数 isValidUserNameOrPassword()，见 UserAuthenticator2
 *
 */
public class UserAuthenticator1 {

    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            //...
        }
        if (!isValidPassword(password)) {
            //...
        }
        //...
    }

    private boolean isValidPassword(String password) {

        if (StringUtils.isBlank(password)) {
            return false;
        }

        if (password.length() < 4 || password.length() > 64) {
            return false;
        }

        if (!StringUtils.isAllLowerCase(password)) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.')) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidUsername(String username) {

        if (StringUtils.isBlank(username)) {
            return false;
        }

        if (username.length() < 4 || username.length() > 64) {
            return false;
        }

        if (!StringUtils.isAllLowerCase(username)) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.')) {
                return false;
            }
        }

        return true;
    }


}
