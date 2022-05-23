package edu.ntnu.idatt2001.magnulal.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTypesTest {
    @Nested
    @DisplayName("Positive tests for the enum 'TerrainTypes' methods")
    public class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Correct matched enum is returned")
        public void getValueMatchingReturnsCorrectForAllEnums() {
            assertEquals(UnitTypes.INFANTRY, UnitTypes.getValueMatching("InfantryUnit"));
            assertEquals(UnitTypes.CAVALRY, UnitTypes.getValueMatching("CavalryUnit"));
            assertEquals(UnitTypes.COMMANDER, UnitTypes.getValueMatching("CommanderUnit"));
            assertEquals(UnitTypes.RANGED, UnitTypes.getValueMatching("RangedUnit"));
        }

        @Test
        @DisplayName("'null' is returned if enum is not matched")
        public void getValueMatchingReturnsNull() {
            assertNull(UnitTypes.getValueMatching("WranglerUnit"));
        }
    }
}
