package priciple.theory.gDRY.demo3;

/**
 * demo3: 代码执行重复
 *
 * 重复执行最明显的一个地方，就是在 login() 函数中，email 的校验逻辑被执行了两次。
 * 一次是在调用 checkIfUserExisted() 函数的时候，另一次是调用 getUserByEmail() 函数的时候。
 * 这个问题解决起来比较简单，我们只需要将校验逻辑从 UserRepo 中移除，统一放到 UserService 中就可以了。
 *
 * 实际上，login() 函数并不需要调用 checkIfUserExisted() 函数，只需要调用一次 getUserByEmail() 函数，
 * 从数据库中获取到用户的 email、password 等信息，然后跟用户输入的 email、password 信息做对比，依次判断是否登录成功。
 *
 * 实际上，这样的优化是很有必要的。
 * 因为 checkIfUserExisted() 函数和 getUserByEmail() 函数都需要查询数据库，而数据库这类的 I/O 操作是比较耗时的。
 * 我们在写代码的时候，应当尽量减少这类 I/O 操作
 *
 * 优化方案见 UserService2
 *
 */
public class UserService1 {

    private UserRepo1 userRepo;

    public User login(String email, String password) {
        boolean existed = userRepo.checkIfUserExisted(email, password);
        if (!existed) {
            //...
        }
        User user = userRepo.getUserByEmail(email);
        return user;
    }

}
