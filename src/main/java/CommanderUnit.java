/**
 * Class for a CommanderUnit which inherits all methods from the superclass
 * 'CavalryUnit' which in turn is a subclass of the abstract superclass 'Unit'
 * A CommanderUnit is a more capable version of the CavalryUnit, and does therefore
 * have a buffed attack and armor compared to its superclass
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Constructor 1 for the class CommanderUnit
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param ATTACK, integer value, cannot be inputted as less than zero
     * @param ARMOR, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the second grade superclass, called 'Unit'
     */
    public CommanderUnit(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR);
    }
    /**
     * Constructor 2 for the class InfantryUnit with some default stats
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the second grade superclass, called 'Unit'
     */
    public CommanderUnit(String NAME, int health) throws IllegalArgumentException {
        super(NAME, health, 25,12);
    }
}
