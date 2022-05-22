package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * Abstract superclass Unit describing commonalities for all unit-types
 * throughout 'Wargames'
 * The Unit superclass is inherited by the subclasses:
 * InfantryUnit
 * CavalryUnit --> which in turn is inherited by CommanderUnit
 * RangedUnit
 * @author magnulal
 * @version 1.0
 * @since 0.1
 */
public abstract class Unit {
    private final String name;
    private int health;
    private final int attack;
    private final int armor;

    /**
     * Constructor for the superclass Unit, is used by subclass constructors which supers the
     * parameters to this constructor
     * @param name, is a String, cannot be left blank or be 'null'
     * @param health, is an int, cannot be less than 0
     * @param attack, is an int, cannot be less than 0
     * @param armor, is an int, cannot be less than 0
     * @throws NullPointerException if the name parameter has the value 'null'
     * @throws BlankStringException if the name argument is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws NegativeIntegerException if the integer value of health, attack or armor is less than zero
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

    /*
       Exception handling methods
     */

    /**
     * Verifies that the string parameter name is a legal argument. The method checks if the value of the name is
     * either 'null' or a blank string. If either of these checks are triggered, an exception is thrown.
     * @param name is a string
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
     * Verifies that the integer parameters are legal arguments. The method checks if any of the parameters
     * have negative integer values. If this occurs a NegativeIntegerException is thrown
     * @param health is an integer
     * @param attack is an integer
     * @param armor is an integer
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
     * Verifies that the ActiveTerrain has been associated with an enum of 'TerrainType' enums, since the
     * terrain-calculated bonuses cannot be validated with this stat having a default 'null'-value
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    private void checkTerrainStatus() throws NullPointerException {
        if(!ActiveTerrain.INSTANCE.isTerrainSet()) throw new NullPointerException("The terrain has not " +
                "been set to a valid terrain type, please make sure this is done before starting any simulation.");
    }

    /**
     * Attack method containing the formula given how the logic behind a unit's attack
     * The method gets the pre-given health of the opponent, subtracts this unit's attack and attackBonus,
     * additions the opponents armor and resistBonus,
     * and then sets the result as the new health of the @param opponent
     * @param opponent, is the opponent that is being attacked
     *                  The opponent's health is altered with its own mutator-method
     *                  setHealth
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
     * Returns the name of this Unit
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the health of this Unit
     * @return int health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the attack-stat of this Unit
     * @return int attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the armor-stat of this Unit
     * @return int armor
     */
    public int getArmor() {
        return armor;
    }
    /**
     * Alters the current health attribute of this Unit
     * This method is used by the {@link #attack(Unit)} method
     * @param health, is an integer, if the inputted integer is negative
     *                this represents an attack doing more damage than the defender has
     *                hit points. Then the health of this unit is set to zero
     *
     */
    public void setHealth(int health){
        if(health < 0) health = 0; //health cannot wind up negative
        this.health = health;
    }

    /**
     * Returns an integer value representing an attack bonus based on a unit's subclass in combination with current
     * terrain that has been set.
     * @param unit is the unit which is validated for its additional terrain attack bonus
     * @return an integer representing the buffing/de-buffing bonus
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    protected int getTerrainAttackBonus(Unit unit) throws NullPointerException {
        checkTerrainStatus();
        switch (ActiveTerrain.INSTANCE.getActiveTerrain()) {
            case FOREST -> {
                if (unit instanceof InfantryUnit) {
                    return 3;
                }
                if (unit instanceof RangedUnit) {
                    return -1;
                }
            }
            case HILL -> {
                if (unit instanceof RangedUnit) {
                    return 4;
                }
            }
            case PLAINS -> {
                if (unit instanceof CavalryUnit) {
                    return 4;
                }
            }
        }
        return 0;
    }

    /**
     * Returns an integer value representing a defense bonus based on a unit's subclass in combination with current
     * terrain that has been set.
     * @param unit is the unit which is validated for its additional terrain defense bonus
     * @return an Integer representing the buffing/de-buffing bonus. Has return-type 'Integer' to
     *  access the possibility of returning a 'null'-value, which informs the CavalryUnit's
     *  {@link CavalryUnit#getResistBonus()} method to return zero for its resist bonus.
     * @throws NullPointerException if the current active terrain of the program has not been set, meaning it is of
     * 'null'-value
     */
    protected Integer getTerrainDefenseBonus(Unit unit) throws NullPointerException {
        checkTerrainStatus();
        if (ActiveTerrain.INSTANCE.getActiveTerrain() == FOREST) {
            if (unit instanceof InfantryUnit) {
                return 3;
            }
            if (unit instanceof CavalryUnit) {
                return null;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String format = "| %1$-20s %2$-18s %3$-14s %4$-12s %5$-12s";
        return String.format(
                format, "Name: '"   + name + "'", "Type: '" + this.getClass().getSimpleName() + "'" , " Health: " + health,
                "Attack: " + attack, "Armor: "  + armor);
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
