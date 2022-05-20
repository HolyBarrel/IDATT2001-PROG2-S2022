package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.utils.*;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.naming.directory.InvalidAttributesException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import static edu.ntnu.idatt2001.magnulal.utils.UnitTypes.*;

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
            updateDisplayedArmies("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Alliance.csv",
                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/Horde.csv");
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
//TODO. ex.handling on huge armies
    @FXML
    public void saveAndReturn(ActionEvent actionEvent) {
        try{
            exMsg.setText("");
            confirmInputValues();

            Army prevArmy1 = ActiveArmies.getActiveArmy1();
            Army prevArmy2 = ActiveArmies.getActiveArmy2();

            Army newArmy1 = parseToArmy(1, prevArmy1, comNrArmy1, infNrArmy1, cavNrArmy1, ranNrArmy1, nameArmy1);
            if(newArmy1 != null){
                FileManager.writeArmyToFileWFileName(nameArmy1.getText(), newArmy1);
                ActiveArmies.setActiveArmy1(newArmy1);
                ActiveArmies.setActiveArmy1Path(nameArmy1.getText());
            }
            /////
            Army newArmy2 = parseToArmy(2, prevArmy2, comNrArmy2, infNrArmy2, cavNrArmy2, ranNrArmy2, nameArmy2);
            if(newArmy2 != null){
                FileManager.writeArmyToFileWFileName(nameArmy2.getText(), newArmy2);
                ActiveArmies.setActiveArmy2(newArmy2);
                ActiveArmies.setActiveArmy2Path(nameArmy2.getText());
            }
            ////
            SceneManager.switchView("main");
        }catch (BlankStringException | NegativeIntegerException | NumberFormatException | IOException n){
            if(n instanceof IOException){
                exMsg.setText("Could not load the home screen, please reload the application.");
            }else{
                exMsg.setText("The given input is wrong: " + n.getMessage() + " Cannot not save to file with " +
                        "these values.");
            }

        }
    }

    /**
     * TODO: rewrite
     * @param prevArmy
     * @param comNrArmy1
     * @param infNrArmy1
     * @param cavNrArmy1
     * @param ranNrArmy1
     * @param nameArmy1
     * @return
     */
    private Army parseToArmy(int armyNumber,Army prevArmy, TextField comNrArmy1, TextField infNrArmy1, TextField cavNrArmy1, TextField ranNrArmy1, TextField nameArmy1) throws FileNotFoundException {
        int comNum = Integer.parseInt(comNrArmy1.getText());
        int infNum = Integer.parseInt(infNrArmy1.getText());
        int cavNum = Integer.parseInt(cavNrArmy1.getText());
        int ranNum = Integer.parseInt(ranNrArmy1.getText());
        if(!(prevArmy.getName().equals(nameArmy1.getText()))
                || prevArmy.getCommanderUnits().size() != comNum
                || prevArmy.getInfantryUnits().size() != infNum
                || prevArmy.getCavalryUnits().size() != cavNum
                || prevArmy.getRangedUnits().size() != ranNum){
            return buildArmy(armyNumber,comNum,infNum,cavNum,ranNum);
        } //TODO: exceptions
        return null;
    }

    private Army buildArmy(int armyNumber, int comNum, int infNum,int cavNum, int ranNum) throws FileNotFoundException { //TODO: builderPattern
        Army newArmy;
        Army baseArmy;
        if(armyNumber == 1){
            newArmy = new Army(nameArmy1.getText());
            baseArmy = FileManager.readArmyFromFile("AllianceDoNotEditThisArmy");
        }else{
            newArmy = new Army(nameArmy2.getText());
            baseArmy = FileManager.readArmyFromFile("HordeDoNotEditThisArmy");
        }
        newArmy.addAll(UnitFactory.createListOfUnits(COMMANDER, baseArmy.getCommanderUnits().get(0).getName(),
                baseArmy.getCommanderUnits().get(0).getHealth(), comNum));
        newArmy.addAll(UnitFactory.createListOfUnits(INFANTRY, baseArmy.getInfantryUnits().get(0).getName(),
                baseArmy.getInfantryUnits().get(0).getHealth(), infNum));
        newArmy.addAll(UnitFactory.createListOfUnits(CAVALRY, baseArmy.getCavalryUnits().get(0).getName(),
                baseArmy.getCavalryUnits().get(0).getHealth(), cavNum));
        newArmy.addAll(UnitFactory.createListOfUnits(RANGED, baseArmy.getRangedUnits().get(0).getName(),
                baseArmy.getRangedUnits().get(0).getHealth(), ranNum));
        return newArmy;
    }

    private void confirmInputValues() throws BlankStringException,
            NegativeIntegerException, NumberFormatException {
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
        if(nameArmy2.getText().isBlank() || nameArmy1.getText().isBlank()){
            throw new BlankStringException("Cannot create an army with a blank name.");
        }
    }


    @FXML
    public void checkIntegerInput(Event event) {
        try{
            confirmInputValues();
        }catch (BlankStringException | NegativeIntegerException | NumberFormatException n){
            exMsg.setText("The given input is wrong: " + n.getMessage() + " Cannot not save to file with " +
                    "these values.");
        }
    }
}
