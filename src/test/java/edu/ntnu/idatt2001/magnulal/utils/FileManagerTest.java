package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.utils.FileManager;
import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.units.CavalryUnit;
import edu.ntnu.idatt2001.magnulal.model.units.CommanderUnit;
import edu.ntnu.idatt2001.magnulal.model.units.InfantryUnit;
import edu.ntnu.idatt2001.magnulal.model.units.RangedUnit;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest { //TODO: test all methods of FileManager
    //TODO: implement serializable

    private String fileName;
    private Army humanArmy = new Army("Alliance");
    private Army orcArmy = new Army("Horde");
    @BeforeEach
    public void initiateAndSaveTestArmyToCSV(){
        this.humanArmy = new Army("AllianceTestArmy");
        this.orcArmy = new Army("Horde");
        for (int i = 0; i < 20; i++) { //TODO. unitFactory
            humanArmy.add(new InfantryUnit("Footman", 100));
            orcArmy.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 20; i++) {
            humanArmy.add(new CavalryUnit("Knight", 100));
            orcArmy.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 20; i++) {
            humanArmy.add(new RangedUnit("Archer", 100));
            orcArmy.add(new RangedUnit("SpearOrc", 100));
        }
        humanArmy.add(new CommanderUnit("MountainKing", 180));
        orcArmy.add(new CommanderUnit("Gul'dan", 180));
    }

    @AfterEach
    public void deleteLastFile(){
        if(this.fileName != null){
            FileManager.deleteAFile(fileName);
        }
    }
    @Nested
    @DisplayName("Tests for the FileManager 'writeArmyToFileWFileName' method")
            //These tests also indirectly test the 'writeArmyToFileWFile' method since this is
            // used in the 'writeArmyToFileWFileName' method
    public class TestWriteArmyToFileWFileName{
        @Nested
        @DisplayName("Positive tests for the 'writeArmyToFileWFileName' method")
        public class PositiveTestsForMethodWriteArmyToFileWFileName {
            @Test
            @DisplayName("writeArmyToFileWFileName creates a file")
            public void writeArmyToFileWFileName(){
                fileName = "Alliance";
                FileManager.writeArmyToFileWFileName(fileName, humanArmy);
                assertTrue(Files.exists(Paths.get(
                        "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/"
                                + fileName + ".csv")));
            }
            @Test
            @DisplayName("writeArmyToFileWFileName creates a file with content")
            public void writeArmyToFileWFileNameCreatesAFileWithCorrectContent(){
                fileName = "orcarmy2";
                FileManager.writeArmyToFileWFileName(fileName, orcArmy);
                assertTrue(Files.exists(Paths.get(
                        "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/"
                                + fileName + ".csv")));
                Army testArmy = null;
                try {
                    testArmy = FileManager.readArmyFromFile(fileName);
                } catch (FileNotFoundException f) {
                    fail("'writeArmyToFileWFileNameCreatesAFileWithCorrectContent' failed with the message: "
                            + f.getMessage());
                }
                assertNotNull(testArmy);
                assertEquals(orcArmy.toString(), testArmy.toString());
            }
        }
        @Nested
        @DisplayName("Negative tests for the 'writeArmyToFileWFileName' method")
                //testing the private method 'isPathValid', which implies that other methods of FileHandler using
                //this method does not need further negative tests
        public class NegativeTestsForMethodWriteArmyToFileWFileName {
            @Test
            @DisplayName("writeArmyToFileWFileName throws 'InvalidPathException' when name contains Q-mark")
            public void writeArmyToFileWFileNameThrowsWhenQmarkIsInFileName(){
                try{
                    FileManager.writeArmyToFileWFileName("orcarmy2?", orcArmy);
                }catch (InvalidPathException i){
                    assertEquals("The given file path contained forbidden characters. " +
                                    "The application could not utilize it, please try again.: " +
                                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orcarmy2?.csv",
                            i.getMessage());
                }
            }
            @Test
            @DisplayName("writeArmyToFileWFileName throws 'InvalidPathException' when name contains Slash")
            public void writeArmyToFileWFileNameThrowsWhenSlashIsInFileName(){
                try{
                    FileManager.writeArmyToFileWFileName("orcarmy2/", orcArmy);
                }catch (InvalidPathException i){
                    assertEquals("The given file path contained forbidden characters. The application could " +
                                    "not utilize it, please try again.: " +
                                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orcarmy2/.csv",
                            i.getMessage());
                }
            }
            @Test
            @DisplayName("writeArmyToFileWFileName throws 'InvalidPathException' when name contains backwards slash")
            public void writeArmyToFileWFileNameThrowsWhenBackSlashIsInFileName(){
                try{
                    FileManager.writeArmyToFileWFileName("orcarmy\\2", orcArmy);
                }catch (InvalidPathException i){
                    assertEquals("The given file path contained forbidden characters. " +
                                    "The application could not utilize it, please try again.: " +
                                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orcarmy\\2.csv",
                            i.getMessage());
                }
            }
        }
    }
    @Nested
    @DisplayName("Tests for the FileManager 'readArmyFromFile' method")
            //These tests also tests if the 'readArmyFromExistingFile' method works, since 'readArmyFromFile'
            // uses that method
    public class TestReadArmyFromFileMethod{
        @Nested
        @DisplayName("Positive tests for the 'readArmyFromFile' method")
        public class PositiveTestsForReadArmyFromFileMethod { //TODO: fix
            @Test
            @DisplayName("readArmyFromFile method creates a correct Army without .csv ending in name")
            public void readArmyFromFile(){
                Army testArmy = null;
                try {
                    testArmy = FileManager.readArmyFromFile("AllianceTestArmy");
                } catch (FileNotFoundException f) {
                    fail("'readArmyFromFile' failed with the message: "
                            + f.getMessage());
                }
                assertNotNull(testArmy);
                assertEquals(humanArmy.toString(), testArmy.toString());
            }
            @Test
            @DisplayName("readArmyFromFile method creates a correct Army with .csv ending in name")
            public void readArmyFromFileWCSVEnding(){
                Army testArmy = null;
                try {
                    testArmy = FileManager.readArmyFromFile("AllianceTestArmy.csv");
                } catch (FileNotFoundException f) {
                    fail("'readArmyFromFileWCSVEnding' failed with the message: "
                            + f.getMessage());
                }
                assertNotNull(testArmy);
                assertEquals(humanArmy.toString(), testArmy.toString());
            }
        }
        @Nested
        @DisplayName("Negative tests for the 'readArmyFromFile' method")
        public class NegativeTestsForReadArmyFromFileMethod {
            @Test
            @DisplayName("readArmyFromFile method throws 'NullPointerException' on nonexistent filename")
            public void readArmyFromFileFileNameDoesNotExist(){
                try{
                    FileManager.readArmyFromFile("hummy.csv");
                }catch (FileNotFoundException f){
                    assertEquals("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/hummy.csv " +
                            "Was the path. Could not find a file with a corresponding file path, please try again.",
                            f.getMessage());
                }
            }
        }
    }
    @Nested
    @DisplayName("Tests for the FileManager 'deleteAFile' method")
    public class TestDeleteAFileMethod {
        @Nested
        @DisplayName("Positive tests for the 'deleteAFile' method")
        public class PositiveTestsForDeleteAFileMethod {
            @Test
            @DisplayName("DeleteAFile method removes an army when the target file exists")
            public void deleteAFileWorksCorrectly() {
                //Creating file
                FileManager.writeArmyToFileWFileName("orcish army", orcArmy);
                //Deleting file
                FileManager.deleteAFile("orcish army");
                assertFalse(Files.exists(
                        Paths.get(
                                "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orcish army.csv")));
            }
            @Test
            @DisplayName("DeleteAFile does not find a file to remove")
            public void deleteAFileDoesNotReachAnyTargetFile() {
                assertFalse(Files.exists(
                        Paths.get(
                                "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/hummy.csv")));
                FileManager.deleteAFile("hummy");
                assertFalse(Files.exists(
                        Paths.get(
                                "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/hummy.csv")));
            }
        }
        @Nested
        @DisplayName("Negative tests for the 'deleteAFile' method")
        public class NegativeTestsForDeleteAFileMethod {
            //Only one negative test is included because 'deleteAFile' method throws by using the same help method
            //isPathValid as 'writeArmyToFileWFileName'
            @Test
            @DisplayName("writeArmyToFileWFileName throws 'InvalidPathException' when name contains Q-mark")
            public void writeArmyToFileWFileNameThrowsWhenQmarkIsInFileName(){
                try{
                    FileManager.deleteAFile("orcarmy2?");
                }catch (InvalidPathException i){
                    assertEquals("The given file path contained forbidden characters. " +
                                    "The application could not utilize it, please try again.: " +
                                    "src/main/resources/edu.ntnu.idatt2001.magnulal/csv/orcarmy2?.csv",
                            i.getMessage());
                }
            }
        }
        //TODO. negative test corrupted armies, implement corrupted data
        //argument evt in reprort
    }
}
