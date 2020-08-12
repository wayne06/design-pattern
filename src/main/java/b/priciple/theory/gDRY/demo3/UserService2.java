package b.priciple.theory.gDRY.demo3;

public class UserService2 {

    private UserRepo2 userRepo;

    public User login(String email, String password) {
        if (!EmailValidation.validate(email)) {
            //...
        }
        if (!PasswordValidation.validate(password)) {
            //...
        }
        User user = userRepo.getUserByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            //...
        }
        return user;
    }

}
