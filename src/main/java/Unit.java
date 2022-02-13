/**
 * Abstract superclass Unit describing commonalities for all unit-types
 * throughout 'Wargames'
 * The Unit superclass is inherited by the subclasses:
 * InfantryUnit
 * CavalryUnit
 * RangedUnit
 *
 * @author Magnus Lutro Allison
 * @version 0.1
 * @since 12.02.2022
 */
public abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armor;


    /**
     * Constructor for the superclass Unit
     * @param name, is a String, cannot be left blank
     * @param health, is an int, cannot drop to less than 0
     * @param attack, is an int, above -1
     * @param armor, is an int, above -1
     */
    public Unit(String name, int health, int attack, int armor) { //throws...
        //add exception handling here
        this.name = name; // cannot be blank
        this.health = health; // include !< 0 eventually here || use set-method
        this.attack = attack;
        this.armor = armor;
    }

}
