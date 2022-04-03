package edu.ntnu.idatt2001.magnulal.unitclasses;

/**
 * Enum to represent all the types of units in this project
 * the enum has attached string values corresponding to each unit type's
 * simpleName written as a string
 */
public enum UnitTypes {
    //Enums
    INFANTRY("InfantryUnit"),CAVALRY("CavalryUnit"),COMMANDER("CommanderUnit"),RANGED("RangedUnit");
    private final String unitType;

    /**
     * Private constructor for the enum
     * cannot be reached by other classes than this
     * @param unitType, is a string attached to the enum
     */
    UnitTypes(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Static method to make enum easily usable with enhanced switch
     * see FileManager 'readArmyFromFile'
     * @param searchString is a string value which if matched with any of the enum's string-attached
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
