package d.designpattern.behavioral.mediator.demo2;

/**
 * 按照中介模式，将 demo1 的代码重新实现一下。在新的代码实现中，各个控件只跟中介对象交互，中介对象负责所有业务逻辑的处理
 *
 * 从代码中可以看出，原本业务逻辑会分散在各个控件中，现在都集中到了中介类中。实际上，这样做既有好处，也有坏处。
 * 好处是简化了控件之间的交互，坏处是中介类有可能会变成大而复杂的“上帝类”（God Class）。
 * 所以，在使用中介模式的时候，我们要根据实际的情况，平衡对象之间交互的复杂度和中介类本身的复杂度。
 */
public class UIControl {

    private static final String LOGIN_BTN_ID               = "login_btn";
    private static final String REG_BTN_ID                 = "reg_btn";
    private static final String USERNAME_INPUT_ID          = "username_input";
    private static final String PASSWORD_INPUT_ID          = "pswd_input";
    private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
    private static final String HINT_TEXT_ID               = "hint_text";
    private static final String SELECTION_ID               = "selection";

    public static void main(String[] args) {
        //Button loginButton = (Button) findViewById(LOGIN_BTN_ID);
        //Button regButton = (Button) findViewById(REG_BTN_ID);
        //Input usernameInput = (Input) findViewById(USERNAME_INPUT_ID);
        //Input passwordInput = (Input) findViewById(PASSWORD_INPUT_ID);
        //Input repeatedPswdInput = (Input) findViewById(REPEATED_PASSWORD_INPUT_ID);
        //Text hintText = (Text) findViewById(HINT_TEXT_ID);
        //Selection selection = (Selection) findViewById(SELECTION_ID);
        //
        //Mediator dialog = new LandingPageDialog();
        //dialog.setLoginButton(loginButton);
        //dialog.setRegButton(regButton);
        //dialog.setUsernameInput(usernameInput);
        //dialog.setPasswordInput(passwordInput);
        //dialog.setRepeatedPswdInput(repeatedPswdInput);
        //dialog.setHintText(hintText);
        //dialog.setSelection(selection);
        //loginButton.setOnClickListener(new OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        dialog.handleEvent(loginButton, "click");
        //    }
        //});
        //regButton.setOnClickListener(new OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        dialog.handleEvent(regButton, "click");
        //    }
        //});

        //...

    }
}
