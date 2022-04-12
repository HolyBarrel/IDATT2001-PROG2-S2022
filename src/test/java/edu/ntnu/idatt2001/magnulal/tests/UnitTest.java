package edu.ntnu.idatt2001.magnulal.tests;
import edu.ntnu.idatt2001.magnulal.model.units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    private Unit testUnit;
    private Unit testUnit2;
    private Unit testUnit3;

    @BeforeEach
    public void initiateTestUnits(){
        //Utilizing anonymous test classes to easily test the methods specified i the abstract class 'Unit'
        //as high up in the hierarchy as possible. This ensures the correct functioning of the methods, while
        //limiting the need to test these methods through each individual subclass of Unit
        this.testUnit = new Unit("Archer", 20, 3, 2){
            @Override
            public int getAttackBonus() {
                return 0;
            }
            @Override
            public int getResistBonus() {
                return 0;
            }};
        this.testUnit2 = new Unit("SpearGoblin", 30, 7, 2){
            @Override
            public int getAttackBonus() {
                return 0;
            }
            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        this.testUnit3 = new Unit("WeakMan", 10, 2, 2){
            @Override
            public int getAttackBonus() {
                return 0;
            }
            @Override
            public int getResistBonus() {
                return 0;
            }
        };
    }
    @Nested
    @DisplayName("Testing the constructor of the abstract class Unit") //using anonymous classes
    class TestsConstructorOfUnit{
        @Nested
        @DisplayName("Positive tests of the constructor of Unit")
        class PositiveTestsConstructorOfUnit{
            @Test
            @DisplayName("Checking the name String-value")
            public void checkingCorrectNameStringValue() {
                assertEquals("Archer", testUnit.getName());
            }
            @Test
            @DisplayName("Checking the health integer-value")
            public void checkingCorrectHealthIntegerValue() {
                assertEquals(20, testUnit.getHealth());
            }
            @Test
            @DisplayName("Checking the attack integer-value")
            public void checkingCorrectAttackIntegerValue() {
                assertEquals(3, testUnit.getAttack());
            }
            @Test
            @DisplayName("Checking the health integer-value")
            public void checkingCorrectArmorIntegerValue() {
                assertEquals(2, testUnit.getArmor());
            }
        }
        @Nested
        @DisplayName("Negative tests of the constructor of Unit")
        class NegativeTestsConstructorOfUnit{
            @Test
            @DisplayName("Name is inputted as a blank string")
            public void blankNameInput() {
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
                    fail("'blankNameInput' should have thrown an exception");
                }catch (IllegalArgumentException e){
                    assertEquals("A unit's name cannot be inputted as an empty string,  please try again.",
                            e.getMessage());
                }
            }
            @Test
            @DisplayName("Name is 'null'-value as input")
            public void nameIsInputtedWithValueNull() {
                try{
                    //EXCEPTION THROWER
                    new Unit(null, 20, 3, 2){
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
                    fail("'nameIsInputtedWithValueNull' should have thrown an exception");
                }catch (NullPointerException n){
                    assertEquals("A unit's name cannot be inputted as 'null', " +
                            "please try again.", n.getMessage());
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
                    assertEquals("A unit cannot be instantiated with a negative integer as parameter," +
                            " please try again.", e.getMessage());
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
    //TODO: double check feedback in bb
    @Nested
    @DisplayName("Testing the toString of Unit") //using anonymous classes
    class TestsToStringOfUnit{
        @Test
        @DisplayName("Subclass of 'Unit' used to test Unit's toString, correct input")
        public void usingSubclassToTestCorrectInputOfUnitsToString() {
            String expectedToString = "| Name: 'Archer'       Health: 20   Attack: 3    Armor: 2    ";
            assertEquals(expectedToString, testUnit.toString());
        }
    }
    @Nested
    @DisplayName("Testing the attack of Unit")
    class TestsAttackMethod{
        @Test
        @DisplayName("Testing attack-method for objects of subclasses of unit-class")
        public void attackMethodForUnits() {
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
        @DisplayName("Testing attack-method when protection is more than damage")
        public void attackMethodProtectMoreThanDamageResultsInNoDamageDone() {
            assertEquals(20, testUnit.getHealth());
            testUnit3.attack(testUnit); //testing that the attack does not harm when protection is more than the damage
            assertEquals(20, testUnit.getHealth());
        }
    }
    @Nested
    @DisplayName("Testing the setHealth-method")
    class TestSetHealthMethod{
        @Test
        @DisplayName("SetHealth-method changes the health to the inputted integer value, correct input")
        public void setHealthSetsHealthToInputtedIntegerValue() {
            assertEquals(20,testUnit.getHealth());
            testUnit.setHealth(10);
            assertEquals(10,testUnit.getHealth());
        }
        @Test
        @DisplayName("Testing that the health is never set to an integer below zero")
        public void setHealthOfNegativeIntFloorsTheHealthAtZero() {
            testUnit.setHealth(-1);
            assertEquals(0,testUnit.getHealth());
        }
    }





}

