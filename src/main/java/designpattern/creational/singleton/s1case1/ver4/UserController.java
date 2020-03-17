package designpattern.creational.singleton.s1case1.ver4;

import java.io.IOException;

public class UserController {

    public void login(String username, String password) {
        try {
            Logger.getInstance().log(username + "logined!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
