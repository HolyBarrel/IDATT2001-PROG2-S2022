package edu.ntnu.idatt2001.magnulal;
import edu.ntnu.idatt2001.magnulal.unitclasses.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangedUnitTest {
    private RangedUnit testRangedUnit;

    @BeforeEach
    public void initiateTestRanger(){
        this.testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
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
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturn() {
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
