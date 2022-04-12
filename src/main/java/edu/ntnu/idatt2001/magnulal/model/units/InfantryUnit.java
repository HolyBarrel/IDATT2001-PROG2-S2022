package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

/**
 * Class for an InfantryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * attackBonus = 2, specific value for objects of this subclass
 * resistBonus = 1, specific value for objects of this subclass
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public class InfantryUnit extends Unit {
    /**
     * Constructor 1 for the class InfantryUnit
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
    public InfantryUnit(String name, int health, int attack, int armor) throws NullPointerException,
            BlankStringException, NullPointerException {
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
    public InfantryUnit(String name, int health) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        super(name, health, 15, 10);
    }

    /**
     * Method that returns an attack bonus for melee combat
     * @return integer value 2
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }
    /**
     * Method that returns a small resist bonus in close defense
     * @return integer value 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
