package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;

/**
 * Class for a CommanderUnit which inherits all methods from the superclass
 * 'CavalryUnit' which in turn is a subclass of the abstract superclass 'Unit'
 * A CommanderUnit is a more capable version of the CavalryUnit, and does therefore
 * have a buffed attack and armor compared to its superclass
 * @author magnulal
 * @version 1.0
 * @since 0.1
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Constructs a CommanderUnit using four given parameters.
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
     * Constructs a CommanderUnit using two given parameters, and sets the values of
     * attack to 25, and
     * armor to 15
     * Instantiates the object with the following parameters:
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
