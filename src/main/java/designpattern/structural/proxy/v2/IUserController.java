package designpattern.structural.proxy.v2;

import designpattern.structural.proxy.UserVo;

public interface IUserController {

    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
