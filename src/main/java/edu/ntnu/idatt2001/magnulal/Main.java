package edu.ntnu.idatt2001.magnulal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class that inherits from Application
 * The class has a start-method that shows the given stage
 * @author magnulal
 * @version 2022-03-30 TODO update
*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/edu.ntnu.idatt2001.magnulal/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Wargames");
            stage.setScene(scene);
            stage.show();
        }catch (IOException i){
            i.printStackTrace();
        }
    }
    /**
     * Static void main-method to launch the Application
     * @param args, list of arguments for the static void method
    */
    public static void main(String[] args) {
        launch();
    }

}
