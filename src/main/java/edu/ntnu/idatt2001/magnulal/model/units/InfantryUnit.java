package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

/**
 * Class for an InfantryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * attackBonus = 2, specific value for objects of this subclass
 * resistBonus = 1, specific value for objects of this subclass
 * @author magnulal
 * @version 0.3
 * @since 0.1
 */
public class InfantryUnit extends Unit {
    /**
     * Constructs an InfantryUnit using four given parameters.
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
     * Constructs an InfantryUnit using two given parameters, and sets the values of
     * attack to 15, and
     * armor to 10
     * Instantiates the object with the following parameters:
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
     * Returns an attack bonus for regular melee combat with additional terrain bonus
     * if the specified case occurs (see {@link Unit#getTerrainDefenseBonus(Unit)} for case explanation)
     * @return integer value 2 (5 if terrain is FOREST)
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    @Override
    public int getAttackBonus() throws NullPointerException{
        return 2 + getTerrainAttackBonus(this);
    }
    /**
     * Returns a small resist bonus in close defense with additional terrain bonus
     * if the specified case occurs (see {@link Unit#getTerrainDefenseBonus(Unit)} for case explanation)
     * @return integer value 1 (4 if terrain is FOREST)
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    @Override
    public int getResistBonus() throws NullPointerException{
        return 1 + getTerrainDefenseBonus(this);
    }
}
