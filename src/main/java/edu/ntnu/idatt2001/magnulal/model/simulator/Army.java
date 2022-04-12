package edu.ntnu.idatt2001.magnulal.model.simulator;
import edu.ntnu.idatt2001.magnulal.model.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import edu.ntnu.idatt2001.magnulal.model.units.*;

import java.io.File;
import java.util.*;
/**
 * Class describing an army containing various types of units
 * The army has a String value for its name, and a list of all
 * Unit-subclass units in this army
 * @author Magnus Lutro Allison
 * @version 0.3
 * @since 0.1
 */
public class Army {
    private final String name;
    private final List<Unit> units;

    /**
     * Constructor 1 for the Army-class
     * @param name, is a String value, cannot be blank or of value 'null'
     * The constructor also creates an empty arraylist to hold units
     * @throws NullPointerException if the name parameter has the value of 'null'
     * @throws BlankStringException if the name parameter is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class

     */
    public Army(String name) throws NullPointerException, BlankStringException{
        checkLegalityOfNameString(name);
        this.name = name.trim();
        this.units = new ArrayList<>();
    }
    /**
     * Constructor 2 for the Army-class
     * @param name, is a non-blank String value
     * @param units, is a list-type with the List interface implemented, for example an
     *               ArrayList
     * @throws NullPointerException if the name parameter has the value of 'null'
     * @throws BlankStringException if the name parameter is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     * @throws IllegalArgumentException, if the name-input is a blank string, or if the list-type is not
     *          suitable for storing an army. This check is for simplifying what types of lists the application has to
     *          account for
     */
    public Army(String name, List<Unit> units) throws NullPointerException, BlankStringException,
            IllegalArgumentException {
        checkLegalityOfNameString(name);
        checkTypeOfList(units);
        this.name = name.trim();
        this.units = units;
    }
    /*
      Exception handling private methods
    */

    //TODO: comment

    /**
     *
     * @param name
     * @throws NullPointerException if the name parameter has the value of 'null'
     * @throws BlankStringException if the name parameter is either an empty string or consists of only
     *          white spaces. Utilizes the .blank() method of the String-class
     */
    private void checkLegalityOfNameString(String name) throws NullPointerException, BlankStringException {
        if(name == null) throw new NullPointerException("The name of the army was given the value 'null' as a " +
                "parameter, please try again.");
        if(name.isBlank()) throw new BlankStringException("The name for an army cannot be inputted as a" +
                " blank string, please try again.");
    }

    //TODO: comment
    private void checkTypeOfList(List<Unit> units) throws IllegalArgumentException {
        // checks that the inputted list is either an ArrayList or LinkedList. A Vector for example would throw
        if(!(units instanceof ArrayList || units instanceof LinkedList)) throw new IllegalArgumentException(
                "The inputted list-type must be either an arraylist, or a linked list, please try again.");
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
     * TODO: eval on shallow copy?
     * TODO: test on a linked list?
     */
    public void addAll(List<Unit> units) throws IllegalArgumentException {
        checkTypeOfList(units);
        for (Unit unit: units) {
            add(unit);
        }
    }

    /**
     * Remove-method that removes the inputted unit from the current
     * army list, is only used when the army contains this unit, and does not need a
     * check
     * @param unit, must be in the army, otherwise exception is thrown TODO: this is wrong?
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
     * Is only run on armies that has units, and does therefore not need a check
     */
    public Unit getRandom(){
        return units.get(getRandomListIndex());
    }

    /**
     * Help-method to specify a given List as an ArrayList.
     * Is used by the accessor methods below, with the intent of not resulting in exceptions if
     * the result-list is used to instantiate a new Army with the Army constructor.
     * The Army constructor requires for the list to either be an instance of ArrayList or LinkedList.
     * @param convertList, is the input list that is transfigured as an ArrayList.
     * @return ArrayList with elements of the list 'convertList'.
     * The method is only used
     */
    private ArrayList<Unit> toArrayList(List<Unit> convertList){
        return new ArrayList<>(convertList);
    }

    /**
     * Accessor-method to get a list of all InfantryUnits in this Army
     * The returned List is of type ArrayList
     * @return List of Units - implicating InfantryUnits
     */
    public List<Unit> getInfantryUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof InfantryUnit)
                .toList());
    }

    /**
     * Accessor method to get a list of all exclusive CavalryUnits in this Army,
     * meaning objects being only instance of CavalryUnit, and not of CommanderUnit
     * The returned List is of type ArrayList
     * @return List of Units - implicating exclusive CavalryUnits
     */
    public List<Unit> getCavalryUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof CavalryUnit && !(u instanceof CommanderUnit))
                .toList());
    }

    /**
     * Accessor method to get a list of all RangedUnits in this Army
     * The returned List is of type ArrayList
     * @return List of Units - implicating RangedUnits
     */
    public List<Unit> getRangedUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof RangedUnit)
                .toList());
    }

    /**
     * Accessor method to get a list of all CommanderUnits in this Army
     * The returned List is of type ArrayList
     * @return List of Units - implicating CommanderUnits
     */
    public List<Unit> getCommanderUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof CommanderUnit)
                .toList());
    }

    /**
     * Method to save the information of this army to a file at the given file path constructed
     * from the String parameter fileName, the file is created if none of that name already exists
     * in the resources root of this project. If the file already exists, the information it
     * contained previously is overwritten
     * @param fileName is a String for the name of the file
     *                 TODO: arent these throwing too?
     */
    public void saveThisArmyToFile(String fileName){
        FileManager.writeArmyToFileWFileName(fileName, this);
    }

    /**
     * Method to save the information of this army to an already created file.
     * If the file already has defined content, the previously saved information
     * is overwritten
     * @param file is an already created file
     */
    public void saveThisArmyToFile(File file){
        FileManager.writeArmyToFileWFile(file, this);
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
