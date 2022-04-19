package edu.ntnu.idatt2001.magnulal.utils;

/**
 * Singleton designed enum to assure the program that a single terrain type is considered
 * the 'ActiveTerrain' at any given point
 */
public enum ActiveTerrain {
    //Enum with commutable TerrainType enum value associated
    INSTANCE;
    private TerrainType activeTerrain;

    /**
     * Sets the activeTerrain associated with the enum 'INSTANCE' to a given enum of 'TerrainType'
     * @param terrainInstance is an enum of the TerrainType enums
     */
    public void setActiveTerrain(TerrainType terrainInstance){
        activeTerrain = terrainInstance;
    }

    /**
     * Returns the enum currently associated with this INSTANCE-enum
     * @return an enum of TerrainType
     */
    public TerrainType getActiveTerrain(){
        return activeTerrain;
    }

    /**
     * Returns a boolean representing whether the enum value associated with this 'INSTANCE' enum has been set
     * @return true if terrain is set (is not 'null'), false otherwise
     */
    public boolean isTerrainSet(){
        return activeTerrain != null;
    }
}
