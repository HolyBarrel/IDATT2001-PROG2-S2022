package edu.ntnu.idatt2001.magnulal;

import edu.ntnu.idatt2001.magnulal.utils.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Main class that inherits from Application
 * The class has a start-method that shows the given stage
 * @author magnulal
 * @version 1.0
 * @since 0.3
*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = SceneManager.retrieveLoader("main");
            SceneManager.setActiveScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Wargames");
            stage.setOnCloseRequest(event -> {
                event.consume();
                this.closeProgram();
            });
            stage.setScene(SceneManager.getActiveScene());
            stage.setMinHeight(540);
            stage.setMinWidth(720);
            stage.show();
        }catch (IOException i){
            i.printStackTrace();
        }
    }
    private void closeProgram(){
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Close?");
        closeAlert.setHeaderText("Exit?");
        closeAlert.setContentText("Sure about closing?");
        Optional<ButtonType> result = closeAlert.showAndWait();
        if(result.get() == ButtonType.OK) Platform.exit();
    }
    /**
     * Static void main-method to launch the Application
     * @param args, list of arguments for the static void method
    */
    public static void main(String[] args) {
        launch();
    }

}
