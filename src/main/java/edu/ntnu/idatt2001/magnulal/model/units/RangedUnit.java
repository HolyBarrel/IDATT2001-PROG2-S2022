package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

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
     * Constructs a RangedUnit using four given parameters.
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
    public RangedUnit(String name, int health, int attack, int armor) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        super(name, health, attack, armor);
    }
    /**
     * Constructs a RangedUnit using two given parameters, and sets the values of
     * attack to 15, and
     * armor to 8
     * Instantiates the object with the following parameters:
     * @param name, String value, cannot be inputted as blank
     * @param health, integer value, cannot be inputted as less than zero
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     */
    public RangedUnit(String name, int health) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        super(name, health, 15, 8);
    }
    /**
     * Returns the number of attacks this RangedUnit has received
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
     * Returns an attack bonus for ranged attacks
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
     * Increase this unit's current number of received attacks
     */
    public void enemyHitsThisUnit(){
        this.hitsReceived++;
    }

    /**
     * Validates the resist bonus this RangedUnit should be buffed with.
     * Is a help method utilized by the {@link #getResistBonus()} method of RangedUnits
     * @return integer value 7 when the RangedUnit has not been hit
     * -represents far-ranged combat,
     * integer value 5 when the RangedUnit has been hit 1 time
     * -represents middle ranged combat,
     * integer value 2 when the RangedUnit has been hit 2 or more times
     * -represents close combat
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
     * Returns an integer resist bonus based on the amount of attacks this RangedUnit has received.
     * Also increments the times this unit has been hit, since this method is called
     * when this unit is defending an attack
     * @return integer value 7 when the RangedUnit has not been hit
     * -represents far-ranged combat,
     * integer value 5 when the RangedUnit has been hit 1 time
     * -represents middle ranged combat,
     * integer value 2 when the RangedUnit has been hit 2 or more times
     * -represents close combat
     */
    @Override
    public int getResistBonus() {
        int resistBonus = resistBonusBasedOnDistanceFromOpposingArmy();
        enemyHitsThisUnit();
        return resistBonus;
    }
}
