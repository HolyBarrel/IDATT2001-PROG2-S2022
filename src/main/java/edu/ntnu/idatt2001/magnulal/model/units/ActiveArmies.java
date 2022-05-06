package edu.ntnu.idatt2001.magnulal.model.units;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;

/**
 * TODO COMMENT ALL
 * TODO: move to utils
 */
public class ActiveArmies { //TODO: make into twingleton
    private static Army activeArmy1;
    private static String activeArmy1Path;
    private static Army activeArmy2;
    private static String activeArmy2Path;

    public static String getActiveArmy1Path() {
        return activeArmy1Path;
    }

    public static void setActiveArmy1Path(String activeArmy1Path) {
        ActiveArmies.activeArmy1Path = activeArmy1Path;
    }

    public static String getActiveArmy2Path() {
        return activeArmy2Path;
    }

    public static void setActiveArmy2Path(String activeArmy2Path) {
        ActiveArmies.activeArmy2Path = activeArmy2Path;
    }

    public static Army getActiveArmy1() {
        return activeArmy1;
    }

    public static void setActiveArmy1(Army activeArmy1) {
        ActiveArmies.activeArmy1 = activeArmy1;
    }

    public static Army getActiveArmy2() {
        return activeArmy2;
    }

    public static void setActiveArmy2(Army activeArmy2) {
        ActiveArmies.activeArmy2 = activeArmy2;
    }
}
