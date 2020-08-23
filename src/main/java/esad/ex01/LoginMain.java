package esad.ex01;

import java.text.MessageFormat;

/**
 * @author ashan on 2020-08-16
 */
class Login {
    private static Login instance;

    private Login() {
        System.out.println("Login Instance Created");
    }

    public static Login getInstance() {
        if (instance == null) {
            synchronized (Login.class) {
                if (instance == null)
                    instance = new Login();
            }
        }
        return instance;
    }

    public boolean validateUser(String username, String password) {
        return username.equals("Udara") && password.equals("Udara");
    }
}

public class LoginMain {
    public static void main(String[] args) {
        Login login1 = Login.getInstance();
        Login login2 = Login.getInstance();

        System.out.println(MessageFormat.format("Validating User: [{0}] Password: [{1}] Authenticated: [{2}]",
                "Udara", "Udara", login1.validateUser("Udara", "Udara")));

        System.out.println(MessageFormat.format("Validating User: [{0}] Password: [{1}] Authenticated: [{2}]",
                "Ashan", "Ashan", login2.validateUser("Ashan", "Ashan")));
    }
}
