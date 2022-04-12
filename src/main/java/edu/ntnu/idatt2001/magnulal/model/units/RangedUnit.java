package edu.ntnu.idatt2001.magnulal.model.units;
/**
 * Class for a ranged unit which is a subclass of the abstract superclass 'Unit'
 * attackBonus = 3, is specific for this subclass
 * resistBonus is either 7, 5 or 2, depending on how many times this unit-object of
 * class has been hit, this is a way to represent how far away the enemy is
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public class RangedUnit extends Unit{
    //Represents proximity of the enemy army
    //TODO: remember to reflect over this use, instead of including hitsReceived/hitsGiven in UNIT-CLASS
    //and reflect over the use of the specified methods which increments this attribute for this unit
    private int hitsReceived = 0;
    /**
     * Constructor 1 for the class RangedUnit
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param attack, integer value, cannot be inputted as less than zero
     * @param armor, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public RangedUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }
    /**
     * Constructor 2 for the class RangedUnit with some default stats
     * Creates an object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public RangedUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 15, 8);
    }
    /**
     * Accessor method that returns the number of attacks this RangedUnit
     * has received
     * According to the project task, this also represents the proximity of the enemy
     * 0 hits --> enemy is far away
     * 1 hit --> enemy is nearing
     * 2+ hits --> enemy is in close combat with this RangedUnit
     * @return integer hitsReceived
     */
    public int getHitsReceived() {
        return hitsReceived;
    }
    /**
     * Method that returns an attack bonus for ranged attacks
     * @return integer value 3 if the attack is done when this unit has received 0 or 1 hits,
     * which represents the RangedUnit attacking from range, else return 0
     */
    @Override
    public int getAttackBonus() {
        if(this.getHitsReceived() <= 1){
            return 3;
        }else{
            return 0;
        }
    }
    /**
     * Increment method to increase this unit's current number of received hits/attacks
     */
    public void enemyHitsThisUnit(){
        this.hitsReceived++;
    }
    /**
     * Help method to assess the resist bonus this RangedUnit should be buffed with
     * @return1 integer value 7 when the RangedUnit has not been hit
     * -represents far-ranged combat
     * @return2 integer value 5 when the RangedUnit has been hit 1 time
     * -represents middle ranged combat
     * @return3 integer value 2 when the RangedUnit has been hit 2 or more times
     * -represents close combat
     * This help method is utilized in the getResistBonus-method beneath
     */
    private int resistBonusBasedOnDistanceFromOpposingArmy(){
        if(this.getHitsReceived() == 0){
            return 7;
        }else if(this.getHitsReceived() == 1){
            return 5;
        }else{
            return 2;
        }
    }
    /**
     * Method that returns an integer resist bonus based on the amount of attacks this
     * RangedUnit has received
     * @return1 integer value 7 when the RangedUnit has not been hit
     * -represents far-ranged combat
     * @return2 integer value 5 when the RangedUnit has been hit 1 time
     * -represents middle ranged combat
     * @return3 integer value 2 when the RangedUnit has been hit 2 or more times
     * -represents close combat
     * Also increments the times this unit has been hit, since this method is called
     * when this unit is defending an attack
     */
    @Override
    public int getResistBonus() {
        int resistBonus = resistBonusBasedOnDistanceFromOpposingArmy();
        enemyHitsThisUnit();
        return resistBonus;
    }
}
