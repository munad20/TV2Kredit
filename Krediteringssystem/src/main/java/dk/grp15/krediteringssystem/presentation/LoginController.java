package dk.grp15.krediteringssystem.presentation;

import dk.grp15.krediteringssystem.logic.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void login() {
        User currentUser = User.login(username.getText(), password.getText());
        if (currentUser != null) {
            Main.setCurrentUser(currentUser);
            Main.changeStage();
        }
        else {
            System.out.println("Incorrect username or password");
        }
    }

}
