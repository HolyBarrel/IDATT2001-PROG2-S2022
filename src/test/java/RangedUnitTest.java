import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangedUnitTest {
    @Nested
    @DisplayName("Positive tests for the subclass 'RangedUnit'")
    class getMethodsReturnsAsExpected {
        //TODO: test attack()?, or do it in the UNITTEST
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
        }
    }
    @Nested
    @DisplayName("Negative tests for the subclass 'RangedUnit'")
    class getMethodsReturnsWrongOutput {
        @Test
        @DisplayName("Checking getAttackBonus against wrong integer")
        public void checkGetAttackBonusReturn() {
            RangedUnit testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
            assertNotEquals(1, testRangedUnit.getAttackBonus());
        }
        @Test
        @DisplayName("Checking getResistBonus against wrong integer")
        public void checkGetResistBonusReturn() {
            RangedUnit testRangedUnit = new RangedUnit("CrossbowMan", 40, 20,2);
            assertNotEquals(1, testRangedUnit.getResistBonus());
        }
    }
}
