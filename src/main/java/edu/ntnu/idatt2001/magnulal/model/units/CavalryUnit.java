package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

/**
 * Class for a CavalryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * attackBonus is either 6 or 2 depending on whether the object of this class is charging
 *  = attackBonus: 6, or not = attackBonus: 2
 *  A cavalryUnit is set to charge on the first attack it makes
 *  resistBonus = 1, specific for objects of this subclass
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public class CavalryUnit extends Unit {
    private boolean charge = true;
    /**
     * Constructs a CavalryUnit with four given parameter values.
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
    public CavalryUnit(String name, int health, int attack, int armor) throws NullPointerException,
            BlankStringException, NegativeIntegerException {
        super(name, health, attack, armor);
    }
    /**
     * Constructs a CavalryUnit with two given parameters, and sets the values of
     * attack to 20, and
     * armor to 12
     * Instantiates the object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     */
    public CavalryUnit(String name, int health) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        super(name, health, 20, 12);
    }
    /**
     * Returns a boolean value representing whether this unit is in a state of 'charge'
     * @return true if this unit is charging, and false otherwise
     */
    public boolean isCharging() {
        return charge;
    }

    /**
     * Stops this unit from charging by altering this charge-attribute to false
     */
    private void stopCharging(){
        this.charge = false;
    }

    /**
     * Overrides the {@link Unit#attack(Unit)} method by adding logic to a cavalry unit's attack.
     * This method ensures that if the unit is charging after a successful attack, the unit must
     * stop its charge.
     * @param opponent, the unit that is attacked by this unit
     */
    @Override
    public void attack(Unit opponent) {
        super.attack(opponent);
        if(isCharging()) stopCharging();
    }

    /**
     * Returns an attack bonus for a charging attack and regular melee combat with additional terrain bonus
     * if the specified case occurs (see {@link Unit#getTerrainAttackBonus(Unit)} for case explanation)
     * @return integer value 6 (10 if terrain is PLAINS) if charging, otherwise integer value 2
     * (6 if terrain is PLAINS).
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    @Override
    public int getAttackBonus() throws NullPointerException {
        //The cavalry unit will get a further charge-bonus to its first attack
        if(isCharging()) return 6 + getTerrainAttackBonus(this); //additional 4 attackBonus when in charge
        return 2 + getTerrainAttackBonus(this); //the default attackBonus is integer value 2
    }

    /**
     * Returns a small resist bonus in close defense with additional terrain bonus
     * if the specified case occurs (see {@link Unit#getTerrainDefenseBonus(Unit)} for case explanation)
     * @return integer value 1 (0 if terrain is FOREST)
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    @Override
    public int getResistBonus() throws NullPointerException {
        if(getTerrainDefenseBonus(this) == null) return 0;
        return 1;
    }
}
