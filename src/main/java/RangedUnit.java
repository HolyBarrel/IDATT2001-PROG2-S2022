public class RangedUnit extends Unit{
    //Represents proximity of the enemy
    //TODO: remember to reflect over this use, instead of including hitsReceived/hitsGiven in UNIT-CLASS
    private int hitsReceived = 0;
    /**
     * Constructor 1 for the class RangedUnit
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @param ATTACK, integer value, cannot be inputted as less than zero
     * @param ARMOR, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public RangedUnit(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR);
    }
    /**
     * Constructor 2 for the class RangedUnit with some default stats
     * Creates an object with the following parameters:
     * @param NAME, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *         logical input-range, or is blank
     *         Is thrown from the constructor of the superclass, 'Unit'
     */
    public RangedUnit(String NAME, int health) throws IllegalArgumentException {
        super(NAME, health, 15, 8);
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
     * @return integer value 3
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * Help-method to increase this unit's current number of received hits/attacks
     * -helper for method beneath V
     */
    private void enemyHitsThisUnit(){
        this.hitsReceived++;
    }

    /**
     * Method that returns an integer defense bonus based on the amounts of attacks this
     * RangedUnit has received
     * @return1 integer value 6 when the RangedUnit has been hit 0 times
     * -represents far-ranged combat
     * @return2 integer value 4 when the RangedUnit has been hit 1 time
     * -represents middle ranged combat
     * @return3 integer value 1 when the RangedUnit has been hit 2 or more times
     * -represents close combat
     * Also calls the enemyHitsThisUnit, since this method is used only when
     * this unit is attacked
     */
    @Override
    public int getResistBonus() {
        int farRangeBonus = 5;
        int middleRangeBonus = 3;
        int defaultBonus = 2;
        if(this.getHitsReceived() == 0){
            enemyHitsThisUnit();
            return farRangeBonus + defaultBonus;
        }else if(this.getHitsReceived() == 1){
            enemyHitsThisUnit();
            return middleRangeBonus + defaultBonus;
        }else{
            enemyHitsThisUnit();
            return defaultBonus;
        }
    }
}
