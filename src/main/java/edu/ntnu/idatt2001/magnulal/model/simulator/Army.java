package edu.ntnu.idatt2001.magnulal.model.simulator;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import edu.ntnu.idatt2001.magnulal.model.units.*;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class describing an army containing various types of units
 * The army has a String value for its name, and a list of all
 * Unit-subclass units in this army
 * @author magnulal
 * @version 1.0
 * @since 0.1
 */
public class Army {
    private final String name;
    private final List<Unit> units;

    /**
     * Constructs an Army utilizing only a string for its name, and sets the units list to an empty arraylist.
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
     * Constructs an Army with a string for its name and a list for the units that are in the army
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
        this.units = units; //TODO: Deep copy???
    }
    /*
      Exception handling private methods
    */

    /**
     * Verifies the string parameter's legality by checking if it is either 'null', or
     * only a blank string. Exceptions are thrown if the checks are triggered.
     * @param name is a string checked by this private method
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

    /**
     * Checks if the list is of an accepted type, implying either an arraylist or linkedList.
     * Utilizes 'instanceof' to verify the list type. An exception is thrown if the check is triggered.
     * @param units is a List with units which is verified
     * @throws IllegalArgumentException, if the list-type is not an arraylist nor linked list
     */
    private void checkTypeOfList(List<Unit> units) throws IllegalArgumentException {
        // checks that the inputted list is either an ArrayList or LinkedList. A Vector for example would throw
        if(!(units instanceof ArrayList || units instanceof LinkedList)) throw new IllegalArgumentException(
                "The inputted list-type must be either an arraylist, or a linked list, please try again.");
    }

    /**
     * Returns the private attribute 'name' of this army
     * @return name, which is a String
     */
    public String getName() {
        return name;
    }

    /**
     * Adds the parameter  unit to the list of units in this army
     * @param unit, an instance of a subclass of the Unit-class
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * Adds multiple units to the list with units in this army
     * @param units, is a list with units
     * @throws IllegalArgumentException, if the list-type is not an arraylist nor a linked list
     * TODO: test on a linked list?
     */
    public void addAll(List<Unit> units) throws IllegalArgumentException {
        checkTypeOfList(units);
        for (Unit unit: units) {
            add(unit);
        }
    }

    /**
     * Removes the inputted unit from the current army list.
     * Is only used when the army contains this unit, inferring that the methods does not need
     * to check if the unit is in the list of this army
     * @param unit, is of the class 'Unit'
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
     * Returns a boolean representing the presence of units in this army.
     * The method returns true if the list contains units, and false otherwise
     * @return boolean value,
     * true -> army has units
     * false -> army does not have units
     */
    public boolean hasUnits(){
        return getArmySize() > 0;
    }

    /**
     * Returns the list of this army as a whole
     * @return list of units associated with this army
     */
    public List<Unit> getAllUnits(){
        return this.units;
    }

    /**
     * Generates a random integer within the range: 0 to army size-1
     * Is a help method for the {@link #getRandom()}
     * @return a random integer corresponding with an index of the list
     */
    private int getRandomListIndex(){
        return new Random().nextInt(getArmySize());
    }

    /**
     * Returns a random unit from the army list using the {@link java.util.Random} class
     * @return Unit (at random index position in the army list)
     * Is only run on armies that has units
     */
    public Unit getRandom(){
        return units.get(getRandomListIndex());
    }

    /**
     * Specifies a given List as an ArrayList.
     * Is used by the accessor methods:
     * {@link #getInfantryUnits()},
     * {@link #getCavalryUnits()},
     * {@link #getRangedUnits()},
     * {@link #getCommanderUnits()}
     * TODO: update when more unit types are implemented
     * The intent is to not result in exceptions if the result-list is used to instantiate a new Army
     * with the Army constructor. The Army constructor requires for the list to either be an instance
     * of ArrayList or LinkedList.
     * @param convertList, is the input list that is transfigured as an ArrayList.
     * @return ArrayList with elements of the list 'convertList'
     */
    private ArrayList<Unit> toArrayList(List<Unit> convertList){
        return new ArrayList<>(convertList);
    }

    /**
     * Returns a list containing all InfantryUnits in this Army
     * The returned List is of type ArrayList
     * @return arraylist of InfantryUnits
     */
    public List<Unit> getInfantryUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof InfantryUnit)
                .collect(Collectors.toList()));
    }

    /**
     * Returns a list of all exclusive CavalryUnits in this Army,
     * The returned arraylist has objects that are only instance of CavalryUnit, and not of CommanderUnit
     * @return arraylist of CavalryUnits
     */
    public List<Unit> getCavalryUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof CavalryUnit && !(u instanceof CommanderUnit))
                .collect(Collectors.toList()));
    }

    /**
     * Returns a list with all RangedUnits of this Army
     * The returned List is of type ArrayList
     * @return List of RangedUnits
     */
    public List<Unit> getRangedUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof RangedUnit)
                .collect(Collectors.toList()));
    }

    /**
     * Returns a list with all CommanderUnits of this Army
     * The returned List is of type ArrayList
     * @return List of CommanderUnits
     */
    public List<Unit> getCommanderUnits(){
        return toArrayList(units.stream()
                .filter(u -> u instanceof CommanderUnit)
                .collect(Collectors.toList()));
    }

    /**
     * Saves the information of this army to a file at the given file path constructed
     * from the String parameter fileName, the file is created if none of that name already exists
     * in the resources root of this project. If the file already exists, the information it
     * contained previously is overwritten
     * @param fileName is a String for the name of the file
     * @throws InvalidPathException if the constructed file path is invalid. Is caused by special characters
     * like '?' in the fileName
     */
    public void saveThisArmyToFile(String fileName) throws InvalidPathException{
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
        StringBuilder sb = new StringBuilder("\n _______________________________________________________________\n")
                .append("| Units of the army: '").append(getName()).append("'");
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
