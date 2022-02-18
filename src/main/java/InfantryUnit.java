/**
 * Class for an InfantryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * TODO: more info here
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 12.02.2022
 */
public class InfantryUnit extends Unit{
    /**
     * Constructor 1 for the class InfantryUnit
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param ATTACK, integer value, cannot be inputted as less than zero
     * @param ARMOR, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public InfantryUnit(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR);
    }

    /**
     * Constructor 2 for the class InfantryUnit with some default stats
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public InfantryUnit(String NAME, int health) throws IllegalArgumentException {
        super(NAME, health, 15, 10);
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
