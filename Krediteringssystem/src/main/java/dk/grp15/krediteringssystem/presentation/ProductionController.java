package dk.grp15.krediteringssystem.presentation;

import dk.grp15.krediteringssystem.logic.credits.Contributor;
import dk.grp15.krediteringssystem.logic.credits.Production;
import dk.grp15.krediteringssystem.logic.credits.Role;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductionController {

    private Parent controller;
    private Stage stage;
    private ContributorController contributorController;

    private List<Contributor> contributors = new ArrayList<>();

    private Production currentProduction = new Production("", 0, 0, null, 0);

    @FXML
    private TextField search;

    @FXML
    private TextField title;

    @FXML
    private TextField lengthHours;

    @FXML
    private TextField lengthMinutes;

    @FXML
    private DatePicker releaseDate;

    @FXML
    private TextArea resume;

    @FXML
    private TableView<Contributor> table = new TableView<>();

    @FXML
    private TableColumn<Contributor, Role> role = new TableColumn<>("Rolle");

    @FXML
    private TableColumn<Contributor, String> name = new TableColumn<>("Medvirkende");

    @FXML
    private TableColumn<Contributor, String> character = new TableColumn<>("Karakter");

    @FXML
    private VBox editor = new VBox();

    public void initialize() {
        contributors.add(new Contributor());
        table.setEditable(true);
        table.getItems().setAll(contributors);

        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        role.setCellFactory(ChoiceBoxTableCell.forTableColumn(Role.values()));
        role.setOnEditCommit(event -> {
            Contributor contributor = event.getRowValue();
            contributor.setRole(event.getNewValue());
            updateTable();
        });

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(event -> {
            Contributor contributor = event.getRowValue();
            contributor.setName(event.getNewValue());
            updateTable();
        });

        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        character.setCellFactory(TextFieldTableCell.forTableColumn());
        character.setOnEditCommit(event -> {
            /*System.out.println(event.getNewValue());
            if (table.getItems().get(event.getTablePosition().getRow()).getRole().equals(Role.ACTOR)) {
                System.out.println(event.getTablePosition().getRow());
                currentProduction.addCharacter(table.getItems().get(event.getTablePosition().getRow()).getId(), event.getNewValue());
            }
            else {
                System.out.println(character.getCellObservableValue(event.getTablePosition().getRow()));
            }*/
            Contributor contributor = event.getRowValue();
            if (table.getItems().get(event.getTablePosition().getRow()).getRole().equals(Role.ACTOR)) {
                contributor.setCharacter(event.getNewValue());
            }
            else {
                contributor.setCharacter("");
            }
            updateTable();
        });

        /*table.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (table.getItems().get(table.getItems().size() - 1).getName().isEmpty() && table.getItems().get(table.getItems().size() - 1).getRole().equals(Role.DEFAULT_ROLE)) {
                for (int i = 0; i < table.getItems().size() - 1; i++) {
                    if (table.getItems().get(i).getName().isEmpty() && table.getItems().get(i).getRole().equals(Role.DEFAULT_ROLE)) {
                        contributors.remove(i);
                        table.getItems().setAll(contributors);
                    }
                }
            }
            else {
                contributors.add(new Contributor());
                table.getItems().setAll(contributors);
            }

        });*/
    }

    public void updateTable() {
        if (table.getItems().get(table.getItems().size() - 1).getName().isEmpty() && table.getItems().get(table.getItems().size() - 1).getRole().equals(Role.DEFAULT_ROLE)) {
            for (int i = table.getItems().size() - 1; i > 0; i--) {
                if (table.getItems().get(i).getName().isEmpty() && table.getItems().get(i).getRole().equals(Role.DEFAULT_ROLE)) {
                    contributors.remove(i);
                    table.getItems().setAll(contributors);
                }
            }
        }
        else {
            contributors.add(new Contributor());
            table.getItems().setAll(contributors);
        }
    }

    public void onNewProduction() {
        title.setText("");
        lengthHours.setText("");
        lengthMinutes.setText("");
        releaseDate.setValue(null);
        resume.setText("");
    }

    public void onNewContributor() {
        stage.getScene().setRoot(controller);
        contributorController.getSearch().setText(search.getText());
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
        LocalDate localDate = releaseDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        List<Contributor> members = new ArrayList<>(table.getItems());

        Production currentProduction = new Production(title.getText(), Integer.parseInt(lengthHours.getText()), Integer.parseInt(lengthMinutes.getText()), date, 1/*, resume.getText(), true, members*/);

        System.out.println(currentProduction.toString());
    }

    public void setVariables(Parent controller, Stage stage, ContributorController contributorController) {
        this.controller = controller;
        this.stage = stage;
        this.contributorController = contributorController;
    }

    public TextField getSearch() {
        return search;
    }
}