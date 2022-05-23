package edu.ntnu.idatt2001.magnulal.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ActiveTerrainTest {
    @Nested
    @DisplayName("Positive tests for the enum singleton 'ActiveTerrain' methods")
    public class MethodsReturnsAsExpected {
        @Test
        @DisplayName("rotateTerrain rotates the active terrain to the 'next' terrain")
        public void rotateTerrainSwitchesActiveTerrainCorrectly() {
            ActiveTerrain.INSTANCE.setActiveTerrain(TerrainType.FOREST);
            ActiveTerrain.INSTANCE.rotateTerrain(1);
            TerrainType secondTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            ActiveTerrain.INSTANCE.rotateTerrain(1);
            TerrainType thirdTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            ActiveTerrain.INSTANCE.rotateTerrain(1);
            TerrainType fourthTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            assertEquals(TerrainType.HILL, secondTerrain);
            assertEquals(TerrainType.PLAINS, thirdTerrain);
            assertEquals(TerrainType.FOREST, fourthTerrain);
        }
        @Test
        @DisplayName("rotateTerrain rotates the active terrain to the 'previous' terrain")
        public void rotateTerrainSwitchesActiveTerrainCorrectlyWithOtherIntValue() {
            ActiveTerrain.INSTANCE.setActiveTerrain(TerrainType.FOREST);
            ActiveTerrain.INSTANCE.rotateTerrain(-1);
            TerrainType secondTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            ActiveTerrain.INSTANCE.rotateTerrain(-1);
            TerrainType thirdTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            ActiveTerrain.INSTANCE.rotateTerrain(-1);
            TerrainType fourthTerrain = ActiveTerrain.INSTANCE.getActiveTerrain();
            assertEquals(TerrainType.PLAINS, secondTerrain);
            assertEquals(TerrainType.HILL, thirdTerrain);
            assertEquals(TerrainType.FOREST, fourthTerrain);
        }
    }
}
