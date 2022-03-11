package edu.ntnu.idatt2001.magnulal;
import java.util.Random;
/**
 * Battle class to simulate a battle between to armies
 * @author Magnus Lutro Allison
 * @version 0.5
 * @since 0.5
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
        if(!armyOne.hasUnits()) throw new IllegalArgumentException("The first army inputted did not have any units, " +
                "please try again with two armies containing units.");
        if(!armyTwo.hasUnits()) throw new IllegalArgumentException("The second army inputted did not have any units, " +
                "please try again with two armies containing units.");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.armyOneIsCommencingBattle = new Random().nextBoolean();
    }
    /**
     * Help method utilized to check if both armies in the battle still have units to fight
     * @return true if both armies have units || false if one of the armies is eradicated
     */
    private boolean battleIsActive(){
        return armyOne.hasUnits() && armyTwo.hasUnits();
    }
    /**
     * Help-method used by the two other help-methods presented beneath
     * Checks if the unit which has taken a blow (defenderUnit) is dead
     * @param defenderUnit, is the unit which is checked to see if is dead after being
     *                      attacked
     * true --- if the defenderUnit has died (has health == 0)
     * false -- if the defenderUnit still has remaining health //TODO: update
     */
    private void removeDefenderIfDead(Unit defenderUnit, Army defendingArmy){
        if(defenderUnit.getHealth() == 0) defendingArmy.remove(defenderUnit);
    }
    /**
     * A help-method that simulates that a unit from armyTwo attacks a unit from armyOne
     * Chooses the attacker and defender randomly from named armies, via .getRandom-method
     * of objects from the Army-class
     * Then the method exercises the attack, with the .attack-method that objects
     * of subclasses of abstract superclass 'Unit' has inherited from 'Unit'-class or polymorphed
     * Finally checks whether the defenderUnit, which has been attacked, is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the .remove()-method for objects of the Army-class
     * The defenderIsDead(Unit defenderUnit) is a private help-method to check if the
     * defenderUnit has health == 0, and hence is dead //TODO: update
     */
    private void unitAttacks(Army attackingArmy, Army defendingArmy){
        if(battleIsActive()){
            Unit attackerUnit = attackingArmy.getRandom();
            Unit defenderUnit = defendingArmy.getRandom();
            attackerUnit.attack(defenderUnit);
            removeDefenderIfDead(defenderUnit, defendingArmy);
        }
    }
    //TODO: remove or keep?
    private void executeAttacksOfOneTurn(){
        if(this.armyOneIsCommencingBattle){
            unitAttacks(armyOne,armyTwo);
            unitAttacks(armyTwo,armyOne);
        }else{
            unitAttacks(armyTwo,armyOne);
            unitAttacks(armyOne,armyTwo);
        }
    }
    /**
     * Simulate-method that simulates a battle between armyOne and armyTwo
     * Utilizes a series of help-methods described above:
     * battleIsActive
     * unitFromArmyOneAttacks, which, in turn uses help-methods: defenderIsDead, battleIsActive
     * unitFromArmyTwoAttacks, which, in turn uses help-methods: defenderIsDead, battleIsActive
     * @return victorious army --> the army that has remaining units
     */
    public Army simulate(){
        while (battleIsActive()){
            executeAttacksOfOneTurn();
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
