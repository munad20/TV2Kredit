package dk.grp15.krediteringssystem.presentation;

import dk.grp15.krediteringssystem.logic.user.Permission;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {

    @FXML
    private TextField username = new TextField();

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private ChoiceBox<Permission> permission;

    private Stage stage;

    public void initialize() {
        permission.getItems().setAll(Permission.values());
    }

    public void cancel() {
        stage = (Stage) username.getScene().getWindow();
        stage.close();
    }

    public void save() {
        stage = (Stage) username.getScene().getWindow();
        if (!username.getText().isEmpty() && !password.getText().isEmpty() && !permission.getSelectionModel().isEmpty()) {
            if (password.getText().equals(confirmPassword.getText())) {
                Main.getCurrentUser().createUser(username.getText(), password.getText(), permission.getValue());
                stage.close();
            }
        }

    }
}
