package ra.presentation;

import ra.business.util.InputMethods;
import ra.business.util.Validate;

public class Login {
    public void login(){

        while (true) {
            System.out.println("Email :");
            String email = InputMethods.getString();
            if(Validate.checkEmail(email)){
                break;
            }
        }
        while (true) {
            System.out.println("Password :");
            String password = InputMethods.getString();
            if(Validate.checkPassword(password)){
                break;
            }
            System.err.println(Validate.ADMIN_PASSWORD_ERROR);
        }
    }
}
