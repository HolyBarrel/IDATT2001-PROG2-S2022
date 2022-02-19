import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * TODO: COMMENT
 */
public class UnitTest {
    private static class UnitTesterClass extends Unit{

        /**
         * Constructor for the superclass Unit, in test class
         * @param NAME
         * @param health
         * @param ATTACK
         * @param ARMOR
         * @throws IllegalArgumentException, is an exception thrown when the arguments are outside the
         *                                   logical input-range, or is blank
         */
        public UnitTesterClass(String NAME, int health, int ATTACK, int ARMOR) throws IllegalArgumentException {
            super(NAME, health, ATTACK, ARMOR);
        }

        /**
         * Is ignored for test purposes
         * @return 0
         * Is tested by non-tester subclasses
         * for example: RangedUnit
         */
        @Override
        public int getAttackBonus() {
            return 0;
        }

        /**
         * Is ignored for test purposes
         * @return 0
         * Is tested by non-tester subclasses
         * for example: RangedUnit
         */
        @Override
        public int getResistBonus() {
            return 0;
        }
    }

    @Nested
    @DisplayName("Positive tests for the abstract class 'Unit'")
    class PositiveTests{
        @Test
        @DisplayName("Subclass of 'Unit' used to test the constructor, correct input")
        public void usingSubclassToTestCorrectInputOfUnitConstructor() {
            Unit testUnit = new UnitTesterClass("Archer", 20,3,2);
            assertEquals("Archer", testUnit.getNAME());
            assertEquals(20, testUnit.getHealth());
            assertEquals(3, testUnit.getATTACK());
            assertEquals(2, testUnit.getARMOR());
        }
        @Test
        @DisplayName("Subclass of 'Unit' used to test Unit's toString, correct input")
        public void usingSubclassToTestCorrectInputOfUnitsToString() {
            assertEquals("\nUNIT:\nName = Archer\nHealth = 20\nAttack = 3\nArmor = 2",
                    new UnitTesterClass("Archer", 20,3,2).toString());
        }

        @Test
        @DisplayName("Subclass of 'Unit' used to test the setHealth-method, correct input")
        public void usingSubclassToTestCorrectInputOfUnitSetHealth() {
            Unit testUnit = new UnitTesterClass("Archer", 20,3,2);
            assertEquals(20, testUnit.getHealth());
            testUnit.setHealth(10);
            assertEquals(10, testUnit.getHealth());
        }



    }
}
