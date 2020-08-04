package priciple.theory.dISP.user;

/**
 * 把“接口”理解为一组 API 接口集合：如果部分接口只被部分调用者使用，那我们就需要将这部分接口隔离出来，单独给对应的调用者使用
 *
 * 删除用户是一个非常慎重的操作，我们只希望通过后台管理系统来执行，所以这个接口只限于给后台管理系统使用。
 * 如果我们把它放到 UserService 中，那所有使用到 UserService 的系统，都可以调用这个接口。
 * 不加限制地被其他业务系统调用，就有可能导致误删用户。
 * 当然，最好的解决方案是从架构设计的层面，通过接口鉴权的方式来限制接口的调用。
 * 不过，如果暂时没有鉴权框架来支持，我们还可以从代码设计的层面，尽量避免接口被误用。
 * 我们参照接口隔离原则，调用者不应该强迫依赖它不需要的接口，将删除接口单独放到另外一个接口 RestrictedUserService 中，
 * 然后将 RestrictedUserService 只打包提供给后台管理系统来使用
 *
 * 在这个例子中，我们把接口隔离原则中的接口，理解为一组接口集合，它可以是某个微服务的接口，也可以是某个类库的接口等等。
 * 在设计微服务或者类库接口的时候，如果部分接口只被部分调用者使用，那我们就需要将这部分接口隔离出来，单独给对应的调用者使用，
 * 而不是强迫其他调用者也依赖这部分不会被用到的接口。
 *
 */
public class UserServiceImpl implements UserService, RestrictedUserService {
    @Override
    public boolean register(String cellphone, String password) {
        return false;
    }

    @Override
    public boolean login(String cellphone, String password) {
        return false;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return null;
    }

    @Override
    public UserInfo getUserInfoByCellphone(String cellphone) {
        return null;
    }

    @Override
    public boolean deleteUserByCellphone(String cellphone) {
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
