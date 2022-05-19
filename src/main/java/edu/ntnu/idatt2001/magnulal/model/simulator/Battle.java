package edu.ntnu.idatt2001.magnulal.model.simulator;
import edu.ntnu.idatt2001.magnulal.model.units.Unit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import edu.ntnu.idatt2001.magnulal.utils.TerrainType;

import java.util.ArrayList;
import java.util.Random;
/**
 * Battle class to simulate a battle between to armies
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 0.1
 */
public class Battle {
    private Army armyOne;
    private Army armyTwo;
    private final boolean armyOneIsCommencingBattle; //if true --> armyOne attacks
    // first in each turn, if false, armyTwo attacks first
    /**
     * Constructs a Battle with two instances of the Class 'Army'
     * Also chooses a boolean value randomly for attribute 'armyOneIsCommencingBattle' which
     * decides whether army one is striking first each turn
     * @param armyOne, the first army of this battle, cannot be an empty army
     * @param armyTwo, the second army of this battle, cannot be an empty army
     * @throws IllegalArgumentException if any of the inputted armies are armies without units
     * @throws NullPointerException if the terrainType parameter has 'null' as value
     * TODO: implement exception handling for an infinite battle
     */
    public Battle(Army armyOne, Army armyTwo, TerrainType terrainType) throws IllegalArgumentException,
            NullPointerException{
        checkIfArmiesHasUnits(armyOne, armyTwo);
        if(terrainType == null) throw new NullPointerException("The specified terrain type is not supported, please" +
                "try one of the following terrain types: FOREST, HILL, PLAINS.");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.armyOneIsCommencingBattle = new Random().nextBoolean();
        ActiveTerrain.INSTANCE.setActiveTerrain(terrainType);

    }

    /**
     * Verifies if both parameter armies contains units using {@link Army#hasUnits()} method.
     * IllegalArgumentException is thrown if the checks are triggered.
     * @param armyOne, is of the 'Army' class
     * @param armyTwo, is of the 'Army' class
     * @throws IllegalArgumentException if one of the armies does not contain any units. There are two checks to create
     * a specified exception-message which specifies which the empty army.
     */
    private void checkIfArmiesHasUnits(Army armyOne, Army armyTwo) throws IllegalArgumentException{
        if(!armyOne.hasUnits()) throw new IllegalArgumentException("The first army inputted: " + armyOne.getName() +
                " did not have any units, please try again with two armies containing units.");
        if(!armyTwo.hasUnits()) throw new IllegalArgumentException("The second army inputted: " + armyOne.getName() +
                " did not have any units, please try again with two armies containing units.");
    }

    /**
     * Checks if both armies in the battle contains units using the {@link Army#hasUnits()} method.
     * Is a help method used by:
     * {@link #unitAttacks(Army, Army)}
     * {@link #simulate()}
     * @return true if both armies have units, false otherwise
     *
     */
    private boolean battleIsActive(){
        return armyOne.hasUnits() && armyTwo.hasUnits();
    }

    /**
     * Checks if the unit which has taken a blow (defenderUnit) is dead
     * Is a help method utilized by:
     * {@link #unitAttacks(Army, Army)}
     * @param defenderUnit, is the unit which is checked to see if is dead after
     *                      being attacked
     *                      if this unit has health == 0 after the attack, it is
     *                      removed from its respective army
     */
    private void removeDefenderIfDead(Unit defenderUnit, Army defendingArmy){
        if(defenderUnit.getHealth() == 0) defendingArmy.remove(defenderUnit);
    }
    /**
     * Simulates an attack by a unit from attackingArmy on a unit from defendingArmy.
     * This method chooses the attacker and defender randomly from named armies, via {@link Army#getRandom()} method
     * of the Army-class. Then the method exercises {@link Unit#attack(Unit)} of the attackerUnit on the defenderUnit.
     * Finally, the method checks whether the defenderUnit, which has been attacked, is dead or not
     * If the defenderUnit is dead, then it is removed from its army
     * using the {@link Army#remove(Unit)} for objects of the Army-class
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
     * TODO: comment
     * @param attackingArmy
     * @param defendingArmy
     * @return
     */
    private ArrayList<Object> unitAttacksGetInfo(Army attackingArmy, Army defendingArmy){
        ArrayList<Object> returnInformation = new ArrayList<>();
        if(battleIsActive()){
            Unit attackerUnit = attackingArmy.getRandom();
            returnInformation.add(attackerUnit);
            Unit defenderUnit = defendingArmy.getRandom();
            returnInformation.add(defenderUnit);
            int healthBeforeAttack = defenderUnit.getHealth();
            attackerUnit.attack(defenderUnit);
            int healthAfterAttack = defenderUnit.getHealth();
            returnInformation.add(healthAfterAttack);
            returnInformation.add(healthBeforeAttack-healthAfterAttack);
            removeDefenderIfDead(defenderUnit, defendingArmy);
        }
        return returnInformation;
    }
    /**
     * Simulates a battle between armyOne and armyTwo. Attacks occur as while both armies have units.
     * The logic of attacking is turn-based, with attacks happening between a random units from the first attacking
     * army attacks a random unit from the other army. Then another random unit from the other army attacks a newly
     * chosen unit from the first army. This is one turn, in which each of the armies attacks. After each attack,
     * the damaged unit is checked for being dead by the {@link #removeDefenderIfDead(Unit, Army)} help method. If
     * it has died, the unit is removed from its army.
     * @return victorious army, which is the army with remaining units after finite turns
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

    /**
     * TODO: comment
     * @return
     */
    public ArrayList<Object> simulateTurnForGUI(){
        ArrayList<Object> returnInformation = new ArrayList<>();
        if(this.armyOneIsCommencingBattle){
            returnInformation.addAll(unitAttacksGetInfo(armyOne,armyTwo));
            returnInformation.add(this);
            if(!battleIsActive()) return returnInformation;
            returnInformation.addAll(unitAttacksGetInfo(armyTwo,armyOne));
            returnInformation.add(this);
        }else{
            returnInformation.addAll(unitAttacksGetInfo(armyTwo,armyOne));
            returnInformation.add(this);
            if(!battleIsActive()) return returnInformation;
            returnInformation.addAll(unitAttacksGetInfo(armyOne,armyTwo));
            returnInformation.add(this);
        }
        return returnInformation;
    }

    public Army getArmyOne() {
        return armyOne;
    }

    public Army getArmyTwo() {
        return armyTwo;
    }

    public void setArmyOne(Army armyOne) {
        this.armyOne = armyOne;
    }

    public void setArmyTwo(Army armyTwo) {
        this.armyTwo = armyTwo;
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
