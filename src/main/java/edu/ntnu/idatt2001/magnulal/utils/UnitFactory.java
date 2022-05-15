package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import edu.ntnu.idatt2001.magnulal.model.units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * UnitFactory class to extract the ability to instantiate units to a separate class
 * This class has a method to create a single unit, and also to create a list with
 * a given integer amount of duplicated units
 * @author magnulal
 * @version 0.3
 * @since 0.3
 */
public class UnitFactory {
    /**
     * Constructs a new unit through the factory by specifying the unit type, name, and integer amount of health
     * @param unitType is an enum of UnitTypes, decides which subclass of 'Unit' the method should instantiate
     * @param unitName is a string which is given as the unit name, cannot be blank or of 'null'-value
     * @param unitHealth is an integer amount which is assigned to the unit
     * @return a specified instantiation of a 'Unit' subclass
     * @throws NullPointerException if the name parameter has the value 'null', or if the enum type is not found
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     * TODO: test
     */
    public static Unit createUnit(UnitTypes unitType, String unitName, int unitHealth) throws NullPointerException,
            BlankStringException, NegativeIntegerException {
        return switch (unitType) {
            case INFANTRY -> new InfantryUnit(unitName, unitHealth);
            case RANGED -> new RangedUnit(unitName, unitHealth);
            case CAVALRY -> new CavalryUnit(unitName, unitHealth);
            case COMMANDER -> new CommanderUnit(unitName, unitHealth);
            //default -> throw new NullPointerException("The requested unit type could not be found.");
        };
    }

    /**
     * Constructs a list of a given integer number of units, these added units are created by the
     * {@link #createUnit(UnitTypes, String, int)} method of this Factory.
     * @param unitType is an enum of UnitTypes, decides which subclass of 'Unit' the method should instantiate
     * @param unitName is a string which is given as the unit name, cannot be blank or of 'null'-value
     * @param unitHealth is an integer amount which is assigned to the unit
     * @param numberOfUnits is an integer specifying how many duplicate units of the given type that are supposed to be
     *                      added to the list that is to be returned
     * @return a list of n duplicate units of a subclass of the 'Unit' class
     * @throws NullPointerException if the name parameter has the value 'null', or if the enum type is not found
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     * TODO: test
     */
    public static List<Unit> createListOfUnits(UnitTypes unitType, String unitName, int unitHealth, int numberOfUnits)
            throws NullPointerException, BlankStringException, NegativeIntegerException {
        int counter = 0;
        List<Unit> listOfUnits = new ArrayList<>();
        while(counter < numberOfUnits){
            listOfUnits.add(createUnit(unitType, unitName, unitHealth));
            counter++;
        }
        return listOfUnits;
    }
}
