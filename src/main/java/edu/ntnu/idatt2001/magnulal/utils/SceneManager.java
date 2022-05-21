package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to manage the flow of the scenes in the application
 * @author magnulal
 * @version 1.0
 * @since 0.3
 */
public class SceneManager {
    private static Scene activeScene;
    private static Stage activeStage;

    /**
     * Private constructor to limit instantiation of objects of this class.
     * This is due to SceneManager being used for its static methods only.
     */
    private SceneManager(){}

    /**
     * Switches the active scene which is viewed by the user of the application
     * @param targetFxml is the file name of the target scene-fxml file located in project resources
     * @throws IOException on problems with loading the FXMLLoader
     */
    public static void switchView(String targetFxml) throws IOException {
        FXMLLoader loader = retrieveLoader(targetFxml);
        activeScene.setRoot(loader.load());
    }

    /**
     * Retrieves the FXMLLoader for a fxml file at the constructed file path. This path is created by utilizing the
     * fileName string
     * @param fileName is a string for the file name of the fxml file which is supposed to be loaded
     * @return a FXMLLoader
     */
    public static FXMLLoader retrieveLoader(String fileName){
        String path = String.format("/edu.ntnu.idatt2001.magnulal/fxml/%s.fxml", fileName);
        return new FXMLLoader(Main.class.getResource(path));
    }

    /**
     * Returns the scene which is the active one presented to the user
     * @return Scene activeScene seen in the GUI
     */
    public static Scene getActiveScene() {
        return activeScene;
    }

    /**
     * Sets the parameter scene as the active scene of the application
     * @param targetScene is the new scene which is set as active
     */
    public static void setActiveScene(Scene targetScene) {
        SceneManager.activeScene = targetScene;
    }

}
