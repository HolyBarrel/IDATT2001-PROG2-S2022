package edu.ntnu.idatt2001.magnulal.model.simulator;
import edu.ntnu.idatt2001.magnulal.model.units.Unit;

import java.util.Random;
/**
 * Battle class to simulate a battle between to armies
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;
    private final boolean armyOneIsCommencingBattle; //if true --> armyOne attacks
    // first in each turn, if false, armyTwo attacks first
    /**
     * Constructor for the class Battle
     * @param armyOne, the first army of this battle, cannot be an empty army
     * @param armyTwo, the second army of this battle, cannot be an empty army
     * @throws IllegalArgumentException if any of the inputted armies are armies without units
     * Also chooses a boolean value randomly for attribute 'armyOneIsCommencingBattle' which
     * decides whether army one is striking first each turn
     */
    public Battle(Army armyOne, Army armyTwo) throws IllegalArgumentException{
        checkIfArmiesHasUnits(armyOne, armyTwo);
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.armyOneIsCommencingBattle = new Random().nextBoolean();
    }
    private void checkIfArmiesHasUnits(Army armyOne, Army armyTwo) throws IllegalArgumentException{
        if(!armyOne.hasUnits()) throw new IllegalArgumentException("The first army inputted did not have any units, " +
                "please try again with two armies containing units.");
        if(!armyTwo.hasUnits()) throw new IllegalArgumentException("The second army inputted did not have any units, "+
                "please try again with two armies containing units.");
    }
    /**
     * Help method utilized to check if both armies in the battle have units
     * @return true if both armies have units || false if one of the armies are
     *
     */
    private boolean battleIsActive(){
        return armyOne.hasUnits() && armyTwo.hasUnits();
    }
    /**
     * Help-method used by the 'unitAttacks' method presented beneath
     * Checks if the unit which has taken a blow (defenderUnit) is dead
     * @param defenderUnit, is the unit which is checked to see if is dead after
     *                      being attacked
     *                      if this unit has health == 0 after the attack, it is
     *                      removed from its respective army
     */
    private void removeDefenderIfDead(Unit defenderUnit, Army defendingArmy){
        if(defenderUnit.getHealth() == 0) defendingArmy.remove(defenderUnit);
    }
    /**
     A help-method that simulates that a unit from attackingArmy attacks a unit from defendingArmy
     * Chooses the attacker and defender randomly from named armies,
     * via .getRandom-method of objects from the Army-class
     * Then the method exercises .attack-method of the attackerUnit on the defenderUnit
     * of subclasses of abstract superclass 'Unit' has inherited from 'Unit'-class or polymorphed
     * Finally checks whether the defenderUnit, which has been attacked, is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the .remove()-method for objects of the Army-class
     * This sequence will occur as long as the help-method 'battleIsActive' is true
     * @param attackingArmy, parameter of class Army. From this army an attacking unit called
     *                       'attackerUnit' is chosen if the battle is active
     * @param defendingArmy, parameter of class Army. From this army a defending unit called
     *                       'defenderUnit' is chosen if the battle is active
     */
    private void unitAttacks(Army attackingArmy, Army defendingArmy){
        if(battleIsActive()){
            Unit attackerUnit = attackingArmy.getRandom();
            Unit defenderUnit = defendingArmy.getRandom();
            attackerUnit.attack(defenderUnit);
            removeDefenderIfDead(defenderUnit, defendingArmy);
        }
    }
    /**
     * Simulate-method that simulates a battle between armyOne and armyTwo
     * Utilizes a series of help-methods described above:
     * battleIsActive
     * unitAttacks which, in turn uses help-methods: battleIsActive, removeDefenderIfDead
     * @return victorious army is the army that has remaining units after a finite amount
     * of attacks
     */
    public Army simulate(){
        while (battleIsActive()){
            if(this.armyOneIsCommencingBattle){
                unitAttacks(armyOne,armyTwo);
                unitAttacks(armyTwo,armyOne);
            }else{
                unitAttacks(armyTwo,armyOne);
                unitAttacks(armyOne,armyTwo);
            }
        }
        if(armyOne.hasUnits()) return armyOne;
        return armyTwo;
    }
    @Override
    public String toString() {
        return "The Great Battle of '" +
                armyOne.getName() + "' and '" +
                armyTwo.getName() + "'\n" +
                armyOne +
                armyTwo;
    }
}
