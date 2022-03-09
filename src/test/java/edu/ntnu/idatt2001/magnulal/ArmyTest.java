package edu.ntnu.idatt2001.magnulal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
//TODO: RESTRUCTURE nested structure to classes w neg and pos tests per method
public class ArmyTest {
    @Nested
    @DisplayName("Positive tests for the class 'Army'")
    class methodsReturnsAsExpected {
        @Test
        @DisplayName("Checking that the constructor accepts just NAME")
        public void checkConstructorWithNameString() {
            try{
                Army testArmy = new Army("Alliance");
                assertEquals("Alliance", testArmy.getName());
            }catch (Exception e){
                fail("'checkConstructorWithNameString' failed");
            }
        }
        @Test
        @DisplayName("Checking that the constructor accepts NAME and list")
        public void checkConstructorWithNameStringAndList() {
            try{
                ArrayList<Unit> unitsTestArray = new ArrayList<>();
                unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
                unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
                unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20,2));
                new Army("Alliance", unitsTestArray);
            }catch (Exception e){
                fail("'checkConstructorWithNameStringAndList' failed");
            }
        }
        @Test
        @DisplayName("Testing if the add()-method functions correctly")
        public void testAddToArmyMethod() {
            Army testArmy = new Army("Alliance");
            testArmy.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            assertEquals(1, testArmy.getArmySize());
        }
        @Test
        @DisplayName("Testing addAll()-method to add multiple units to army")
        public void testAddAllUnitsOfAListMethod() {
            try{
                Army testArmy = new Army("Alliance");
                ArrayList<Unit> unitsTestArray = new ArrayList<>();
                unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
                unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
                unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20,2));
                testArmy.addAll(unitsTestArray);
                assertEquals(3, testArmy.getArmySize());
            }catch (Exception e){
                fail("'testAddAllUnitsOfAListMethod' failed");
            }
        }
        @Test
        @DisplayName("Testing if the remove()-method functions correctly")
        public void testRemoveFromArmyMethod() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CommanderUnit testCommanderUnit =
                    new CommanderUnit("Silas the Widowmaker", 160, 30, 50);
            testArmy.add(testCommanderUnit);
            assertEquals(4, testArmy.getArmySize());
            testArmy.remove(testCavalryUnit);
            assertEquals(3, testArmy.getArmySize());
            testArmy.remove(testInfantryUnit);
            assertEquals(2, testArmy.getArmySize());
            testArmy.remove(testRangedUnit);
            assertEquals(1, testArmy.getArmySize());
            testArmy.remove(testCommanderUnit);
            assertEquals(0, testArmy.getArmySize());
        }
        @Test
        @DisplayName("Testing if the hasUnits method functions correctly")
        public void testHasUnitsMethod() {
            Army testArmy = new Army("Alliance");
            assertFalse(testArmy.hasUnits());
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            assertTrue(testArmy.hasUnits());
        }
        @Test
        @DisplayName("Testing getRandom unit method")
        public void testGetRandomMethod() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Unit randomPickedUnit = testArmy.getRandom();
            assertTrue(testArmy.getAllUnits().contains(randomPickedUnit));
        }
        @Test
        @DisplayName("Testing toString method for an army")
        public void testToStringMethod() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            Army testArmy = new Army("Alliance", unitsTestArray);
            String underLine = "\n|_______________________________________________________________\n";
            String testStr = underLine + "| Units of the army: 'Alliance'" + underLine +
                    "| Name: 'GrandLancer'  Health: 75   Attack: 12   Armor: 10   " + underLine +
                    "| Name: 'GrandLancer'  Health: 65   Attack: 10   Armor: 10   " + underLine +
                    "| Name: 'CrossbowMan'  Health: 40   Attack: 20   Armor: 2    " + underLine +
                    "'Alliance' is an army with 3 total units";
            assertEquals(testStr, testArmy.toString());
        }
        @Test
        @DisplayName("Testing equals method for an army")
        public void testEqualsMethod() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Alliance", unitsTestArray);
            assertEquals(testArmy, testArmy2);
        }
        @Test
        @DisplayName("Testing hashcode method for an army")
        public void testHashCodeMethod() {
            CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
            CavalryUnit cavalryUnit2 = new CavalryUnit("GrandLancer", 65, 10, 10);
            RangedUnit rangedUnit1 = new RangedUnit("CrossbowMan", 40, 20, 2);
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(cavalryUnit1);
            unitsTestArray.add(cavalryUnit2);
            unitsTestArray.add(rangedUnit1);
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Alliance", unitsTestArray);
            assertEquals(testArmy.hashCode(), testArmy2.hashCode());
        }
        @Test
        @DisplayName("Testing equals method for an army, on different armies")
        public void testEqualsMethod2() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            ArrayList<Unit> unitsTestArray2 = new ArrayList<>();
            unitsTestArray2.add(new CavalryUnit("GrandWhipper", 75, 12, 10));
            unitsTestArray2.add(new CavalryUnit("GrandLancer", 85, 10, 10));
            unitsTestArray2.add(new RangedUnit("CrossbowMan", 40, 20, 5));
            unitsTestArray2.add(new RangedUnit("CrossbowWoman", 47, 20, 5));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Britforce", unitsTestArray2);
            assertNotEquals(testArmy, testArmy2);
        }
        @Test
        @DisplayName("Testing hashcode method for an army, two different armies")
        public void testHashCodeMethod2() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            ArrayList<Unit> unitsTestArray2 = new ArrayList<>();
            unitsTestArray2.add(new CavalryUnit("GrandWhipper", 75, 12, 10));
            unitsTestArray2.add(new CavalryUnit("GrandLancer", 85, 10, 10));
            unitsTestArray2.add(new RangedUnit("CrossbowMan", 40, 20, 5));
            unitsTestArray2.add(new RangedUnit("CrossbowWoman", 47, 20, 5));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Britforce", unitsTestArray2);
            assertNotEquals(testArmy.hashCode(), testArmy2.hashCode());
        }

    }
    @Nested
    @DisplayName("Negative tests for the class 'Army'")
    class methodsReturnsUnexpectedValues {
        @Test
        @DisplayName("Testing constructor 1 of 'Army' class with empty string-parameter")
        public void constructor1TestWithStringAsParameter() {
            try{
                //EXCEPTION THROWER
                new Army("");
                fail("'constructor1TestWithStringAsParameter' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("The name for an army cannot be inputted as an" +
                        " empty string, please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Testing constructor 2 of 'Army' class with empty string-parameter")
        public void constructor2TestWithStringAsParameter() {
            try{
                CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                ArrayList<Unit> unitsTestArray = new ArrayList<>();
                unitsTestArray.add(cavalryUnit1);
                //EXCEPTION THROWER
                new Army("", unitsTestArray);
                fail("'constructor2TestWithStringAsParameter' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("The name for an army cannot be inputted as an" +
                        " empty string, please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Testing constructor 2 of 'Army' class with unwanted list-parameter")
        public void constructor2TestWithUnwantedListParameter() {
            try{
                CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                ArrayList<Unit> unitsTestArray = new ArrayList<>();
                unitsTestArray.add(cavalryUnit1);
                //EXCEPTION THROWER
                new Army("", unitsTestArray);
                fail("'constructor2TestWithUnwantedListParameter' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("The name for an army cannot be inputted as an" +
                        " empty string, please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Testing addAll method with wrong list-parameter")
        public void testingAddAllMethodWithWrongListTypeAsParameter() {
            try{
                CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                Vector<Unit> unitsTestArray = new Vector<>();
                unitsTestArray.add(cavalryUnit1);
                //EXCEPTION THROWER
                new Army("Alliance", unitsTestArray);
                fail("'testingAddAllMethodWithWrongListTypeAsParameter' should have thrown an exception");
            }catch (IllegalArgumentException e){
                assertEquals("The inputted list-type must be either an arraylist, " +
                        "or a linked list, please try again.", e.getMessage());
            }
        }
    }
}
