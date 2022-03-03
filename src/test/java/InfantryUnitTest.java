import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to one class w neg and pos tests per method
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
}
