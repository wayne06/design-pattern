package priciple.theory.gDRY.demo1;

import org.apache.commons.lang3.StringUtils;

/**
 * demo1: 实现逻辑重复
 *
 * 经过重构之后，代码行数减少了，也没有重复的代码了，是不是更好了呢？答案是否定的。
 * 单从名字上看，合并之后的 isValidUserNameOrPassword() 函数，负责两件事情：验证用户名和验证密码，
 * 违反了“单一职责原则”和“接口隔离原则”。
 *
 * 实际上，即便将两个函数合并成 isValidUserNameOrPassword()，代码仍然存在问题。
 * 因为 isValidUserName() 和 isValidPassword() 两个函数，虽然从代码实现逻辑上看起来是重复的，但是从语义上并不重复。
 *
 * 所谓“语义不重复”指的是：从功能上来看，这两个函数干的是完全不重复的两件事情，一个是校验用户名，另一个是校验密码。
 * 尽管在目前的设计中，两个校验逻辑是完全一样的，但如果按照第二种写法，将两个函数的合并，那就会存在潜在的问题。
 * 在未来的某一天，如果我们修改了密码的校验逻辑，比如，允许密码包含大写字符，允许密码的长度为 8 到 64 个字符，
 * 那这个时候，isValidUserName() 和 isValidPassword() 的实现逻辑就会不相同。
 * 我们就要把合并后的函数，重新拆成合并前的那两个函数。
 *
 * 尽管代码的实现逻辑是相同的，但语义不同，我们判定它并不违反 DRY 原则。
 * 对于包含重复代码的问题，我们可以通过抽象成更细粒度函数的方式来解决。
 * 比如将校验只包含 a~z、0~9、dot 的逻辑封装成 boolean onlyContains(String str, String charlist); 函数。
 *
 */
public class UserAuthenticator2 {

    public void authenticate(String username, String password) {
        if (!isValidUsernameOrPassword(username)) {
            //...
        }
        if (!isValidUsernameOrPassword(password)) {
            //...
        }
        //...
    }

    private boolean isValidUsernameOrPassword(String password) {

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

}
