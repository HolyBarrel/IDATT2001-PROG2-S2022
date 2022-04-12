package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

/**
 * Class for a CommanderUnit which inherits all methods from the superclass
 * 'CavalryUnit' which in turn is a subclass of the abstract superclass 'Unit'
 * A CommanderUnit is a more capable version of the CavalryUnit, and does therefore
 * have a buffed attack and armor compared to its superclass
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Constructor 1 for the class CommanderUnit
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param attack, integer value, cannot be inputted as less than zero
     * @param armor, integer value, cannot be inputted as less than zero
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     */
    public CommanderUnit(String name, int health, int attack, int armor) throws NullPointerException,
            BlankStringException, NegativeIntegerException {
        super(name, health, attack, armor);
    }
    /**
     * Constructor 2 for the class InfantryUnit with some default stats
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     */
    public CommanderUnit(String name, int health) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        super(name, health, 25,15);
    }
}
