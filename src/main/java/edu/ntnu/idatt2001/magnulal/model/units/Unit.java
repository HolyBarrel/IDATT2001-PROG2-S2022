package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.model.exceptions.NegativeIntegerException;

/**
 * Abstract superclass Unit describing commonalities for all unit-types
 * throughout 'Wargames'
 * The Unit superclass is inherited by the subclasses:
 * InfantryUnit
 * CavalryUnit --> which in turn is inherited by CommanderUnit
 * RangedUnit
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 0.1
 */
public abstract class Unit {
    private final String name;
    private int health;
    private final int attack;
    private final int armor;

    /**
     * Constructor for the superclass Unit
     * @param name, is a String, cannot be left blank or be 'null'
     * @param health, is an int, cannot be less than 0
     * @param attack, is an int, cannot be less than 0
     * @param armor, is an int, cannot be less than 0
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     * //TODO: should it be possible to instantiate a unit with 0 health
     */
    public Unit(String name, int health, int attack, int armor) throws NullPointerException, BlankStringException,
            NegativeIntegerException {
        checkLegalityOfNameString(name);
        checkLegalityOfIntegers(health, attack, armor);
        this.name = name.trim();
        this.setHealth(health);
        this.attack = attack;
        this.armor = armor;
    }

//TODO: check if all exceptions are handled
    //TODo: correct for TDos in sticky notes!
    //TODO: create own exceptions =)

    /*
       Exception handling methods
     */
    /**
     * TODO: comment
     * @param name
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     */
    private void checkLegalityOfNameString(String name) throws NullPointerException, BlankStringException {
        if(name == null) throw new NullPointerException("A unit's name cannot be inputted as 'null'," +
                " please try again.");
        if(name.isBlank()) throw new BlankStringException("A unit's name cannot be inputted as a blank string, " +
                " please try again.");
    }

    /**
     * TODO: comment
     * @param health
     * @param attack
     * @param armor
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
     */
    private void checkLegalityOfIntegers(int health, int attack, int armor) throws NegativeIntegerException {
        if(health < 0) throw new NegativeIntegerException("A unit cannot be instantiated with a negative integer " +
                "as parameter, please try again.");
        if(attack < 0) throw new NegativeIntegerException("A unit's attack must be a positive integer," +
                " please try again.");
        if(armor < 0) throw new NegativeIntegerException("A unit's armor must be a positive integer," +
                " please try again.");
    }
    /**
     * Attack method containing the formula given how the logic behind a unit's attack
     * is in the game
     * @param opponent, is the opponent that is being attacked
     *                  The opponent's health is altered with its own mutator-method
     *                  setHealth
     *                  The logic gets the pre-given health of the opponent,
     *                  subtracts this unit's attack and attackBonus,
     *                  additions the opponents armor and resistBonus,
     *                  and then sets the result as the new health
     *                  of the @param opponent
     */
    public void attack(Unit opponent){
        //An integer value for total damage is initialized
        //Represents the damage-action
        int damage = this.attack + this.getAttackBonus();
        //An integer value for total opponent protection is initialized
        //Represents the defence-action
        int protection = opponent.getArmor() + opponent.getResistBonus();
        //The health of the opponent only needs to be updated if the attack does damage
        //Represents the outcome of the damage- and defence-actions
        if(protection < damage) opponent.setHealth(opponent.getHealth() - damage + protection);
    }

    /**
     * Accessor method that returns the name of this Unit
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method that returns the health of this Unit
     * @return int health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Accessor method that returns the attack-quantity of this Unit
     * @return int attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Accessor method that returns the armor-quantity of this Unit
     * @return int armor
     */
    public int getArmor() {
        return armor;
    }
    /**
     * Mutator method that alters the current health attribute of this Unit
     * @param health, is an integer, if the inputted integer is negative
     *                this represents an attack doing more damage than the defender has
     *                hit points. Then the health of this unit is set to zero
     *
     */
    public void setHealth(int health){
        if(health < 0) health = 0; //health cannot wind up negative
        this.health = health;
    }

    @Override
    public String toString() {
        String format = "| %1$-20s %2$-12s %3$-12s %4$-12s";
        return String.format(
                format, "Name: '"   + name + "'", "Health: " + health, "Attack: " + attack, "Armor: "  + armor);
    }

    /**
     * Abstract method shell to force a unique attack-bonus method for each of
     * the subclasses of Unit
     * @return int attackBonus
     */
    protected abstract int getAttackBonus();

    /**
     * Abstract method shell to force a unique resist-bonus method for each of
     * the subclasses of Unit
     * @return int resistBonus
     */
    protected abstract int getResistBonus();
}
