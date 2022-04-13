package edu.ntnu.idatt2001.magnulal.model.units;

/**
 * Enum to represent all the types of units in this project
 * the enum has attached string values corresponding to each unit subclass'
 * simpleName written as a string
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.2
 */
public enum UnitTypes {
    //Enums
    INFANTRY("InfantryUnit"),CAVALRY("CavalryUnit"),COMMANDER("CommanderUnit"),RANGED("RangedUnit");
    private final String unitType;

    /**
     * Constructs an enum of 'UnitTypes'
     * This constructor cannot be reached by other classes since it is 'private'
     * @param unitType, is a string attached to the enum
     */
    UnitTypes(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Static method to make enum easily usable with enhanced switch
     * see {@link edu.ntnu.idatt2001.magnulal.utils.FileManager#readArmyFromFile(String)}
     * @param searchString is a string value which matched with any of the enum's string-attached
     *                     string returns the enum value
     * @return enum value corresponding with searchString if found, and 'null' otherwise
     */
    public static UnitTypes getValueMatching(String searchString){
        for (UnitTypes t: values()) {
            if(t.unitType.equals(searchString)){
                return t;
            }
        }
        return null;
    }
}
