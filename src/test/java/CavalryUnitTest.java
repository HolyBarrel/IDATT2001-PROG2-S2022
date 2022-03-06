import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to classes w neg and pos tests per method
public class CavalryUnitTest {
    @Nested
    @DisplayName("Positive tests for the subclass 'CavalryUnit'")
    class getMethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that isCharging returns expected output")
        public void checkIsChargingAttacksReturn() {
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandLancer", 75, 12, 10);
            assertTrue(testCavalryUnit.isCharging());
            testCavalryUnit.attack(testCavalryUnit2);
            assertFalse(testCavalryUnit.isCharging());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturnWhenCharging() {
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandLancer", 75, 12, 10);
            //charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
            testCavalryUnit.attack(testCavalryUnit2);
            //not charging
            assertEquals(2, testCavalryUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking that getResistBonus returns expected output")
        public void checkGetResistBonusReturn() {
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            assertEquals(1, testCavalryUnit.getResistBonus());
        }
    }
}
