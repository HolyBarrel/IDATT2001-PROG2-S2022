package edu.ntnu.idatt2001.magnulal;
import edu.ntnu.idatt2001.magnulal.model.simulatorclasses.Army;
import edu.ntnu.idatt2001.magnulal.unitclasses.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {
    @Nested
    @DisplayName("Tests for the Army Constructor")
    class TestsForArmyConstructor{
        @Nested
        @DisplayName("Positive tests for the Army Constructor")
        class positiveTestsForArmyConstructor{
            @Test
            @DisplayName("Checking that constructor accepts just a string")
            public void checkConstructorWithNameString() {
                try{
                    Army testArmy = new Army("Alliance");
                    assertEquals("Alliance", testArmy.getName());
                }catch (Exception e){
                    fail("'checkConstructorWithNameString' failed");
                }
            }
            @Test
            @DisplayName("Checking that constructor accepts string for name and a list")
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
        }
        @Nested
        @DisplayName("Negative tests for the Army Constructor")
        class negativeTestsForArmyConstructor {
            @Test
            @DisplayName("Testing constructor of 'Army' class with empty string-parameter")
            public void constructor1WithStringAsParameter() {
                try{
                    //EXCEPTION THROWER
                    new Army("");
                    fail("'constructor1WithStringAsParameter' should have thrown an exception");
                }catch (IllegalArgumentException e){
                    assertEquals("The name for an army cannot be inputted as an" +
                            " empty string, please try again.", e.getMessage());
                }
            }
            @Test
            @DisplayName("Testing constructor of 'Army' class with  'null' as parameter")
            public void constructor1WithNullAsParameter() {
                try{
                    //EXCEPTION THROWER
                    new Army(null);
                    fail("'constructor1WithNullAsParameter' should have thrown an exception");
                }catch (NullPointerException n){
                    assertEquals("The name of the army was given the value 'null' " +
                                    "as a parameter, please try again.",
                            n.getMessage());
                }
            }
            @Test
            @DisplayName("Testing constructor 2 of 'Army' class with empty string-parameter")
            public void constructor2WithStringAsParameter() {
                try{
                    CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(cavalryUnit1);
                    //EXCEPTION THROWER
                    new Army("", unitsTestArray);
                    fail("'constructor2WithStringAsParameter' should have thrown an exception");
                }catch (IllegalArgumentException e){
                    assertEquals("The name for an army cannot be inputted as an" +
                            " empty string, please try again.", e.getMessage());
                }
            }
            @Test
            @DisplayName("Testing constructor 2 of 'Army' class with unwanted list-parameter")
            public void constructor2WithUnwantedListParameter() {
                try{
                    CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(cavalryUnit1);
                    //EXCEPTION THROWER
                    new Army("", unitsTestArray);
                    fail("'constructor2WithUnwantedListParameter' should have thrown an exception");
                }catch (IllegalArgumentException e){
                    assertEquals("The name for an army cannot be inputted as an" +
                            " empty string, please try again.", e.getMessage());
                }
            }
            @Test
            @DisplayName("Testing constructor 2 of 'Army' class with  'null' as parameter")
            public void constructor2WithNullAsParameter() {
                try{
                    CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(cavalryUnit1);
                    //EXCEPTION THROWER
                    new Army(null,unitsTestArray);
                    fail("'constructor2WithNullAsParameter' should have thrown an exception");
                }catch (NullPointerException n){
                    assertEquals("The name of the army was given the value 'null' " +
                                    "as a parameter, please try again.",
                            n.getMessage());
                }
            }
        }
    }

    @Nested
    @DisplayName("Tests for add-method of Army")
    class TestAddToArmy{
        @Test
        @DisplayName("Testing if the add()-method adds a unit to the army")
        public void addToArmyMethod() {
            Army testArmy = new Army("Alliance");
            testArmy.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            assertEquals(1, testArmy.getArmySize());
        }
    }
    @Nested
    @DisplayName("Tests for addAll-method of Army")
    class TestsAddAllToArmy{
        @Nested
        @DisplayName("Positive tests for addAll-method of Army")
        class positiveTestsAddAllToArmy{
            @Test
            @DisplayName("Testing addAll()-method to add multiple units to army")
            public void addAllUnitsOfAListMethod() {
                try{
                    Army testArmy = new Army("Alliance");
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
                    unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
                    unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20,2));
                    testArmy.addAll(unitsTestArray);
                    assertEquals(3, testArmy.getArmySize());
                }catch (Exception e){
                    fail("'addAllUnitsOfAListMethod' failed");
                }
            }
        }
        @Nested
        @DisplayName("Negative tests for addAll-method of Army")
        class negativeTestsAddAllToArmy{
            @Test
            @DisplayName("Testing addAll method with wrong list-parameter")
            public void addAllMethodWithWrongListTypeAsParameter() {
                try{
                    CavalryUnit cavalryUnit1 = new CavalryUnit("GrandLancer", 75, 12, 10);
                    Vector<Unit> unitsTestArray = new Vector<>();
                    unitsTestArray.add(cavalryUnit1);
                    //EXCEPTION THROWER
                    new Army("Alliance", unitsTestArray);
                    fail("'addAllMethodWithWrongListTypeAsParameter' should have thrown an exception");
                }catch (IllegalArgumentException e){
                    assertEquals("The inputted list-type must be either an arraylist, " +
                            "or a linked list, please try again.", e.getMessage());
                }
            }
        }
    }

    @Nested
    @DisplayName("Tests for remove-method of Army")
    class TestsRemoveMethodOfArmyClass{
        @Test
        @DisplayName("Testing if the remove()-method removes a units from the army")
        public void removeFromArmyMethod() {
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
            //Due to the Battle.simulate() always checking Battle.isBattleActive,
            //no unit will tried to be removed from an empty army, and therefore no further tests
            //are necessary
        }
    }

    @Nested
    @DisplayName("Tests for hasUnits method of Army")
    class TestsHasUnitsMethod {
        @Test
        @DisplayName("Testing if the hasUnits method detects if the army has units")
        public void hasUnitsMethod() {
            Army testArmy = new Army("Alliance");
            assertFalse(testArmy.hasUnits());
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            assertTrue(testArmy.hasUnits());
        }
    }

    @Nested
    @DisplayName("Tests for getRandom method of Army")
    class TestsGetRandomMethod{
        @Test
        @DisplayName("Testing that getRandom unit method gets a unit from the army")
        public void getRandomMethod() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            Army testArmy = new Army("Alliance", unitsTestArray);
            for (int i = 0; i < testArmy.getAllUnits().size(); i++) { //test checks as many times as elements
                // is the army
                Unit randomPickedUnit = testArmy.getRandom();
                assertTrue(testArmy.getAllUnits().contains(randomPickedUnit));
            }
        }
    }

    @Nested
    @DisplayName("Tests for toString method of Army")
    class TestsToString{
        @Test
        @DisplayName("Testing toString method for an army")
        public void toStringMethod() {
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
    }
    @Nested
    @DisplayName("Tests for equals and hashCode methods of Army")
    class TestsHashCodeAndEquals{
        @Test
        @DisplayName("Testing equals method for armies that are considered equal")
        public void equalsMethodOnEqualArmies() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 75, 12, 10));
            unitsTestArray.add(new CavalryUnit("GrandLancer", 65, 10, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 40, 20, 2));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Alliance", unitsTestArray);
            assertEquals(testArmy, testArmy2);
        }
        @Test
        @DisplayName("Testing hashcode method for an army on equal armies")
        public void testHashCodeMethodOnEqualArmies() {
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
        public void testEqualsMethodOnDifferentArmies() {
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
        public void testHashCodeMethodOnDifferentArmiesAlternative() {
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
    @DisplayName("Tests for getInfantryUnits method of Army")
    class TestsForGetInfantryUnitsMethod {
        @Test
        @DisplayName("Validating correctness of getInfantryUnits method's output")
        public void validatingGetInfantryUnitsWithArmyContainingInfantry() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            RangedUnit testRangedUnit2 = new RangedUnit("Ice Mage", 20, 85, 10);
            testArmy.add(testRangedUnit2);
            assertTrue(testArmy.getInfantryUnits().stream().allMatch(u -> u instanceof InfantryUnit));
        }

        @Test
        @DisplayName("Validating correctness of getInfantryUnits method's output when the " +
                "army does not have an infantry unit")
        public void validatingGetInfantryUnitsWithArmyNotContainingInfantry() {
            Army testArmy = new Army("Alliance");
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            assertEquals(0, testArmy.getInfantryUnits().stream()
                    .filter(u -> u instanceof InfantryUnit).toList().size());
        }
    }
    @Nested
    @DisplayName("Tests for getCavalryUnits method of Army")
    class TestsForGetCavalryUnitsMethod {
        @Test
        @DisplayName("Validating correctness of getCavalryUnit method's output")
        public void validatingGetCavalryUnitsWithArmyContainingCavalry() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            RangedUnit testRangedUnit2 = new RangedUnit("Ice Mage", 20, 85, 10);
            testArmy.add(testRangedUnit2);
            assertTrue(testArmy.getCavalryUnits().stream()
                    .allMatch(u -> u instanceof CavalryUnit && !(u instanceof CommanderUnit)));
        }
        @Test
        @DisplayName("Validating correctness of getCavalryUnit method's output when the " +
                "army does not have a cavalry unit")
        public void validatingGetCavalryUnitsWithArmyNotContainingCavalry() {
            Army testArmy = new Army("Alliance");
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            assertEquals(0, testArmy.getCavalryUnits().stream()
                    .filter(u -> u instanceof CavalryUnit).toList().size());
        }
    }
    @Nested
    @DisplayName("Tests for getRangedUnits method of Army")
    class TestsForGetRangedUnitsMethod {
        @Test
        @DisplayName("Validating correctness of getRangedUnit method's output")
        public void validatingGetRangedUnitsWithArmyContainingRanged() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            RangedUnit testRangedUnit2 = new RangedUnit("Ice Mage", 20, 85, 10);
            testArmy.add(testRangedUnit2);
            assertTrue(testArmy.getRangedUnits().stream()
                    .allMatch(u -> u instanceof RangedUnit));
        }
        @Test
        @DisplayName("Validating correctness of getRangedUnit method's output when the " +
                "army does not have a ranger")
        public void validatingGetRangedUnitsWithArmyNotContainingRanger() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            assertEquals(0, testArmy.getRangedUnits().stream()
                    .filter(u -> u instanceof RangedUnit).toList().size());
        }
    }
    @Nested
    @DisplayName("Tests for getCommanderUnits method of Army")
    class TestsForGetCommanderUnitsMethod {
        @Test
        @DisplayName("Validating correctness of getCommanderUnit method's output")
        public void validatingGetCommanderUnitsWithArmyContainingCommander() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            CommanderUnit testCommanderUnit = new CommanderUnit(
                    "Commander Fink", 120,  95, 100);
            testArmy.add(testCommanderUnit);
            assertTrue(testArmy.getCommanderUnits().stream()
                    .allMatch(u -> u instanceof CommanderUnit));
        }

        @Test
        @DisplayName("Validating correctness of getCommanderUnit method's output when the " +
                "army does not have a commander")
        public void validatingGetCommanderUnitsWithArmyNotContainingCommander() {
            Army testArmy = new Army("Alliance");
            CavalryUnit testCavalryUnit = new CavalryUnit("GrandLancer", 75, 12, 10);
            testArmy.add(testCavalryUnit);
            InfantryUnit testInfantryUnit = new InfantryUnit("Paladin", 100, 20, 18);
            testArmy.add(testInfantryUnit);
            RangedUnit testRangedUnit = new RangedUnit("Fire Mage", 20, 80, 10);
            testArmy.add(testRangedUnit);
            CavalryUnit testCavalryUnit2 = new CavalryUnit("GrandRider", 75, 12, 10);
            testArmy.add(testCavalryUnit2);
            InfantryUnit testInfantryUnit2 = new InfantryUnit("Musket man", 30, 40, 18);
            testArmy.add(testInfantryUnit2);
            assertEquals(0, testArmy.getCommanderUnits().stream()
                    .filter(u -> u instanceof CommanderUnit).toList().size());
        }
    }
}

