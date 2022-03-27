package edu.ntnu.idatt2001.magnulal.unitclasses;
/**
 * Class for a CommanderUnit which inherits all methods from the superclass
 * 'CavalryUnit' which in turn is a subclass of the abstract superclass 'Unit'
 * A CommanderUnit is a more capable version of the CavalryUnit, and does therefore
 * have a buffed attack and armor compared to its superclass
 * @author Magnus Lutro Allison
 * @version 0.6
 * @since 0.3
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Constructor 1 for the class CommanderUnit
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param attack, integer value, cannot be inputted as less than zero
     * @param armor, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the second grade superclass, called 'Unit'
     */
    public CommanderUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }
    /**
     * Constructor 2 for the class InfantryUnit with some default stats
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the second grade superclass, called 'Unit'
     */
    public CommanderUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 25,15);
    }
}
