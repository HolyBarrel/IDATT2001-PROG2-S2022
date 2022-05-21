package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.model.units.Unit;

/**
 * Enum containing all allowed TerrainTypes in Wargames
 * These different TerrainTypes aid in creating variation in buffing units with terrain bonuses
 * To further see the logic, see {@link edu.ntnu.idatt2001.magnulal.model.units.Unit} for methods calculating
 * these bonuses
 * @author magnulal
 * @version 1.0
 * @since 0.3
 */
public enum TerrainType {
    FOREST,
    HILL,
    PLAINS
}
