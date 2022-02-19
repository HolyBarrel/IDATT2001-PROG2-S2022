import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests the constructor, set-method and toString-method of the class
 * 'Unit' to avoid test code duplication for each subclass of the
 * mentioned test-cases
 */
public class UnitTest {
    private static class UnitTesterClass extends Unit{

        /**
         * Constructor for the superclass Unit, in test class
         * Below parameters described in superclass
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
         * Is tested by non-tester subclasses of 'Unit'-class
         */
        @Override
        public int getAttackBonus() {
            return 0;
        }

        /**
         * Is ignored for test purposes
         * Is tested by non-tester subclasses of 'Unit'-class
         */
        @Override
        public int getResistBonus() {
            return 0;
        }
    }

    @Nested
    @DisplayName("Positive tests for the abstract class 'Unit'")
    class InputIsAcceptedTests {
        //TODO: REVISE NAMING
        //TODO: add a test for the ATTACK-method
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
            String expectedToString = "\nUNIT:\nName = Archer\nHealth = 20\nAttack = 3\nArmor = 2";
            Unit testUnit = new UnitTesterClass("Archer", 20,3,2);
            assertEquals(expectedToString, testUnit.toString());
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
    @Nested
    @DisplayName("Negative tests for the abstract class 'Unit'")
    class InputIsNotAcceptedTests {

        @Test
        @DisplayName("Name is inputted as a blank string")
        public void blankNameInputTest() {
            try{
                new UnitTesterClass("", 30, 5, 1);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                assertThrows(IllegalArgumentException.class,
                        () -> new UnitTesterClass("", 30, 5, 1));
            }
        }

        @Test
        @DisplayName("Attack is inputted as a negative integer")
        public void attackStatIsInputtedAsNegativeInteger() {
            try{
                new UnitTesterClass("Berserker", 30, -1, 6);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                assertThrows(IllegalArgumentException.class,
                        () -> new UnitTesterClass("Berserker", 30, -1, 6));
            }
        }

        @Test
        @DisplayName("Armor is inputted as a negative integer")
        public void armorInputtedAsNegativeInteger() {
            try{
                new UnitTesterClass("Druid", 40, 5, -1);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                assertThrows(IllegalArgumentException.class,
                        () -> new UnitTesterClass("Druid", 40, 5, -1));
            }
        }

        @Test
        @DisplayName("Health is set to an integer below 0 with set-health-method")
        public void healthIsSetToIntegerLessThanZeroUsingSetMethod() {
            Unit testUnit = new UnitTesterClass("Druid", 40, 5, 2);
            try{
                testUnit.setHealth(-1);
            }catch (IllegalArgumentException e){
                //System.out.println(e.getMessage());
                assertThrows(IllegalArgumentException.class,
                        () -> testUnit.setHealth(-1));
            }
        }
    }
}

