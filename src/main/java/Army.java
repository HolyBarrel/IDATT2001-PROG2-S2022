import java.util.*;

/**
 * Class describing an army containing various types of UNITS
 * The army has a String value for its name, and a list of all
 * Unit-subclass UNITS in this army
 */
public class Army {
    private final String NAME;
    private final List<Unit> UNITS;

    /**
     * Constructor 1 for the Army-class
     * @param NAME, is a non-blank String value
     * The constructor also creates an empty arraylist to hold UNITS
     * @throws IllegalArgumentException, if the name-input is a blank string
     */
    public Army(String NAME) throws IllegalArgumentException{
        if(NAME.isBlank()) throw new IllegalArgumentException("The name for an army cannot be inputted as an" +
                " empty string, please try again.");
        this.NAME = NAME.trim();
        this.UNITS = new ArrayList<>();
    }
    /**
     * Constructor 2 for the Army-class
     * @param NAME, is a non-blank String value
     * @param units, is a list-type with the List interface implemented, for example an
     *               ArrayList
     * @throws IllegalArgumentException, if the name-input is a blank string, or if the list-type is not
     * suitable for storing an army
     */
    public Army(String NAME, List<Unit> units) throws IllegalArgumentException{
        if(NAME.isBlank()) throw new IllegalArgumentException("The name for an army cannot be inputted as an" +
                " empty string, please try again.");
        if(!(units instanceof ArrayList || units instanceof LinkedList)) throw new IllegalArgumentException(
                "The inputted list-type must be either an arraylist, or a linked list, please try again.");
        this.NAME = NAME.trim();
        this.UNITS = units;
    }

    /**
     * Accessor method to get the private attribute 'NAME' of this army
     * @return String NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Add-method to add the parameter inputted unit to the list with
     * UNITS in this army
     * @param unit, an instance of a subclass of the Unit-class
     */
    public void add(Unit unit){
        this.UNITS.add(unit);
    }

    /**
     * Add-method to add multiple UNITS to the list with UNITS in this
     * army
     * @param units, is a list with UNITS
     * @throws IllegalArgumentException, if the list-type is not
     * suitable for storing an army
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
     * @throws IllegalArgumentException if the unit tried for removal is not in the army
     */
    public void remove(Unit unit) throws IllegalArgumentException{
        if(!this.UNITS.contains(unit)) throw new IllegalArgumentException("The given unit is not in the army and " +
                "therefore cannot be removed, please try again.");
        this.UNITS.remove(unit);
    }

    /**
     * Returns the size of this army
     * @return integer value UNITS.size()
     */
    public int getArmySize(){
        return this.UNITS.size();
    }
    /**
     * Boolean return method that returns true if the list for an
     * army contains UNITS
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
        return this.UNITS;
    }

    /**
     * Method to generate a random index integer within the range:
     * 0 to [army size-1]
     * @return a random integer of the list
     */
    //TODO: REVISE later
    public int getRandomListIndex(){
        return new Random().nextInt(getArmySize());
    }

    /**
     * Accessor-method that returns a random unit from the army list
     * @return Unit (at random index in the army list)
     * @throws NullPointerException if the army list is empty
     */
    public Unit getRandom() throws NullPointerException{
        if(!hasUnits()) throw new NullPointerException("The getRandom method cannot return a random unit " +
                "from an empty army list, please try again.");
        return UNITS.get(getRandomListIndex());
    }

    @Override
    public String toString() {
        String underLine = "\n|_______________________________________________________________\n";
        StringBuilder sb = new StringBuilder(underLine).append("| Units of the army: '").append(getNAME()).append("'");
        for(Unit unit: getAllUnits()){
            sb.append(underLine);
            sb.append(unit.toString());
        }
        sb.append(underLine);
        sb.append("'").append(getNAME()).append("' is an army with ").append(getArmySize()).append(" total units");
        return String.valueOf(sb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(NAME, army.NAME) && Objects.equals(UNITS, army.UNITS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME, UNITS);
    }
}
