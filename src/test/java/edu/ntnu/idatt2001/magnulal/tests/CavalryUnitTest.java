package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.unitclasses.CavalryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
    private CavalryUnit testCavalryUnit;
    private CavalryUnit testCavalryUnit2;
    @BeforeEach
    public void initiateCavalryTestUnit(){
        this.testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
        this.testCavalryUnit2 = new CavalryUnit("GrandLancer", 75, 12, 10);
    }
    @Nested
    @DisplayName("Positive tests for the subclass 'CavalryUnit'")
    class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that isCharging returns expected output")
        public void checkIsChargingAttacksReturn() {
            assertTrue(testCavalryUnit.isCharging());
            testCavalryUnit.attack(testCavalryUnit2);
            assertFalse(testCavalryUnit.isCharging());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturnWhenCharging() {
            //charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
            testCavalryUnit.attack(testCavalryUnit2);
            //not charging
            assertEquals(2, testCavalryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output")
        public void checkGetResistBonusReturn() {
            assertEquals(1, testCavalryUnit.getResistBonus());
        }
    }
}
