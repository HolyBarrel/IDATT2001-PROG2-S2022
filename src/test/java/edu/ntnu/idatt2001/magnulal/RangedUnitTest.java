package edu.ntnu.idatt2001.magnulal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to classes w neg and pos tests per method
public class RangedUnitTest {
    @Nested
    @DisplayName("Positive tests for the subclass 'RangedUnit'")
    class getMethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getHitsReceived returns expected output")
        public void checkGetHitsReceivedReturn() {
            RangedUnit testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
            assertEquals(0, testRangedUnit.getHitsReceived());
            testRangedUnit.getResistBonus();
            assertEquals(1, testRangedUnit.getHitsReceived());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output, at different ranges")
        public void checkGetResistBonusReturn() {
            RangedUnit testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
            //far away
            assertEquals(7, testRangedUnit.getResistBonus());
            //middle range
            assertEquals(5, testRangedUnit.getResistBonus());
            //close combat
            assertEquals(2, testRangedUnit.getResistBonus());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturn() {
            RangedUnit testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
            assertEquals(3, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(3, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(0, testRangedUnit.getAttackBonus());
            testRangedUnit.getResistBonus();
            assertEquals(0, testRangedUnit.getAttackBonus());
        }
    }
}
