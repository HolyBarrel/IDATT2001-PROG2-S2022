import java.util.Random;

/**
 * Battle class to simulate a battle between to armies
 * @author Magnus Lutro Allison
 * @version 0.4
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;

    /**
     * Constructor for the class Battle
     * @param armyOne, the first army of this battle, cannot be an empty army
     * @param armyTwo, the second army of this battle, cannot be an empty army
     * @throws IllegalArgumentException if any of the inputted armies are armies without units
     */
    public Battle(Army armyOne, Army armyTwo) throws IllegalArgumentException{
        if(!armyOne.hasUnits()) throw new IllegalArgumentException("The first army inputted did not have any units, " +
                "please try again with two armies containing units.");
        if(!armyTwo.hasUnits()) throw new IllegalArgumentException("The second army inputted did not have any units, " +
                "please try again with two armies containing units.");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Help-method that returns a random boolean which in turn is used to decide
     * the first-striking army of the battle
     * @return boolean 'random', either:
     * true --- means that armyOne strikes first each round, or
     * false -- means that armyTwo strikes first each round
     */
    private boolean isArmyOneStrikingFirst(){
        return new Random().nextBoolean();
    }

    /**
     * Help-method used by the two help-methods presented beneath this method
     * Checks if the unit which has taken a blow (defenderUnit) is dead
     * @param defenderUnit, is the unit which is checked to see if is dead after being
     *                      attacked
     * @return boolean
     * true --- if the defenderUnit has died
     * false -- if the defenderUnit still has remaining health
     */
    private boolean isDefenderDead(Unit defenderUnit){
        return defenderUnit.getHealth() == 0;
    }
    /**
     * A help-method that simulates that a unit from armyOne attacks a unit from armyTwo
     * Chooses the attacker and defender randomly from named armies, via .getRandom-method
     * of objects from the Army-class
     * Then the method exercises the attack, with the .attack-method that objects
     * of subclasses of abstract superclass 'Unit' has inherited from 'Unit'-class
     * Finally checks whether the defenderUnit, which has been attacked is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the .remove()-method for objects of the Army-class
     * The isDefenderDead(Unit defenderUnit) is a private help-method to check if the
     * defenderUnit has health == 0, and hence is dead
     *
     */
    private void unitFromArmyOneAttacks(){
        Unit attackerUnit = armyOne.getRandom();
        Unit defenderUnit = armyTwo.getRandom();
        attackerUnit.attack(defenderUnit);
        if (isDefenderDead(defenderUnit)) armyTwo.remove(defenderUnit);
    }
    /**
     * A help-method that simulates that a unit from armyTwo attacks a unit from armyOne
     * Chooses the attacker and defender randomly from named armies, via .getRandom-method
     * of objects from the Army-class
     * Then the method exercises the attack, with the .attack-method that objects
     * of subclasses of abstract superclass 'Unit' has inherited from 'Unit'-class
     * Finally checks whether the defenderUnit, which has been attacked, is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the .remove()-method for objects of the Army-class
     * The isDefenderDead(Unit defenderUnit) is a private help-method to check if the
     * defenderUnit has health == 0, and hence is dead
     *
     */
    private void unitFromArmyTwoAttacks(){
        Unit attackerUnit = armyTwo.getRandom();
        Unit defenderUnit = armyOne.getRandom();
        attackerUnit.attack(defenderUnit);
        if (isDefenderDead(defenderUnit)) armyOne.remove(defenderUnit);
    }

    /**
     * Simulate-method that simulates a battle between armyOne and armyTwo
     * Utilizes a series of help-methods described above:
     * isArmyOneStrikingFirst
     * unitFromArmyOneAttacks, which, in turn uses help-method: isDefenderDead
     * unitFromArmyTwoAttacks, which, in turn uses help-method: isDefenderDead
     * @return victorious army --> the army that has remaining units
     */
    public Army simulate(){
        boolean isArmyOneCommencingBattle = isArmyOneStrikingFirst();
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            if(isArmyOneCommencingBattle){
                unitFromArmyOneAttacks();
                if(armyOne.hasUnits() && armyTwo.hasUnits()) unitFromArmyTwoAttacks();
            }else{
                unitFromArmyTwoAttacks();
                if(armyOne.hasUnits() && armyTwo.hasUnits()) unitFromArmyOneAttacks();
            }
        }
        if(armyOne.hasUnits()) return armyOne;
        return armyTwo;
    }
    //TODO: REVISE
    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }
}
