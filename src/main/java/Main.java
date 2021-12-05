import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
//        Animal a = new Animal("t","t", "t", "t", "2021-01-01", 1);
//        AnimalManager.addAnimal(a);
//        List<Animal> l = AnimalManager.readAnimals();
//        for(Animal on:l){
//            System.out.println(on.getNickname());
//        }
//        AnimalManager.deleteAnimal(l.get(6)); l.remove(l.get(6));
//        Animal updateAnimal = l.get(2);
//        updateAnimal.setColor("test");
//        AnimalManager.updateAnimal(updateAnimal);
//        l = AnimalManager.readAnimals();
//        for(Animal on:l){
//            System.out.println(on.getNickname());
//        }
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setScene(scene);
        stage.show();
    }
}
