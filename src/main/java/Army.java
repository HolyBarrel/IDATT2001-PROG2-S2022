import java.util.*;

/**
 * Class describing an army containing various types of units
 * The army has a String value for its name, and a list of all
 * Unit-subclass units in this army
 * @author Magnus Lutro Allison
 * @version 0.4
 */
public class Army {
    private final String name;
    private final List<Unit> units;

    /**
     * Constructor 1 for the Army-class
     * @param name, is a non-blank String value
     * The constructor also creates an empty arraylist to hold units
     * @throws IllegalArgumentException, if the name-input is a blank string
     */
    public Army(String name) throws IllegalArgumentException{
        if(name.isBlank()) throw new IllegalArgumentException("The name for an army cannot be inputted as an" +
                " empty string, please try again.");
        this.name = name.trim();
        this.units = new ArrayList<>();
    }
    /**
     * Constructor 2 for the Army-class
     * @param name, is a non-blank String value
     * @param units, is a list-type with the List interface implemented, for example an
     *               ArrayList
     * @throws IllegalArgumentException, if the name-input is a blank string, or if the list-type is not
     * suitable for storing an army
     */
    public Army(String name, List<Unit> units) throws IllegalArgumentException{
        if(name.isBlank()) throw new IllegalArgumentException("The name for an army cannot be inputted as an" +
                " empty string, please try again.");
        if(!(units instanceof ArrayList || units instanceof LinkedList)) throw new IllegalArgumentException(
                "The inputted list-type must be either an arraylist, or a linked list, please try again.");
        this.name = name.trim();
        this.units = units;
    }

    /**
     * Accessor method to get the private attribute 'name' of this army
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Add-method to add the parameter inputted unit to the list with
     * units in this army
     * @param unit, an instance of a subclass of the Unit-class
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * Add-method to add multiple units to the list with units in this
     * army
     * @param units, is a list with units
     * @throws IllegalArgumentException, if the list-type is not
     * suitable
     */
    public void addAll(List<Unit> units) throws IllegalArgumentException{
        if(!(units instanceof ArrayList || units instanceof LinkedList)) throw new IllegalArgumentException(
                "The inputted list-type must be either an arraylist, or a linked list, please try again.");
        for (Unit unit: units) {
            add(unit);
        }
    }

    /**
     * Remove-method that removes the inputted unit from the current
     * army list
     * @param unit, must be in the army, otherwise exception is thrown
     */
    public void remove(Unit unit){ //could implement illegalArgumentEx, but this is handled in the simulation
        this.units.remove(unit);
    }

    /**
     * Returns the size of this army
     * @return integer value units.size()
     */
    public int getArmySize(){
        return this.units.size();
    }
    /**
     * Boolean return method that returns true if the list for an
     * army contains units
     * @return boolean value
     */
    public boolean hasUnits(){
        return getArmySize() > 0;
    }

    /**
     * Accessor-method that returns the list of this army as a whole
     * @return List<Unit> list-implementation
     */
    public List<Unit> getAllUnits(){
        return this.units;
    }

    /**
     * Help-method to generate a random index integer within the range:
     * 0 to army size-1
     * @return a random integer of the list
     */
    private int getRandomListIndex(){
        return new Random().nextInt(getArmySize());
    }

    /**
     * Accessor-method that returns a random unit from the army list
     * @return Unit (at random index in the army list)
     * @throws NullPointerException if the army list is empty
     */
    public Unit getRandom(){
        return units.get(getRandomListIndex());
    }

    @Override
    public String toString() {
        String underLine = "\n|_______________________________________________________________\n";
        StringBuilder sb = new StringBuilder(underLine).append("| Units of the army: '").append(getName()).append("'");
        for(Unit unit: getAllUnits()){
            sb.append(underLine);
            sb.append(unit.toString());
        }
        sb.append(underLine);
        sb.append("'").append(getName()).append("' is an army with ").append(getArmySize()).append(" total units");
        return String.valueOf(sb);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
