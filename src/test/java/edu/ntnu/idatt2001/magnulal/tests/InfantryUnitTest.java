package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.units.InfantryUnit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import org.junit.jupiter.api.*;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;
import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
    private InfantryUnit testInfantryUnit;

    @BeforeEach
    public void initiateTestInfantryUnit(){
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        this.testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
    }
    @AfterEach
    public void clearTerrain(){
        ActiveTerrain.INSTANCE.setActiveTerrain(null);
    }
    @Nested
    @DisplayName("Positive tests for the subclass 'InfantryUnit'")
    class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in FOREST")
        public void checkGetAttackBonusReturnInFOREST() {
            assertEquals(5, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output on PLAINS")
        public void checkGetAttackBonusReturnOnPLAINS() {
            ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
            assertEquals(2, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in HILL")
        public void checkGetAttackBonusReturnInHILL() {
            ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
            assertEquals(2, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output in FOREST")
        public void checkGetResistBonusReturnInFOREST() {
            assertEquals(4, testInfantryUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output on PLAINS")
        public void checkGetResistBonusReturnOnPLAINS() {
            ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
            assertEquals(1, testInfantryUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output in HILL")
        public void checkGetResistBonusReturnInHILL() {
            ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
            assertEquals(1, testInfantryUnit.getResistBonus());
        }
    }
    @Nested
    @DisplayName("Negative tests for the subclass 'InfantryUnit'")
    class MethodThrowsException {
        @Test
        @DisplayName("Checking that getAttackBonus throws NullPointerException when active terrain is 'null'")
        public void checkGetAttackBonusThrowingException() {
            ActiveTerrain.INSTANCE.setActiveTerrain(null);
            try{
                testInfantryUnit.getAttackBonus();
                fail("'checkGetAttackBonusThrowingException' did not throw the expected NullPointerException");
            }catch (NullPointerException n){
                assertEquals("The terrain has not been set to a valid terrain type, please make sure this " +
                        "is done before starting any simulation.", n.getMessage());
            }
        }
    }
}
