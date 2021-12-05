import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    ObservableList<Animal> animals = FXCollections.observableArrayList();
    Animal selectedAnimal = null;
    @FXML
    private TableView<Animal> table;
    @FXML
    private TableColumn<Animal, String> birthCol;
    @FXML
    private TableColumn<Animal, String> colorCol;
    @FXML
    private TableColumn<Animal, String> breedCol;

    @FXML
    private TableColumn<Animal, Integer> costCol;

    @FXML
    private TableColumn<Animal, Integer> idCol;

    @FXML
    private TableColumn<Animal, String> nameCol;

    @FXML
    private TableColumn<Animal, String> nicknameCol;

    @FXML
    private Label welcomeText;
    private Object ActionEvent;

    @FXML
    private void initialize() {
        animals = AnimalManager.readAnimals();
        idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        nicknameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("nickname"));
        costCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("cost"));
        breedCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("breed"));
        colorCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("color"));
        birthCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("birthdate"));
        // ObservableList<Animal> list=null; list.addAll(animals);
        table.setItems(animals);
        TableView.TableViewSelectionModel<Animal> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Animal>() {
            @Override
            public void changed(ObservableValue<? extends Animal> observableValue, Animal animal, Animal t1) {
                if (t1 != null) {
                    System.out.println(t1.getNickname());
                    selectedAnimal = t1;
                } else System.out.println("НЕ ВЫБРАН ЭЛЕМЕНТ!");
            }
        });
    }

    @FXML
    protected void onRefresh() {
        animals = AnimalManager.readAnimals();
        table.setItems(animals);
    }

    @FXML
    protected void onDelete() {
        if (selectedAnimal != null) {
            AnimalManager.deleteAnimal(selectedAnimal);
            animals.remove(selectedAnimal);
            table.setItems(animals);
            selectedAnimal = null;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Не выбран элемент для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onEdit() {
        System.out.println("EDIT WORK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
        //loader.setLocation();
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditController editController = loader.getController();
        editController.initData(selectedAnimal);
        stage.showAndWait();
        onRefresh();
    }

    @FXML
    protected void onAdd() {
        System.out.println("ADD WORK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        //loader.setLocation();
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddController editController = loader.getController();
        stage.showAndWait();
        onRefresh();
    }
}