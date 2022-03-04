import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to one class w neg and pos tests per method
public class UnitTest {
    private final Unit testUnit = new Unit("Archer", 20, 3, 2){
        @Override
        public int getAttackBonus() {
            return 0;
        }
        @Override
        public int getResistBonus() {
            return 0;
        }
    };
    private final Unit testUnit2 = new Unit("SpearGoblin", 30, 7, 2){
        @Override
        public int getAttackBonus() {
            return 0;
        }
        @Override
        public int getResistBonus() {
            return 0;
        }
    };

    @Nested
    @DisplayName("Positive tests for the abstract class 'Unit'")
    class TestingSupportedInputData {
        @Test
        @DisplayName("Subclass of 'Unit' used to test the constructor, correct input")
        public void usingSubclassToTestCorrectInputOfUnitConstructor() {
            assertEquals("Archer", testUnit.getName());
            assertEquals(20, testUnit.getHealth());
            assertEquals(3, testUnit.getAttack());
            assertEquals(2, testUnit.getArmor());
        }
        @Test
        @DisplayName("Subclass of 'Unit' used to test Unit's toString, correct input")
        public void usingSubclassToTestCorrectInputOfUnitsToString() {
            String expectedToString = "| Name: 'Archer'       Health: 20   Attack: 3    Armor: 2    ";
            assertEquals(expectedToString, testUnit.toString());
        }
        @Test
        @DisplayName("Testing attack-method for objects of subclasses of unit-class")
        public void testingAttackMethodForUnits() {
            assertEquals(20, testUnit.getHealth());
            testUnit2.attack(testUnit);
            assertEquals(15, testUnit.getHealth());
            testUnit2.attack(testUnit);
            assertEquals(10, testUnit.getHealth());
            testUnit2.attack(testUnit);
            assertEquals(5, testUnit.getHealth());
            testUnit2.attack(testUnit);
            assertEquals(0, testUnit.getHealth());
            testUnit2.attack(testUnit);
            assertEquals(0, testUnit.getHealth());
        }
        @Test
        @DisplayName("Subclass of 'Unit' used to test the setHealth-method, correct input")
        public void usingSubclassToTestCorrectInputOfUnitSetHealth() {
            assertEquals(20,testUnit.getHealth());
            testUnit.setHealth(10);
            assertEquals(10,testUnit.getHealth());
            testUnit.setHealth(-1);
            assertEquals(0,testUnit.getHealth());
        }
    }
    @Nested
    @DisplayName("Negative tests for the abstract class 'Unit'")
    class TestingUnsupportedInputValues {
        @Test
        @DisplayName("Name is inputted as a blank string")
        public void blankNameInputTest() {
            try{
                //EXCEPTION THROWER
                new Unit("", 20, 3, 2){
                    @Override
                    public int getAttackBonus() {
                        return 0;
                    }
                    @Override
                    public int getResistBonus() {
                        return 0;
                    }
                };
                //FAIL
                fail("'blankNameInputTest' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("A unit's name cannot be inputted as an empty string,  please try again.",
                        e.getMessage());
            }
        }
        @Test
        @DisplayName("Health is set to negative integer")
        public void healthSetToNegativeInteger() {
            try{
                //EXCEPTION THROWER
                new Unit("Archer", -20, 3, 2){
                    @Override
                    public int getAttackBonus() {
                        return 0;
                    }
                    @Override
                    public int getResistBonus() {
                        return 0;
                    }
                };
                //FAIL
                fail("'armorInputtedAsNegativeInteger' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("A unit's health must be a positive integer, please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Attack is inputted as a negative integer")
        public void attackStatIsInputtedAsNegativeInteger() {
            try{
                //EXCEPTION THROWER
                new Unit("Archer", 20, -1, 2){
                    @Override
                    public int getAttackBonus() {
                        return 0;
                    }
                    @Override
                    public int getResistBonus() {
                        return 0;
                    }
                };
                //FAIL
                fail("'attackStatIsInputtedAsNegativeInteger' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("A unit's attack must be a positive integer, please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Armor is set to negative integer")
        public void armorSetToNegativeInteger() {
            try{
                //EXCEPTION THROWER
                new Unit("Archer", 20, 3, -1){
                    @Override
                    public int getAttackBonus() {
                        return 0;
                    }
                    @Override
                    public int getResistBonus() {
                        return 0;
                    }
                };
                //FAIL
                fail("'armorInputtedAsNegativeInteger' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("A unit's armor must be a positive integer, please try again.", e.getMessage());
            }
        }
    }
}

