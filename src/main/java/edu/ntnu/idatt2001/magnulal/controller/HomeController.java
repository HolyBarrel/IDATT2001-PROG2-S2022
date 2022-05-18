package edu.ntnu.idatt2001.magnulal.controller;

import edu.ntnu.idatt2001.magnulal.model.simulator.Battle;
import edu.ntnu.idatt2001.magnulal.utils.ActiveArmies;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
//todo: modality

import javax.naming.directory.InvalidAttributesException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * Home screen controller for the Wargames application
 * @author magnulal
 * @version 0.3
 * @since 0.3
 */
public class HomeController {
    //Source: https://stackoverflow.com/questions/14256588/opening-a-javafx-filechooser-in-the-user-directory,
    // 05.05.2022
    private static FileChooser currentFileChooser = new FileChooser();
    private static FileChooser.ExtensionFilter currentExtFilter =
            new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");


    @FXML
    private Label labelArmy2;
    @FXML
    private Label labelArmy1;
    @FXML
    private Label exMsg;
    @FXML
    private Label comArmy1;
    @FXML
    private Button btnSimBattle;
    @FXML
    private Button btnTerrainRight;
    @FXML
    private Label comArmy2;
    @FXML
    private Label cavArmy1;
    @FXML
    private Label cavArmy2;
    @FXML
    private Label infArmy1;
    @FXML
    private Label infArmy2;
    @FXML
    private Label ranArmy1;
    @FXML
    private Label ranArmy2;
    @FXML
    private Label currentTerrain;
    @FXML
    private Label lblPathArmy2;
    @FXML
    private Button btnTerrainLeft;
    @FXML
    private Label lblPathArmy1;
    @FXML
    private Button btnResetArmy2;
    @FXML
    private Button btnResetArmy1;
    @FXML
    private Button btnSeeArmy1;
    @FXML
    private Button btnSeeArmy2;
    @FXML
    private Button btnLoadArmy1;
    @FXML
    private Button btnLoadArmy2;
    @FXML
    private Label sumUnitsArmy2;
    @FXML
    private Label sumUnitsArmy1;

