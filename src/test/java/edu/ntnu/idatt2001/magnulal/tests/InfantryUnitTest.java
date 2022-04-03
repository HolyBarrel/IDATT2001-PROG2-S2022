package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.unitclasses.InfantryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
    private InfantryUnit testInfantryUnit;

    @BeforeEach
    public void initiateTestInfantryUnit(){
        this.testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
    }
    @Nested
    @DisplayName("Positive tests for the subclass 'InfantryUnit'")
    class MethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturn() {
            assertEquals(2, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output")
        public void checkGetResistBonusReturn() {
            assertEquals(1, testInfantryUnit.getResistBonus());
        }
    }
}
