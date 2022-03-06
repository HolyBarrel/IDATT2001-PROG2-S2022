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
     * @return boolean
     * true --- if the defenderUnit has died (has health == 0)
     * false -- if the defenderUnit still has remaining health
     */
    private boolean defenderIsDead(Unit defenderUnit){
        return defenderUnit.getHealth() == 0;
    }
    /**
     * A help-method that simulates that a unit from armyOne attacks a unit from armyTwo
     * Chooses the attacker and defender randomly from named armies, via .getRandom-method
     * of objects from the Army-class
     * Then the method exercises the attack, with the .attack-method that objects
     * of subclasses of abstract superclass 'Unit' has inherited from 'Unit'-class or polymorphed
     * Finally checks whether the defenderUnit, which has been attacked is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the .remove()-method for objects of the Army-class
     * The defenderIsDead(Unit defenderUnit) is a private help-method to check if the
     * defenderUnit has health == 0, and hence is dead
     */
    private void unitFromArmyOneAttacks(){
        if(battleIsActive()) {
            Unit attackerUnit = armyOne.getRandom();
            Unit defenderUnit = armyTwo.getRandom();
            attackerUnit.attack(defenderUnit);
            if (defenderIsDead(defenderUnit)) armyTwo.remove(defenderUnit);
        }
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
     * defenderUnit has health == 0, and hence is dead
     */
    private void unitFromArmyTwoAttacks(){
        if(battleIsActive()) {
            Unit attackerUnit = armyTwo.getRandom();
            Unit defenderUnit = armyOne.getRandom();
            attackerUnit.attack(defenderUnit);
            if (defenderIsDead(defenderUnit)) armyOne.remove(defenderUnit);
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
        boolean armyOneIsCommencingBattle = new Random().nextBoolean(); //if true --> armyOne attacks
        // first, if false armyTwo attacks first
        while (battleIsActive()){
            if(armyOneIsCommencingBattle){
                unitFromArmyOneAttacks();
                unitFromArmyTwoAttacks();
            }else{
                unitFromArmyTwoAttacks();
                unitFromArmyOneAttacks();
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
