package edu.ntnu.idatt2001.magnulal.unitclasses;

/**
 * Class for a CavalryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * attackBonus is either 6 or 2 depending on whether the object of this class is charging
 *  = attackBonus: 6, or not = attackBonus: 2
 *  A cavalryUnit is set to charge on the first attack it makes
 *  resistBonus = 1, specific for objects of this subclass
 * @author Magnus Lutro Allison
 * @version 0.6
 * @since 0.3
 */
public class CavalryUnit extends Unit {
    private boolean charge = true;
    /**
     * Constructor 1 for the class CavalryUnit
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param attack, integer value, cannot be inputted as less than zero
     * @param armor, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public CavalryUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }
    /**
     * Constructor 2 for the class InfantryUnit with some default stats
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public CavalryUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 20, 12);
    }
    /**
     * Accessor method that returns a boolean true if this unit is charging, and false otherwise
     */
    public boolean isCharging() {
        return charge;
    }

    /**
     * Mutator method to stop this unit from charging
     */
    private void stopCharging(){
        this.charge = false;
    }

    /**
     * Attack method containing the formula given how the logic behind a cavalry unit's attack
     * is in the game
     *
     * @param opponent, the unit that is attacked by this unit
     */
    @Override
    public void attack(Unit opponent) {
        super.attack(opponent);
        if(isCharging()) stopCharging(); //the unit will stop charging after the first successful attack
    }

    /**
     * Method that returns an attack bonus for charge attack and melee combat
     * @return1 integer value 6, is returned if charging
     * @return2 integer value 2, is returned if not charging
     */
    @Override
    public int getAttackBonus() {
        //The cavalry unit will get a further charge-bonus to its first attack
        if(isCharging()) return 6; //additional 4 attackBonus when in charge
        return 2; //the default attackBonus is integer value 2
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
