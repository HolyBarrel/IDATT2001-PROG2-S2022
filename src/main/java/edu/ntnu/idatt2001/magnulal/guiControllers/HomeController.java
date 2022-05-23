package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.utils.ActiveArmies;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import edu.ntnu.idatt2001.magnulal.utils.SceneManager;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * Home screen controller for the Wargames application
 * @author magnulal
 * @version 1.0
 * @since 0.3
 */
public class HomeController implements Initializable {
    //Source: https://stackoverflow.com/questions/14256588/opening-a-javafx-filechooser-in-the-user-directory,
    // 05.05.2022
    private static final FileChooser currentFileChooser = new FileChooser();
    private static final FileChooser.ExtensionFilter currentExtFilter =
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
    private Button btnLoadArmy1; //TODO:remove
    @FXML
    private Button btnLoadArmy2;
    @FXML
    private Label sumUnitsArmy2;
    @FXML
    private Label sumUnitsArmy1;
    @FXML
    private Button btnEditArmy1;
    @FXML
    private Button btnEditArmy2;

    /**
     * Runs on initialization of the graphical user interface to specify pre-defined data
     * for the application. If the active armies has not already been set, the
     * {@link FileManager#writeArmyToFileWFile(File, Army)} method is used to write the backup armies to the:
     * src/main/resources/edu.ntnu.idatt2001.magnulal/csv directory.
     * The graphical representation of the active armies are also updated.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        currentTerrain.setText(fetchActiveTerrain());

        if(ActiveArmies.getActiveArmy1() == null && ActiveArmies.getActiveArmy2() == null) {
            try {
                FileManager.writeArmyToFileWFile(new File("src/main/resources/edu.ntnu.idatt2001." +
                        "magnulal/csv/Alliance.csv"), FileManager.readArmyFromFullFilePath("src/main/resources/" +
                        "edu.ntnu.idatt2001.magnulal/csvBackup/Alliance.csv"));
                FileManager.writeArmyToFileWFile(new File("src/main/resources/edu.ntnu.idatt2001." +
                        "magnulal/csv/Horde.csv"), FileManager.readArmyFromFullFilePath("src/main/resources/" +
                        "edu.ntnu.idatt2001.magnulal/csvBackup/Horde.csv"));
                ActiveArmies.setActiveArmy1(FileManager.readArmyFromFile("Alliance"));
                ActiveArmies.setActiveArmy2(FileManager.readArmyFromFile("Horde"));
            } catch (IOException e) {
                exMsg.setText(e.getMessage());
            }
        }
        updateWArmies(ActiveArmies.getActiveArmy1(),ActiveArmies.getActiveArmy2());
    }

    /**
     * Updates the armies of the application to the graphical representation in the GUI and sets the
     * active armies. See {@link ActiveArmies}.
     * @param army1 is the first army for the simulation
     * @param army2 is the second army for the simulation
     */
    private void updateWArmies(Army army1, Army army2){
        ActiveArmies.setActiveArmy1(army1);
        ActiveArmies.setActiveArmy2(army2);
        ActiveArmies.setActiveArmy1Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                ActiveArmies.getActiveArmy1().getName() + ".csv");
        ActiveArmies.setActiveArmy2Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                ActiveArmies.getActiveArmy2().getName() + ".csv");
        lblPathArmy1.setText("Path: " + ActiveArmies.getActiveArmy1Path());
        lblPathArmy2.setText("Path: " + ActiveArmies.getActiveArmy2Path());
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
     */
    @FXML
    public void rotateTerrainRight(){
        currentTerrain.setText(rotateActiveTerrain(1));
    }

    /**
     * Rotates the currently chosen terrain for battle in the application when the left-arrow button is
     * clicked by the user.
     * Occurs at the click event of the button: {@link HomeController#btnTerrainLeft}.
     */
    @FXML
    public void rotateTerrainLeft(){
        currentTerrain.setText(rotateActiveTerrain(-1));
    }

    /**
     * Retrieves the currently active terrain which is set by the application in string-form
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
     */
    @FXML
    public void initiateSimulation(){
        if(ActiveArmies.getActiveArmy1().hasUnits() && ActiveArmies.getActiveArmy2().hasUnits()) {
            try {
                SceneManager.switchView("battleScreen");
            } catch (IOException e) {
                exMsg.setText(e.getMessage());
            }
        }else{
            if(!ActiveArmies.getActiveArmy1().hasUnits()){
                exMsg.setText("The army called: " + ActiveArmies.getActiveArmy1().getName() + " " +
                        "did not contain any units, and could not participate in battle.");
            }else{
                exMsg.setText("The army called: " + ActiveArmies.getActiveArmy2().getName() + " " +
                        "did not contain any units, and could not participate in battle.");
            }
        }
    }

    /**
     * Loads the second army from an existing file using a file chooser through the method:
     * {@link HomeController#openFileExplorer()} and stores the read army-data in {@link ActiveArmies}.
     * Also updates the stats for the chosen army in visually in the graphical user interface.
     * Occurs at the click event of the button: {@link HomeController#btnLoadArmy2}
     */
    @FXML
    public void loadArmy2() {
        try{
            File file = openFileExplorer();
            ActiveArmies.setActiveArmy2(FileManager.readArmyFromExistingFile(file));
            ActiveArmies.setActiveArmy2Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                    ActiveArmies.getActiveArmy2().getName() + ".csv");
            lblPathArmy2.setText("Path: " + ActiveArmies.getActiveArmy2Path());
            setStatsArmy2();
        }catch (NullPointerException | BlankStringException | NegativeIntegerException | NumberFormatException e){
            exMsg.setText("Load-error:" + e.getMessage());
        }
    }

    /**
     * Loads the first army from an existing file using a file chooser through the method:
     * {@link HomeController#openFileExplorer()} and stores the read army-data in {@link ActiveArmies}.
     * Also updates the stats for the chosen army in visually in the graphical user interface.
     * Occurs by a click event of the button: {@link HomeController#btnLoadArmy1}
     */
    @FXML
    public void loadArmy1() {
        try{
            File file = openFileExplorer();
            ActiveArmies.setActiveArmy1(FileManager.readArmyFromExistingFile(file));
            ActiveArmies.setActiveArmy1Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                    ActiveArmies.getActiveArmy1().getName() + ".csv");
            lblPathArmy1.setText("Path: " + ActiveArmies.getActiveArmy1Path());
            setStatsArmy1();
        }catch (NullPointerException | BlankStringException | NegativeIntegerException | NumberFormatException e){
            exMsg.setText("Load-error:" + e.getMessage());
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
    private File openFileExplorer() throws NullPointerException{
        //Source: https://stackoverflow.com/questions/14256588/opening-a-javafx-filechooser-in-the-user-directory,
        // 05.05.2022
        currentFileChooser.getExtensionFilters().add(currentExtFilter);
        currentFileChooser.setInitialDirectory(new File(FileManager.constructHomePath()));
        File targetFile = currentFileChooser.showOpenDialog(null);
        String path;
        if(targetFile != null) {
            path = targetFile.getPath();
        } else {
            throw new NullPointerException("No file was selected for the chosen army, please try again.");
        }
        return targetFile;
    }

    /**
     * Resets the first army to its original state by reading the active path which is associated with that army.
     * The stats for the first army is visually displayed by the application.
     * Occurs at the click event of the button: {@link HomeController#btnResetArmy1}
     */
    @FXML
    public void resetArmy1(){
        try {
            ActiveArmies.setActiveArmy1(FileManager.readArmyFromFullFilePath(ActiveArmies.getActiveArmy1Path()));
            setStatsArmy1();
        }catch (FileNotFoundException f){
            exMsg.setText(f.getMessage());
        }
    }

    /**
     * Resets the second army to its original state by reading the active path which is associated with that army.
     * The stats for the second army is visually displayed by the application.
     * Occurs at the click event of the button: {@link HomeController#btnResetArmy2}
     */
    @FXML
    public void resetArmy2(){
        try{
            ActiveArmies.setActiveArmy2(FileManager.readArmyFromFullFilePath(ActiveArmies.getActiveArmy2Path()));
            setStatsArmy2();
        }catch(FileNotFoundException f){
            exMsg.setText(f.getMessage());
        }

    }

    /**
     * Opens a JOptionPane with a scrollable pane showing the to-string of the second active army.
     * Occurs on button click of: {@link HomeController#btnSeeArmy2}.
     */
    @FXML
    public void seeArmy2() {
        //Source: https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function, 06.05.22
        JTextArea armyInformation = new JTextArea(ActiveArmies.getActiveArmy2().toString());
        JScrollPane scrollInfoPane = new JScrollPane(armyInformation);
        scrollInfoPane.setPreferredSize( new Dimension( 590, 611));
        JOptionPane.showMessageDialog(null, scrollInfoPane, "Selected army:",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Opens a JOptionPane with a scrollable pane showing the to-string of the first active army. Occurs on button click of:
     * {@link HomeController#btnSeeArmy1}.
     */
    @FXML
    public void seeArmy1() {
        //Source: https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function, 06.05.22
        JTextArea armyInformation = new JTextArea(ActiveArmies.getActiveArmy1().toString());
        JScrollPane scrollInfoPane = new JScrollPane(armyInformation);
        scrollInfoPane.setPreferredSize( new Dimension( 590, 611));
        JOptionPane.showMessageDialog(null, scrollInfoPane, "Selected army:",
                JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Switches to the editor.fxml scene using the {@link SceneManager#switchView(String)} method. If the horde army's
     * file is not located in the csv directory, the default horde-army read from the csvBackup directory is used to
     * write the horde army to the csv directory. This is a solution to counter any accidental deletion of the
     * default horde army.
     * This process utilizes methods of the {@link FileManager} to write and read.
     */
    @FXML
    public void editArmy2() {
        if(!new File("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Horde.csv").exists()) {
            try {
                FileManager.writeArmyToFileWFile(new File(
                        "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Horde.csv"),
                        FileManager.readArmyFromFullFilePath(
                                "src/main/resources/edu.ntnu.idatt2001.magnulal/csvBackup/Horde.csv"));
            } catch (IOException e) {
                exMsg.setText(e.getMessage());
            }
        }
        try {
            SceneManager.switchView("editor");
        } catch (IOException i) {
            exMsg.setText(i.getMessage());
        }
    }

    /**
     * Switches to the editor.fxml scene using the {@link SceneManager#switchView(String)} method. If the alliance
     * army's file is not located in the csv directory, the default alliance-army read from the csvBackup directory is
     * used to write the alliance army to the csv directory. This is a solution to counter any accidental deletion of the
     * default alliance army. This process utilizes methods of the {@link FileManager} to write and read.
     */
    @FXML
    public void editArmy1() {
        if(!new File("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Alliance.csv").exists()) {
            try {
                FileManager.writeArmyToFileWFile(
                        new File("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Alliance.csv"),
                        FileManager.readArmyFromFullFilePath("src/main/resources/edu.ntnu.idatt2001.magnulal/" +
                                "csvBackup/Alliance.csv"));
            } catch (IOException e) {
                exMsg.setText(e.getMessage());
            }
        }
        try {
            SceneManager.switchView("editor");
        } catch (IOException i) {
            exMsg.setText(i.getMessage());
        }
    }
}

