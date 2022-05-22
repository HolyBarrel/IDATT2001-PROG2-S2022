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
    /**
     * Starts the application which called Wargames
     * @param stage is the active stage
     */
    @Override
    public void start(Stage stage){
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

    /**
     * Closes the program by first displaying a confirmation alert to the user
     */
    private void closeProgram(){
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("EXITCONFIRMATION");
        closeAlert.setHeaderText("Exit?");
        closeAlert.setContentText("Sure about closing the ongoing instance of Wargames?");
        Optional<ButtonType> feed = closeAlert.showAndWait();
        if(feed.get() == ButtonType.OK) Platform.exit();
    }
    /**
     * Static void main-method to launch the Application
     * @param args, list of arguments for the static void method
    */
    public static void main(String[] args) {
        launch();
    }

}
