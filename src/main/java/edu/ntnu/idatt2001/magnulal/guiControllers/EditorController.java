package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.utils.*;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.TooLargeIntegerException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import static edu.ntnu.idatt2001.magnulal.utils.UnitTypes.*;

/**
 * Controller class to control the editor scene
 * @author magnulal
 * @version 1.0
 * @since 0.3
 */
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

    /**
     * Updates the information that is displayed in the GUI based on the active armies.
     */
    @FXML
    public void initialize() {
        updateWArmies(ActiveArmies.getActiveArmy1(),ActiveArmies.getActiveArmy2());
    }

    /**
     * Help method that updates the active armies to the GUI.
     * @param army1 is the first active army
     * @param army2 is the second active army
     */
    private void updateWArmies(Army army1, Army army2){
        ActiveArmies.setActiveArmy1(army1);
        ActiveArmies.setActiveArmy2(army2);
        ActiveArmies.setActiveArmy1Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                ActiveArmies.getActiveArmy1().getName() + ".csv");
        ActiveArmies.setActiveArmy2Path("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/" +
                ActiveArmies.getActiveArmy2().getName() + ".csv");
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

    /**
     * Switches the currently viewed scene to the home screen. Utilizes the {@link SceneManager#switchView(String)}
     * to target the main.fxml document
     */
    @FXML
    public void returnHome() {
        try {
            SceneManager.switchView("main");
        } catch (IOException e) {
            exMsg.setText(e.getMessage() + " Please reload the application if no other solutions apply.");
        }
    }

    /**
     * Saves the current edit if the inputted values are confirmed by the {@link EditorController#confirmInputValues()}
     * method. Then switches the currently viewed scene to the home screen. Uses the
     * {@link EditorController#parseToArmy(int, Army, TextField, TextField, TextField, TextField, TextField)} to create
     * the new army after it has been edited. Also utilized the
     * {@link FileManager#writeArmyToFileWFileName(String, Army)} method to save the inputted army as a file.
     * This implicates that if the user chooses to edit only the amounts of different unit types, but not the name, the
     * method will overwrite the already existing CSV file to update its unit numbers. If no info has changed,
     * the parseToArmy will return null and the save method will not create a new army.
     */
    @FXML
    public void saveAndReturn() {
        try{
            exMsg.setText("");
            confirmInputValues();

            Army prevArmy1 = ActiveArmies.getActiveArmy1();
            Army prevArmy2 = ActiveArmies.getActiveArmy2();

            Army newArmy1 = parseToArmy(1, prevArmy1, comNrArmy1, infNrArmy1, cavNrArmy1,
                    ranNrArmy1, nameArmy1);
            if(newArmy1 != null){
                FileManager.writeArmyToFileWFileName(nameArmy1.getText(), newArmy1);
                ActiveArmies.setActiveArmy1(newArmy1);
            }
            Army newArmy2 = parseToArmy(2, prevArmy2, comNrArmy2, infNrArmy2, cavNrArmy2,
                    ranNrArmy2, nameArmy2);
            if(newArmy2 != null){
                FileManager.writeArmyToFileWFileName(nameArmy2.getText(), newArmy2);
                ActiveArmies.setActiveArmy2(newArmy2);
            }
            SceneManager.switchView("main");
        }catch (BlankStringException | NegativeIntegerException | NumberFormatException | IOException |
                TooLargeIntegerException n){
            if(n instanceof IOException){
                exMsg.setText("Could not load the home screen, please reload the application.");
            }else{
                exMsg.setText("Wrong input: " + n.getMessage() + " Cannot not save to file with " +
                        "these values.");
            }
        }
    }

    /**
     * Parses the legal inputted integers from the GUI to return an army adhering to the information edited by the
     * user. If the information is the same as the loaded armies, the method returns 'null'.
     * @param armyNumber is either the first (1) or the second army (2)
     * @param prevArmy is the previous army which is checked for changes
     * @param comNrArmy is the number of commander units in the given army currently in the TextField
     * @param infNrArmy is the number of infantry units in the given army currently in the TextField
     * @param cavNrArmy is the number of cavalry units in the given army currently in the TextField
     * @param ranNrArmy is the number of ranged units in the given army currently in the TextField
     * @param nameArmy is the name of the given army
     * @return a new Army if there is new info, and 'null' otherwise
     * @throws NumberFormatException if the parsed information cannot be used to create an army
     * @throws FileNotFoundException if the {@link EditorController#buildArmy(int, int, int, int, int)} cannot
     * find the intended file
     */
    private Army parseToArmy(int armyNumber,Army prevArmy, TextField comNrArmy,
                             TextField infNrArmy, TextField cavNrArmy, TextField ranNrArmy, TextField nameArmy)
            throws NumberFormatException, FileNotFoundException {
        int comNum = Integer.parseInt(comNrArmy.getText());
        int infNum = Integer.parseInt(infNrArmy.getText());
        int cavNum = Integer.parseInt(cavNrArmy.getText());
        int ranNum = Integer.parseInt(ranNrArmy.getText());
        if(!(prevArmy.getName().equals(nameArmy.getText()))
                || prevArmy.getCommanderUnits().size() != comNum
                || prevArmy.getInfantryUnits().size() != infNum
                || prevArmy.getCavalryUnits().size() != cavNum
                || prevArmy.getRangedUnits().size() != ranNum){
            return buildArmy(armyNumber,comNum,infNum,cavNum,ranNum);
        }
        return null;
    }

    /**
     * Creates an army from the parsed information. Uses the Alliance and Horde armies in the csvBackup directory to
     * access information on specifications on the unit types' name and health. Thereby all units are duplicated of the
     * ones in the 'baseArmies'.
     * @param armyNumber is the number of the army that is going to be built. Is either army one (1), or army two (2)
     * @param comNum is the number of commander units generated
     * @param infNum is the number of infantry units generated
     * @param cavNum is the number of cavalry units generated
     * @param ranNum is the number of commander units generated
     * @return an Army the built army
     * @throws FileNotFoundException if the file is not located
     * @throws InvalidPathException if the path is an invalid one
     * @throws NullPointerException if the file could not be used to create an army or contained information about
     * units that could not be used to create a unit
     */
    private Army buildArmy(int armyNumber, int comNum, int infNum,int cavNum, int ranNum) throws FileNotFoundException,
            InvalidPathException {
        Army newArmy;
        Army baseArmy;
        if(armyNumber == 1){
            newArmy = new Army(nameArmy1.getText());
            baseArmy = FileManager.
                    readArmyFromFullFilePath("src/main/resources/edu.ntnu.idatt2001.magnulal/csvBackup/Alliance.csv");
        }else{
            newArmy = new Army(nameArmy2.getText());
            baseArmy = FileManager.
                    readArmyFromFullFilePath("src/main/resources/edu.ntnu.idatt2001.magnulal/csvBackup/Horde.csv");
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

    /**
     * Runs checks on all the user inputted information. Throws exceptions to inform the user on any illegal input.
     * @throws BlankStringException if either of the armies' names are a blank string
     * @throws NegativeIntegerException if there has been inputted a negative integer in an input field
     * @throws NumberFormatException if there has been inputted a number that could not be parsed to an integer
     * @throws TooLargeIntegerException if the number is of unwanted size, meaning an integer above 10000
     */
    private void confirmInputValues() throws BlankStringException,
            NegativeIntegerException, NumberFormatException, TooLargeIntegerException {
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
        if(numberOfDifferentUnits.stream().anyMatch(integer -> integer > 10000)){
            throw new TooLargeIntegerException("Please enter an integer less than 10000 for the amount of a " +
                    "type of units.");
        }
        if(nameArmy2.getText().isBlank() || nameArmy1.getText().isBlank()){
            throw new BlankStringException("Cannot create an army with a blank name.");
        }
    }

    /**
     * Checks all input values at an input event. This occurs if the user interacts with any of the editable TextFields
     * of the EditorController.
     * @param event is the event occurring at edit
     */
    @FXML
    public void checkIntegerInput(Event event) {
        try{
            confirmInputValues();
        }catch (BlankStringException | NegativeIntegerException | NumberFormatException | TooLargeIntegerException n){
            exMsg.setText("The given input is wrong: " + n.getMessage() + " Cannot not save to file with " +
                    "these values.");
        }
    }
}
