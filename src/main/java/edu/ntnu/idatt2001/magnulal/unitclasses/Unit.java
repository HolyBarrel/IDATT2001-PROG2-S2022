package edu.ntnu.idatt2001.magnulal.unitclasses;
/**
 * Abstract superclass Unit describing commonalities for all unit-types
 * throughout 'Wargames'
 * The Unit superclass is inherited by the subclasses:
 * InfantryUnit
 * CavalryUnit --> which in turn is inherited by CommanderUnit
 * RangedUnit
 * @author Magnus Lutro Allison
 * @version 0.6 //TODO: ENDRE TIL PRIVATE STATIC VAR
 * @since 0.2
 */
public abstract class Unit {
    private final String name;
    private int health;
    private final int attack;
    private final int armor;

    /**
     * Constructor for the superclass Unit
     * @param name, is a String, cannot be left blank
     * @param health, is an int, cannot be less than 0
     * @param attack, is an int, above -1
     * @param armor, is an int, above -1
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *          logical input-range, or is blank
     */
    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if(name == null) throw new IllegalArgumentException("A unit's name cannot be inputted as 'null'," +
                " please try again.");
        if(name.isBlank()) throw new IllegalArgumentException("A unit's name cannot be inputted as an empty string, " +
                " please try again.");
        if(health < 0) throw new IllegalArgumentException("A unit's health must be a positive integer," +
                " please try again.");
        if(attack < 0) throw new IllegalArgumentException("A unit's attack must be a positive integer," +
                " please try again.");
        if(armor < 0) throw new IllegalArgumentException("A unit's armor must be a positive integer," +
                " please try again.");
        this.name = name.trim();
        this.setHealth(health);
        this.attack = attack;
        this.armor = armor;
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
