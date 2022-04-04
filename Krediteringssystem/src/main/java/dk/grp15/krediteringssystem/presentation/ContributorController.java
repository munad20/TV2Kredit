package dk.grp15.krediteringssystem.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ContributorController {

    private Parent controller;
    private Stage stage;
    private ProductionController productionController;

    @FXML
    private TextField search;

    @FXML
    private TextField title;

    @FXML
    private TextArea resume;

    @FXML
    private VBox editor = new VBox();

    public void initialize() {
    }

    public void onNewProduction() {
        stage.getScene().setRoot(controller);
        productionController.getSearch().setText(search.getText());
    }

    public void onNewContributor() {
        
    }

    public void onNewUser() {
        Parent root;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/newUser.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ny Bruger");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSave() {

    }

    public void setVariables(Parent controller, Stage stage, ProductionController productionController) {
        this.controller = controller;
        this.stage = stage;
        this.productionController = productionController;
    }

    public TextField getSearch() {
        return search;
    }

}