import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditController {

    Animal editingAnimal = null;
    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker birthdateField;

    @FXML
    private TextField breedField;

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
    FlowPane root;

    @FXML
    void initialize() {
        System.out.println("initialize editController");
    }

    public void initData(Animal selectedAnimal) {
        System.out.println("initData");
        editingAnimal = selectedAnimal;
        if (editingAnimal != null) {
            nameField.setText(editingAnimal.getName());
            nicknameField.setText(editingAnimal.getNickname());
            String dateStr = editingAnimal.getBirthdate();
            System.out.println(dateStr + " " + dateStr.substring(0, 4) + " " + dateStr.substring(5, 7) + " " + dateStr.substring(8));
            try {
                int year = Integer.parseInt(dateStr.substring(0, 4));
                int month = Integer.parseInt(dateStr.substring(5, 7));
                int day = Integer.parseInt(dateStr.substring(8));
                System.out.println(year + " " + month + " " + day);
                birthdateField.setValue(LOCAL_DATE(dateStr));
            } catch (Exception e) {
                System.out.println("какой-то пипец с датой, удачи)");
            }

            breedField.setText(editingAnimal.getBreed());
            costField.setText(String.valueOf(editingAnimal.getCost()));
            colorField.setText(editingAnimal.getColor());
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onSave(ActionEvent actionEvent) {
        try {
            LocalDate date = birthdateField.getValue();
            String dateStr = (date.getYear()) + "-" + (date.getMonthValue()) + "-" + date.getDayOfMonth();
            System.out.println(dateStr);
            editingAnimal.setBirthdate(dateStr);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Неправильно заполнено поле даты рождения, введите корректную дату в формате гггг-мм-дд.");
            alert.showAndWait();
        }

        editingAnimal.setBreed(breedField.getText());
        editingAnimal.setColor(colorField.getText());
        editingAnimal.setNickname(nicknameField.getText());
        editingAnimal.setName(nameField.getText());
        try {
            Double cost = Double.valueOf(costField.getText());
            editingAnimal.setCost(cost);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Неправильно заполнено поле цены, введите корректную цену.");
            alert.showAndWait();
        }
        //TODO - Изменение записи
        AnimalManager.updateAnimal(editingAnimal);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}
