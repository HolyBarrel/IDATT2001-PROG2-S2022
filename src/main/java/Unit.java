/**
 * Abstract superclass Unit describing commonalities for all unit-types
 * throughout 'Wargames'
 * The Unit superclass is inherited by the subclasses:
 * InfantryUnit
 * CavalryUnit --> which in turn is inherited by CommanderUnit
 * RangedUnit
 * This class is currently set to be tested by its subclasses
 *
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 12.02.2022
 */
public abstract class Unit {
    private final String NAME;
    private int health;
    private final int ATTACK;
    private final int ARMOR;

    /**
     * Constructor for the superclass Unit
     * @param NAME, is a String, cannot be left blank
     * @param health, is an int, cannot be less than 0
     * @param ATTACK, is an int, above -1
     * @param ARMOR, is an int, above -1
     * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
     *          logical input-range, or is blank
     */
    public Unit(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
        if(NAME.isBlank()) throw new IllegalArgumentException("A unit's name cannot be inputted as an empty string, " +
                " please try again.");
        //Health is checked in set-method
        if(ATTACK < 0) throw new IllegalArgumentException("A unit's attack must be a positive integer," +
                " please try again.");
        if(ARMOR < 0) throw new IllegalArgumentException("A unit's armor must be a positive integer," +
                " please try again.");
        this.NAME = NAME.trim();
        this.setHealth(health);
        this.ATTACK = ATTACK;
        this.ARMOR = ARMOR;
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
        //TODO: later ADD if(this.ATTACK + this.getAttackBonus() >=
        // opponent.getHealth() + (opponent.getArmor() + opponent.getResistBonus())){
        // --> delete / kill -> (Unit opponent){...}
        //IF::attackFAIL
        opponent.setHealth(opponent.getHealth()
                - (this.ATTACK + this.getAttackBonus())
                + (opponent.getARMOR() + opponent.getResistBonus()));
        if(opponent.getHealth() < 0) opponent.setHealth(0);
        //TODO: in battle-class - check if the unit has health == 0
        //if(health == 0) army.remove(opponent)
    }

    /**
     * Accessor method that returns the name of this Unit
     * @return String NAME
     */
    public String getNAME() {
        return NAME;
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
     * @return int ATTACK
     */
    public int getATTACK() {
        return ATTACK;
    }

    /**
     * Accessor method that returns the armor-quantity of this Unit
     * @return int ARMOR
     */
    public int getARMOR() {
        return ARMOR;
    }
    /**
     * Mutator method that alters the current health attribute of this Unit
     * @param health, is a positive integer
     * @throws IllegalArgumentException if the integer input is less than zero
     */
    public void setHealth(int health) throws IllegalArgumentException{
        if(health < 0) throw new IllegalArgumentException("A unit's health must be a positive integer," +
                " please try again.");
        this.health = health;
    }

    @Override
    public String toString() {
        String format = "| %1$-20s %2$-12s %3$-12s %4$-12s";
        return String.format(
                format, "Name: '"   + NAME + "'", "Health: " + health, "Attack: " + ATTACK, "Armor: "  + ARMOR);
    }

    /**
     * Abstract method shell to force a unique attack-bonus method for each of
     * the subclasses of Unit
     * @return int attackBonus
     */
    public abstract int getAttackBonus();

    /**
     * Abstract method shell to force a unique resist-bonus method for each of
     * the subclasses of Unit
     * @return int resistBonus
     */
    public abstract int getResistBonus();

}
