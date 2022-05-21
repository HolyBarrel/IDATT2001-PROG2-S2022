package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;

/**
 * Global class holding information about the currently active armies of the Wargames application.
 * This class is essential to the graphical user interface because ActiveArmies is used to store information
 * about the two accessible armies with their corresponding paths at runtime. Functions as a temporary storage
 * class during runtime.
 */
public class ActiveArmies {
    private static Army activeArmy1;
    private static String activeArmy1Path;
    private static Army activeArmy2;
    private static String activeArmy2Path;

    /**
     * Private constructor to make object instantiation of this class inaccessible
     */
    private ActiveArmies(){}

    /**
     * Returns the string path of the first active army.
     * @return is a string path
     */
    public static String getActiveArmy1Path() {
        return activeArmy1Path;
    }

    /**
     * Sets the string path of the first active army to the parameter string
     * @param activeArmy1Path is a String path
     */
    public static void setActiveArmy1Path(String activeArmy1Path) {
        ActiveArmies.activeArmy1Path = activeArmy1Path;
    }

    /**
     * Returns the string path of the second active army
     * @return string activeArmy2Path
     */
    public static String getActiveArmy2Path() {
        return activeArmy2Path;
    }

    /**
     * Sets the string path of the second active army to the parameter string
     * @param activeArmy2Path is a String path
     */
    public static void setActiveArmy2Path(String activeArmy2Path) {
        ActiveArmies.activeArmy2Path = activeArmy2Path;
    }

    /**
     * Returns the first active army
     * @return Army active army one
     */
    public static Army getActiveArmy1() {
        return activeArmy1;
    }

    /**
     * Sets the first active army to the army parameter
     * @param activeArmy1 is an Army
     */
    public static void setActiveArmy1(Army activeArmy1) {
        ActiveArmies.activeArmy1 = activeArmy1;
    }

    /**
     * Returns the second active army
     * @return Army active army two
     */
    public static Army getActiveArmy2() {
        return activeArmy2;
    }

    /**
     * Sets the second active army to the army parameter
     * @param activeArmy2 is an Army
     */
    public static void setActiveArmy2(Army activeArmy2) {
        ActiveArmies.activeArmy2 = activeArmy2;
    }
}
