package edu.ntnu.idatt2001.magnulal.utils;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * Singleton designed enum to assure the program that a single terrain type is considered
 * the 'ActiveTerrain' at any given point
 * //TODO implement version etc on all of these
 * TODO test
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

    /**
     * TODO COMMENT
     * @param direction
     * @return
     */
    public void rotateTerrain(int direction){
        if(direction == 1){
            switch (ActiveTerrain.INSTANCE.getActiveTerrain()){
                case FOREST -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
                }
                case HILL -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
                }
                case PLAINS -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
                }
            }
        }else{
            switch (ActiveTerrain.INSTANCE.getActiveTerrain()){
                case FOREST -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
                }
                case HILL -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
                }
                case PLAINS -> {
                    ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
                }
            }
        }
    }
}
