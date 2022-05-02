package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.units.CavalryUnit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import org.junit.jupiter.api.*;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;
import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
    private CavalryUnit testCavalryUnit;
    private CavalryUnit testCavalryUnit2;
    @BeforeEach
    public void initiateCavalryTestUnit(){
        ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
        this.testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
        this.testCavalryUnit2 = new CavalryUnit("GrandLancer", 75, 12, 10);
    }
    @AfterEach
    public void clearTerrain(){
        ActiveTerrain.INSTANCE.setActiveTerrain(null);
    }
    @Nested
    @DisplayName("Positive tests for the subclass 'CavalryUnit'")
    public class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that isCharging returns expected output")
        public void checkIsChargingAttacksReturn() {
            assertTrue(testCavalryUnit.isCharging());
            testCavalryUnit.attack(testCavalryUnit2);
            assertFalse(testCavalryUnit.isCharging());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output on PLAINS")
        public void checkGetAttackBonusReturnWhenChargingOnPLAINS() {
            //charging
            assertEquals(10, testCavalryUnit.getAttackBonus());
            testCavalryUnit.attack(testCavalryUnit2);
            //not charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in FOREST")
        public void checkGetAttackBonusReturnWhenChargingInFOREST() {
            ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
            //charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
            testCavalryUnit.attack(testCavalryUnit2);
            //not charging
            assertEquals(2, testCavalryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in HILL")
        public void checkGetAttackBonusReturnWhenChargingInHILL() {
            ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
            //charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
            testCavalryUnit.attack(testCavalryUnit2);
            //not charging
            assertEquals(2, testCavalryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output on PLAINS")
        public void checkGetResistBonusReturnOnPLAINS() {
            assertEquals(1, testCavalryUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output in FOREST")
        public void checkGetResistBonusReturnInFOREST() {
            ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
            assertEquals(0, testCavalryUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output in HILL")
        public void checkGetResistBonusReturnInHILL() {
            ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
            assertEquals(1, testCavalryUnit.getResistBonus());
        }
    }
    @Nested
    @DisplayName("Negative tests for the subclass 'CavalryUnit'")
    public class MethodsThrowsExceptions {
        @Test
        @DisplayName("Checking that getAttackBonus throws NullPointerException when active terrain is 'null'")
        public void checkGetAttackBonusThrowingException() {
            ActiveTerrain.INSTANCE.setActiveTerrain(null);
            try{
                testCavalryUnit.getAttackBonus();
                fail("'checkGetAttackBonusThrowingException' did not throw the expected NullPointerException");
            }catch (NullPointerException n){
                assertEquals("The terrain has not been set to a valid terrain type, please make sure this " +
                        "is done before starting any simulation.", n.getMessage());
            }
        }
    }
}
