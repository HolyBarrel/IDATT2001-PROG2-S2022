package edu.ntnu.idatt2001.magnulal.controller;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.simulator.Battle;
import edu.ntnu.idatt2001.magnulal.utils.ActiveArmies;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import edu.ntnu.idatt2001.magnulal.utils.SceneManager;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
//todo: modality
import javax.naming.directory.InvalidAttributesException;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

public class EditorController {
    @FXML
    private Label exMsg;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnHome;
    @FXML
    private TextField comNrArmy1;
    @FXML
    private TextField infNrArmy2;
    @FXML
    private TextField nameArmy2;
    @FXML
    private TextField ranNrArmy2;
    @FXML
    private TextField nameArmy1;
    @FXML
    private TextField ranNrArmy1;
    @FXML
    private TextField cavNrArmy2;
    @FXML
    private TextField cavNrArmy1;
    @FXML
    private TextField infNrArmy1;
    @FXML
    private TextField comNrArmy2;

    @Deprecated
    public void initialize() throws FileNotFoundException { //TODO: handle
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
        setStatsArmy1();
        setStatsArmy2();
    }

    /**
     * Asserts the stats of the first chosen army to the graphical user interface.
     * Resets the exception label text.
     */
    private void setStatsArmy1(){

        nameArmy1.setText(ActiveArmies.getActiveArmy1().getName());
        infNrArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getInfantryUnits().size()));
        cavNrArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getCavalryUnits().size()));
        comNrArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getCommanderUnits().size()));
        ranNrArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getRangedUnits().size()));
        exMsg.setText("");
    }

    /**
     * Asserts the stats of the second chosen army to the graphical user interface.
     * Resets the exception label text.
     */
    private void setStatsArmy2(){
        nameArmy2.setText(ActiveArmies.getActiveArmy2().getName());
        infNrArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getInfantryUnits().size()));
        cavNrArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCavalryUnits().size()));
        comNrArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getCommanderUnits().size()));
        ranNrArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getRangedUnits().size()));
        exMsg.setText("");
    }

    @FXML
    public void returnHome(ActionEvent actionEvent) {
        try {
            SceneManager.switchView("main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveAndReturn(ActionEvent actionEvent) {

        try {
            SceneManager.switchView("main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    private boolean saveArmy1() throws {
        try{
            Army saveArmy = new Army(nameArmy1.getText());

        }
    }
    */


    @FXML
    public void checkIntegerInput(Event event) {
        try{
            exMsg.setText("");
            ArrayList<Integer> numberOfDifferentUnits = new ArrayList<>();
            numberOfDifferentUnits.add(Integer.parseInt(comNrArmy1.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(infNrArmy1.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(cavNrArmy1.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(ranNrArmy1.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(comNrArmy2.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(comNrArmy2.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(infNrArmy2.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(cavNrArmy2.getText()));
            numberOfDifferentUnits.add(Integer.parseInt(ranNrArmy2.getText()));
            if(numberOfDifferentUnits.stream().anyMatch(integer -> integer < 0)){
                throw new NegativeIntegerException("One input is negative, this is not accepted as a legal " +
                        "amount of units.");
            }


        }catch (NegativeIntegerException | NumberFormatException n){
            exMsg.setText("The given input is wrong: " + n.getMessage() + "Could not save to file.");
        }
    }
}
