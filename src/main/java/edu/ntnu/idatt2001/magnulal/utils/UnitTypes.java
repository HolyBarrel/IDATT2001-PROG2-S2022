package edu.ntnu.idatt2001.magnulal.utils;

import java.util.Arrays;

/**
 * Enum to represent all the types of units in this project
 * the enum has attached string values corresponding to each unit subclass'
 * simpleName written as a string
 * @author magnulal
 * @version 1.0
 * @since 0.2
 */
public enum UnitTypes {
    //Enums
    INFANTRY("InfantryUnit"),
    CAVALRY("CavalryUnit"),
    COMMANDER("CommanderUnit"),
    RANGED("RangedUnit");
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
     * @param nameString is a string value which is matched with any of the enum's string-attached
     *                     string which returns the enum type
     * @return enum value corresponding with nameString if found, and 'null' otherwise
     */
    public static UnitTypes getValueMatching(String nameString){
        for (UnitTypes type: values()) {
            if(type.unitType.equals(nameString)){
                return type;
            }
        }
        return null;
    }
}