    /**
     * Runs on initialization of the graphical user interface to specify pre-defined data
     * for the application.
     */
    @FXML
    public void initialize() throws FileNotFoundException { //TODO: handle
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        currentTerrain.setText(fetchActiveTerrain());
        try {
            updateDisplayedArmies("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/humanarmy.csv",
                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orchorde.csv");
        } catch (InvalidAttributesException e) {
            exMsg.setText(e.getMessage());
        }
    }

    /**
     * Updates the armies displayed by the graphical user interface
     * @param pathArmy1 is the file path to the army which is graphically represented as army one by the
     *                  application
     * @param pathArmy2 is the file path to the army which is graphically represented as army two by the
     *                  application
     * @throws InvalidPathException if the {@link FileManager#readArmyFromFullFilePath(String)}-method cannot utilize
     * the given path because of illegal syntax
     * @throws FileNotFoundException if the {@link FileManager#readArmyFromFullFilePath(String)}-method cannot locate
     * a file at the specified path
     * @throws InvalidAttributesException if the {@link FileManager#readArmyFromFullFilePath(String)}-method attempts to
     * read an army without syntactically correct values. //todo double check
     */
    private void updateDisplayedArmies(String pathArmy1, String pathArmy2) throws FileNotFoundException,
            InvalidPathException, InvalidAttributesException { //TODO: handle
        ActiveArmies.setActiveArmy1(FileManager.readArmyFromFullFilePath(pathArmy1));
        ActiveArmies.setActiveArmy1Path(pathArmy1);
        ActiveArmies.setActiveArmy2(FileManager.readArmyFromFullFilePath(pathArmy2));
        ActiveArmies.setActiveArmy2Path(pathArmy2);
        lblPathArmy1.setText("Path: " + ActiveArmies.getActiveArmy1Path()); //TODO: make text copy-able
        lblPathArmy2.setText("Path: " + ActiveArmies.getActiveArmy2Path()); //TODO: make text copy-able
        setStatsArmy1();
        setStatsArmy2();
    }

    /**
     * Asserts the stats of the first chosen army to the graphical user interface.
     * Resets the exception label text.
     */
    private void setStatsArmy1(){
        labelArmy1.setText(ActiveArmies.getActiveArmy1().getName());
        infArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getInfantryUnits().size()));
        cavArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getCavalryUnits().size()));
        comArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getCommanderUnits().size()));
        ranArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getRangedUnits().size()));
        sumUnitsArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getArmySize()));
        exMsg.setText("");
    }

    /**
     * Asserts the stats of the second chosen army to the graphical user interface.
     * Resets the exception label text.
     */
    private void setStatsArmy2(){
        labelArmy2.setText(ActiveArmies.getActiveArmy2().getName());
        infArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getInfantryUnits().size()));
        cavArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCavalryUnits().size()));
        comArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCommanderUnits().size()));
        ranArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getRangedUnits().size()));
        sumUnitsArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getArmySize()));
        exMsg.setText("");
    }

    /**
     * Rotates the currently chosen terrain for battle in the application when the right-arrow button is
     * clicked by the user.
     * Occurs at the click event of the button: {@link HomeController#btnTerrainRight}.
     * @param actionEvent is the click-event summoned by the user of the program
     */
    @FXML
    public void rotateTerrainRight(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(1));
    }

    /**
     * Rotates the currently chosen terrain for battle in the application when the left-arrow button is
     * clicked by the user.
     * Occurs at the click event of the button: {@link HomeController#btnTerrainLeft}.
     * @param actionEvent is the click-event summoned by the user of the program
     */
    @FXML
    public void rotateTerrainLeft(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(-1));
    }

    /**
     * Retrieves the currently active terrain which is set by the application
     * @return toString of the terrain type which is active
     */
    private String fetchActiveTerrain(){
        return ActiveTerrain.INSTANCE.getActiveTerrain().toString();
    }

    /**
     * Rotates the active terrain enum value set as the active value based on a directionally deciding integer.
     * This event is instantiated by the {@link HomeController#btnTerrainLeft}- or the
     * {@link HomeController#btnTerrainLeft}-button.
     * @param rotationValue is the integer value which decides which direction the active terrain enum is rotated
     * @return a string representing the active terrain
     */
    private String rotateActiveTerrain(int rotationValue){
        ActiveTerrain.INSTANCE.rotateTerrain(rotationValue);
        return fetchActiveTerrain();
    }

    /**
     * Initiates the simulation between the two armies set as active in the application.
     * Updates the stats for the two armies displayed post battle.
     * Displays an exception if the two armies does not satisfy the requirements for initiation of a simulation.
     * This event is instantiated by the {@link HomeController#btnSimBattle} button.
     * @param actionEvent is the click-event occurring when the btnSimBattle is initiated
     */
    @FXML
    public void initiateSimulation(ActionEvent actionEvent){ //TODO: ex handling
        try {
            Battle battle = new Battle(ActiveArmies.getActiveArmy1(), ActiveArmies.getActiveArmy2(), ActiveTerrain.INSTANCE.getActiveTerrain());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Battle result");
            alert.setHeaderText("The victorious army was: " + battle.simulate().getName());
            setStatsArmy1();
            setStatsArmy2();
            alert.showAndWait();
        }catch (IllegalArgumentException i){
            exMsg.setText(i.getMessage());
        }
    }

    /**
     * Loads the second army from an existing file using a file chooser through the method:
     * {@link HomeController#openFileExplorer()} and stores the read army-data in {@link ActiveArmies}.
     * Also updates the stats for the chosen army in visually in the graphical user interface.
     * Occurs at the click event of the button: {@link HomeController#btnLoadArmy2}
     * @param actionEvent is the click event which is triggering this method
     */
    @FXML
    public void loadArmy2(ActionEvent actionEvent) {
        try{
            File file = openFileExplorer();
            ActiveArmies.setActiveArmy2(FileManager.readArmyFromExistingFile(file));
            ActiveArmies.setActiveArmy2Path(file.getAbsolutePath());
            lblPathArmy2.setText("Path: " + file.getAbsolutePath());
            setStatsArmy2();
        }catch (NullPointerException n){ //TODO. improve
            exMsg.setText(n.getMessage());
        }
    }

    /**
     * Loads the first army from an existing file using a file chooser through the method:
     * {@link HomeController#openFileExplorer()} and stores the read army-data in {@link ActiveArmies}.
     * Also updates the stats for the chosen army in visually in the graphical user interface.
     * Occurs by a click event of the button: {@link HomeController#btnLoadArmy1}
     * @param actionEvent is the click event which is triggering this method
     */
    @FXML
    public void loadArmy1(ActionEvent actionEvent) {
        try{
            File file = openFileExplorer();
            ActiveArmies.setActiveArmy1(FileManager.readArmyFromExistingFile(file));
            ActiveArmies.setActiveArmy1Path(file.getAbsolutePath());
            lblPathArmy1.setText("Path: " + file.getAbsolutePath()); //TODO: make not weird
            setStatsArmy1();
        }catch (NullPointerException n){
            exMsg.setText(n.getMessage());
        }
    }

    /**
     * Returns a file chosen by the user of the application. Achieves this by presenting the user with a FileChooser
     * with a filter for '.csv' files. This FileChooser is being presented at the user home path or as the system home
     * path.
     * @return the file the user has chosen from the FileChooser
     * @throws NullPointerException if the load event did not correctly retrieve an army from a file. Occurs on exit
     * of the FileChooser
     */
    private File openFileExplorer(){ //TODO: Exception handling
        //Source: https://stackoverflow.com/questions/14256588/opening-a-javafx-filechooser-in-the-user-directory,
        // 05.05.2022
        currentFileChooser.getExtensionFilters().add(currentExtFilter);
        currentFileChooser.setInitialDirectory(new File(FileManager.constructHomePath()));
        File targetFile = currentFileChooser.showOpenDialog(null);
        String path;
        if(targetFile != null) {
            path = targetFile.getPath();
        } else {
            throw new NullPointerException("No file was selected for the chosen army, please try again."); //TODO: test
        }
        return targetFile;
    }

    /**
     * Resets the first army to its original state by reading the active path which is associated with that army.
     * The stats for the first army is visually displayed by the application.
     * Occurs at the click event of the button: {@link HomeController#btnResetArmy1}
     * @param actionEvent is the button click-event
     */
    @FXML
    public void resetArmy1(ActionEvent actionEvent) throws FileNotFoundException { //TODO: handle
        ActiveArmies.setActiveArmy1(FileManager.readArmyFromFullFilePath(ActiveArmies.getActiveArmy1Path())); //TODO: handle
        setStatsArmy1();
    }

    /**
     * Resets the second army to its original state by reading the active path which is associated with that army.
     * The stats for the second army is visually displayed by the application.
     * Occurs at the click event of the button: {@link HomeController#btnResetArmy2}
     * @param actionEvent is the button click-event
     */
    @FXML
    public void resetArmy2(ActionEvent actionEvent) throws FileNotFoundException { // TODO: hanlde
        ActiveArmies.setActiveArmy2(FileManager.readArmyFromFullFilePath(ActiveArmies.getActiveArmy2Path()));
        setStatsArmy2();
    }


    @FXML
    public void seeArmy2(ActionEvent actionEvent) {
        //TODO: is under development, this is just a temporary solution to fulfill the minimum requirements for task 3
        //Source: https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function, 06.05.22
        //TODO: revise
        JTextArea armyInformation = new JTextArea(ActiveArmies.getActiveArmy2().toString());
        JScrollPane scrollInfoPane = new JScrollPane(armyInformation);
        scrollInfoPane.setPreferredSize( new Dimension( 900, 611));
        JOptionPane.showMessageDialog(null, scrollInfoPane, "Selected army:",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void seeArmy1(ActionEvent actionEvent) {
        //TODO: is under development, this is just a temporary solution to fulfill the minimum requirements for task 3
        //Source: https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function, 06.05.22
        //TODO: revise
        JTextArea armyInformation = new JTextArea(ActiveArmies.getActiveArmy1().toString());
        JScrollPane scrollInfoPane = new JScrollPane(armyInformation);
        scrollInfoPane.setPreferredSize( new Dimension( 900, 611));
        JOptionPane.showMessageDialog(null, scrollInfoPane, "Selected army:",
                JOptionPane.INFORMATION_MESSAGE);
    }
//TODO: dialog box when quitting
}

