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

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * Home screen controller for the Wargames application //TODO: COMMENT ALL
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

    @Deprecated
    public void initialize() throws FileNotFoundException { //TODO: handle
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        currentTerrain.setText(fetchActiveTerrain());
        updateDisplayedArmies("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/humanarmy.csv",
                "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orchorde.csv");
    }

    /**
     * TODO: comment

     * @throws FileNotFoundException
     */
    private void updateDisplayedArmies(String pathArmy1, String pathArmy2) throws FileNotFoundException { //TODO: handle
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
     * TODO: comment

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

    private void setStatsArmy2(){
        labelArmy2.setText(ActiveArmies.getActiveArmy2().getName());
        infArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getInfantryUnits().size()));
        cavArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCavalryUnits().size()));
        comArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCommanderUnits().size()));
        ranArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getRangedUnits().size()));
        sumUnitsArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getArmySize()));
        exMsg.setText("");
    }

    @FXML
    public void rotateTerrainRight(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(1));
    }

    @FXML
    public void rotateTerrainLeft(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(-1));
    }

    private String fetchActiveTerrain(){
        return ActiveTerrain.INSTANCE.getActiveTerrain().toString();
    }

    private String rotateActiveTerrain(int rotationValue){
        ActiveTerrain.INSTANCE.rotateTerrain(rotationValue);
        return fetchActiveTerrain();
    }

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

    @FXML
    public void loadArmy2(ActionEvent actionEvent) {
        File file = openFileExplorer();
        ActiveArmies.setActiveArmy2(FileManager.readArmyFromExistingFile(file));
        ActiveArmies.setActiveArmy2Path(file.getAbsolutePath());
        lblPathArmy2.setText("Path: " + file.getAbsolutePath());
        setStatsArmy2();
    }

    @FXML
    public void loadArmy1(ActionEvent actionEvent) { //TODO: MAKE ROBUST
        File file = openFileExplorer();
        ActiveArmies.setActiveArmy1(FileManager.readArmyFromExistingFile(file));
        ActiveArmies.setActiveArmy1Path(file.getAbsolutePath());
        lblPathArmy1.setText("Path: " + file.getAbsolutePath()); //TODO: make not weird
        setStatsArmy1();
    }

    /**
     * TODO: comment
     * @return
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
            path = null;
        }
        return targetFile;
    }

    @FXML
    public void resetArmy1(ActionEvent actionEvent) throws FileNotFoundException { //TODO: handle
        ActiveArmies.setActiveArmy1(FileManager.readArmyFromFullFilePath(ActiveArmies.getActiveArmy1Path())); //TODO: handle
        setStatsArmy1();
    }

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
