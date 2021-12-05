import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker birthdateField;

    @FXML
    private TextField breedField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField colorField;

    @FXML
    private TextField costField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameView;

    @FXML
    private TextField nicknameField;
    Animal newAnimal;

    @FXML
    void AddButton(ActionEvent event) {
        newAnimal = new Animal();
        if (nameField.getText().trim().equals("") || nicknameField.getText().trim().equals("")
                || costField.getText().trim().equals("") || breedField.getText().trim().equals("")
                || birthdateField.getValue()==null||colorField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Есть пустое поле");
            alert.showAndWait();
            return;
        }
        newAnimal.setName(nameField.getText().trim());
        newAnimal.setNickname(nicknameField.getText());
        try {
            Double cost = Double.valueOf(costField.getText());
            newAnimal.setCost(cost);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Неправильно заполнено поле цены, введите корректную цену.");
            alert.showAndWait();
            return;
        }
        newAnimal.setBreed(breedField.getText());

        try {
            LocalDate date = birthdateField.getValue();
            String dateStr = (date.getYear()) + "-" + (date.getMonthValue()) + "-" + date.getDayOfMonth();
            System.out.println(dateStr);
            newAnimal.setBirthdate(dateStr);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Неправильно заполнено поле даты");
            alert.showAndWait();
            return;
        }

        newAnimal.setColor(colorField.getText());
        AnimalManager.addAnimal(newAnimal);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

    }


    public void CancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

