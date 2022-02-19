import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
    @Nested
    @DisplayName("Positive tests for the subclass 'InfantryUnit'")
    class getMethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturn() {
            InfantryUnit testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
            assertEquals(2, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output")
        public void checkGetResistBonusReturn() {
            InfantryUnit testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
            assertEquals(1, testInfantryUnit.getResistBonus());
        }
    }
    @Nested
    @DisplayName("Negative tests for the subclass 'InfantryUnit'")
    class getMethodsReturnsWrongOutput {
        @Test
        @DisplayName("Checking getAttackBonus against wrong integer")
        public void checkGetAttackBonusReturn() {
            InfantryUnit testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
            assertNotEquals(1, testInfantryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking getResistBonus against wrong integer")
        public void checkGetResistBonusReturn() {
            InfantryUnit testInfantryUnit = new InfantryUnit("Axeman", 60, 20,5);
            assertNotEquals(2, testInfantryUnit.getResistBonus());
        }
    }
}
