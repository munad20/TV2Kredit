package dk.grp15.krediteringssystem.presentation;

import dk.grp15.krediteringssystem.logic.credits.Contributor;
import dk.grp15.krediteringssystem.logic.user.Permission;
import dk.grp15.krediteringssystem.logic.user.SystemAdministrator;
import dk.grp15.krediteringssystem.logic.user.User;
import dk.grp15.krediteringssystem.persistence.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class Main extends Application{

    private static int width = 1600;
    private static int height = 900;

    private static User currentUser;

    private static Stage systemStage;
    private static Stage loginStage;

    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D bounds = screen.getVisualBounds();



    @Override
    public void start(Stage primaryStage) throws IOException {
        systemStage = new Stage();
        loginStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent login = loadFXML("login.fxml", fxmlLoader);
        LoginController loginController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader();
        Parent production = loadFXML("production.fxml", fxmlLoader);
        ProductionController productionController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader();
        Parent contributor = loadFXML("contributor.fxml", fxmlLoader);
        ContributorController contributorController = fxmlLoader.getController();

        productionController.setVariables(contributor, systemStage, contributorController);
        contributorController.setVariables(production, systemStage, productionController);

        Scene loginScene = new Scene(login, 800, 600);
        Scene scene = new Scene(production, width, height);

        systemStage.setScene(scene);

        loginStage.setResizable(false);
        loginStage.setTitle("Krediteringssystem");
        loginStage.setX(bounds.getWidth() / 2 - 800 / 2);
        loginStage.setY(bounds.getHeight() / 2 - 600 / 2);

        loginStage.setScene(loginScene);
        loginStage.show();
    }

    private Parent loadFXML(String file, FXMLLoader fxmlLoader) throws IOException {
        fxmlLoader.setLocation(this.getClass().getResource("/" + file));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void changeStage() {
        systemStage.setTitle("Krediteringssystem");
        systemStage.setX(bounds.getWidth() / 2 - width / 2);
        systemStage.setY(bounds.getHeight() / 2 - height / 2);
        systemStage.setMaximized(true);
        loginStage.close();
        systemStage.show();
    }

    /*public static void main(String[] args) {
        User tempUser = new SystemAdministrator(1, Permission.SYSADMIN);

        tempUser.createProduction("Test", 1, 1, new Date());
        System.out.println(Catalogue.getInstance().getProductions().get(0));
    }*/
}
