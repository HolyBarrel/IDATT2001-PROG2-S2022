import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to one class w neg and pos tests per method
public class CavalryUnitTest {
    @Nested
    @DisplayName("Positive tests for the subclass 'CavalryUnit'")
    class getMethodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that getSuccessfulAttacks returns expected output")
        public void checkGetSuccessfulAttacksReturn() {
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            assertEquals(0, testCavalryUnit.getSuccessfulAttacks());
            testCavalryUnit.getAttackBonus();
            assertEquals(1, testCavalryUnit.getSuccessfulAttacks());
        }
        @Test
        @DisplayName("Checking that getAttackBonus returns expected output")
        public void checkGetAttackBonusReturnWhenCharging() {
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            //charging
            assertEquals(6, testCavalryUnit.getAttackBonus());
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
