package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.units.RangedUnit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import org.junit.jupiter.api.*;

import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;
import static org.junit.jupiter.api.Assertions.*;

public class RangedUnitTest {
    private RangedUnit testRangedUnit;

    @BeforeEach
    public void initiateTestRanger(){
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        this.testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
    }
    @AfterEach
    public void clearTerrain(){
        ActiveTerrain.INSTANCE.setActiveTerrain(null);
    }
    @Nested
    @DisplayName("Positive tests for the subclass 'RangedUnit'")
    class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getHitsReceived returns expected output")
        public void checkGetHitsReceivedReturn() {
            assertEquals(0, testRangedUnit.getHitsReceived());
            testRangedUnit.getResistBonus();
            assertEquals(1, testRangedUnit.getHitsReceived());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output, at different ranges")
        public void checkGetResistBonusReturn() {
            //far away
            assertEquals(7, testRangedUnit.getResistBonus());
            //middle range
            assertEquals(5, testRangedUnit.getResistBonus());
            //close combat
            assertEquals(2, testRangedUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in FOREST")
        public void checkGetAttackBonusReturnInFOREST() {
            assertEquals(4, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(4, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(1, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(1, testRangedUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output in HILL")
        public void checkGetAttackBonusReturnInHILL() {
            ActiveTerrain.INSTANCE.setActiveTerrain(HILL);
            assertEquals(9, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(9, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(6, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(6, testRangedUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output on PLAINS")
        public void checkGetAttackBonusReturnOnPlains() {
            ActiveTerrain.INSTANCE.setActiveTerrain(PLAINS);
            assertEquals(5, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(5, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(2, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(2, testRangedUnit.getAttackBonus());
        }
    }
    @Nested
    @DisplayName("Negative tests for the subclass 'RangedUnit'")
    class MethodsThrowsExceptions {
        @Test
        @DisplayName("Checking that getAttackBonus throws NullPointerException when active terrain is 'null'")
        public void checkGetAttackBonusThrowingException() {
            ActiveTerrain.INSTANCE.setActiveTerrain(null);
            try{
                testRangedUnit.getAttackBonus();
                fail("'checkGetAttackBonusThrowingException' did not throw the expected NullPointerException");
            }catch (NullPointerException n){
                assertEquals("The terrain has not been set to a valid terrain type, please make sure this " +
                        "is done before starting any simulation.", n.getMessage());
            }
        }
    }
}
