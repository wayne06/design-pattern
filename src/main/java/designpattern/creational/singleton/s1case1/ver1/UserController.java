package designpattern.creational.singleton.s1case1.ver1;

import java.io.IOException;

public class UserController {

    private Logger logger = new Logger();

    public void login(String username, String password) {
        try {
            logger.log(username + "logined!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
