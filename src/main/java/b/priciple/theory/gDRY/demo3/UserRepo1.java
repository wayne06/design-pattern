package b.priciple.theory.gDRY.demo3;

public class UserRepo1 {
    public boolean checkIfUserExisted(String email, String password) {
        if (!EmailValidation.validate(email)) {
            //...
        }

        if (!PasswordValidation.validate(password)) {
            //...
        }

        // query db to check if email & password exists...
        return false;
    }

    public User getUserByEmail(String email) {
        if (!EmailValidation.validate(email)) {
            //...
        }

        // query db to get user by email...
        return null;
    }
}
