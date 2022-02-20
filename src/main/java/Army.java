import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
     * Add-method to add a multiple UNITS to the list with UNITS in this
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
     * @throws UnsupportedOperationException if the unit tried for removal is not in the army
     */
    public void remove(Unit unit) throws Exception{
        if(!this.UNITS.contains(unit)) throw new Exception("The given unit is not in the army and " +
                "therefore cannot be removed, please try again.");
        if(!hasUnits()) throw new Exception("The getRandom method cannot return a random unit " +
                "from an empty army list, please try again.");
        this.UNITS.remove(unit);
    }

    /**
     * Private help-method that returns the size of this army
     * @return integer value UNITS.size()
     */
    private int getArmySize(){
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
     * Accessor-method that returns a random unit from the army list
     * @return Unit (at random index in the army list)
     * @throws Exception if the army list is empty
     */
    public Unit getRandom() throws Exception{
        if(!hasUnits()) throw new Exception("The getRandom method cannot return a random unit " +
                "from an empty army list, please try again.");
        int indexInArmySize = new Random().nextInt(getArmySize());
        return UNITS.get(indexInArmySize);
    }

    //TODO: add stringbuilder
    @Override
    public String toString() {
        return "Army{" +
                "NAME='" + NAME + '\'' +
                ", UNITS=" + UNITS +
                '}';
    }

    //TODO: add equals
    //TODO: add hashCode
}
