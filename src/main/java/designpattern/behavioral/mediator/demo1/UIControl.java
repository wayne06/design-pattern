package designpattern.behavioral.mediator.demo1;

/**
 * 假设有一个比较复杂的对话框，对话框中有很多控件，比如按钮、文本框、下拉框等。
 * 当我们对某个控件进行操作的时候，其他控件会做出相应的反应，比如，在下拉框中选择“注册”，注册相关的控件就会显示在对话框中。
 * 如果我们在下拉框中选择“登陆”，登陆相关的控件就会显示在对话框中
 *
 * 按照中介模式，将上面的代码重新实现一下，见 demo2
 */
public class UIControl {

    private static final String LOGIN_BTN_ID               = "login_btn";
    private static final String REG_BTN_ID                 = "reg_btn";
    private static final String USERNAME_INPUT_ID          = "username_input";
    private static final String PASSWORD_INPUT_ID          = "pswd_input";
    private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
    private static final String HINT_TEXT_ID               = "hint_text";
    private static final String SELECTION_ID               = "selection";

    //public static void main(String[] args) {
    //    Button loginButton = (Button) findViewById(LOGIN_BTN_ID);
    //    Button regButton = (Button) findViewById(REG_BTN_ID);
    //    Input usernameInput = (Input) findViewById(USERNAME_INPUT_ID);
    //    Input passwordInput = (Input) findViewById(PASSWORD_INPUT_ID);
    //    Input repeatedPswdInput = (Input) findViewById(REPEATED_PASSWORD_INPUT_ID);
    //    Text hintText = (Text) findViewById(HINT_TEXT_ID);
    //    Selection selection = (Selection) findViewById(SELECTION_ID);
    //
    //    loginButton.setOnClickListener(new OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //            String username = usernameInput.text();
    //            String password = passwordInput.text();
    //            //校验数据...
    //            //做业务处理...
    //        }
    //    });
    //
    //    regButton.setOnClickListener(new OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //            //获取usernameInput、passwordInput、repeatedPswdInput数据...
    //            //校验数据...
    //            //做业务处理...
    //        }
    //    });
    //
    //    //...省略selection下拉选择框相关代码....
    //}

}
