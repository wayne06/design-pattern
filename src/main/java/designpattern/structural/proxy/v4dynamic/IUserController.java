package designpattern.structural.proxy.v4dynamic;

import designpattern.structural.proxy.UserVo;

public interface IUserController {

    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
