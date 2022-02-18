/**
 * Class for a CavalryUnit with specification of the abstract methods of its
 * superclass 'Unit'
 * TODO: more info here
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 12.02.2022
 */
public class CavalryUnit extends Unit{
    private int successfulAttacks = 0;
    /**
     * Constructor 1 for the class CavalryUnit
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param ATTACK, integer value, cannot be inputted as less than zero
     * @param ARMOR, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public CavalryUnit(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
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
    public CavalryUnit(String NAME, int health) throws IllegalArgumentException {
        super(NAME, health, 20, 12);
    }
    /**
     * Accessor method that returns the number of attacks this CavalryUnit has done
     * @return int successfulAttacks
     */
    public int getSuccessfulAttacks() {
        return successfulAttacks;
    }
    /**
     * Help-method to increase this unit's current number of successful attacks
     * -helper for method beneath V
     */
    private void attackSuccess(){
        this.successfulAttacks++;
    }
    /**
     * Method that returns an attack bonus for charge attack and melee combat
     * Also increments the number of successful attacks done
     * @return1 integer value 6, is returned if charging
     * @return2 integer value 2, is returned if not charging
     */
    @Override
    public int getAttackBonus() {
        int defaultAtkBonus = 2;
        int chargeAtkBonus = 4;
        //The cavalry unit will get a further charge-bonus in its first attack
        boolean charge = this.getSuccessfulAttacks() == 0;
        attackSuccess();
        if(charge){
            return defaultAtkBonus + chargeAtkBonus;
        }else{
            return defaultAtkBonus;
        }
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
