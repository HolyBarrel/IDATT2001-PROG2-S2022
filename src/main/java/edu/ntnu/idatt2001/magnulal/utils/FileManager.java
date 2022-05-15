package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.units.*;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * FileManager class to deal with all file handling in the project 'Wargames'
 * Has various methods to write or read an army to or from a .csv (comma separated values)
 * file
 * The constructor is private to ensure that other classes cannot instantiate objects of
 * the FileManager
 * This is because this class is only used for its static methods
 * @author magnulal
 * @version 0.3
 * @since 0.2
 */
public class FileManager { //TODO: check charset also

    /**
     * Private empty constructor to specify to the compiler that objects of
     * this class are not possible to instantiate
     */
    private FileManager(){}

    /**
     * Creates a string that is a correctly formatted path to this project's 'resources' root:
     * 'src/main/resources/edu.ntnu.idatt2001.magnulal/csv'.
     * @param fileName, is the file name specified
     * @return formatted string with 'src/main/resources' added to the start of the inputted
     * file name, and '.csv' to the end of it if the parameter does not end with '.csv'. This creates a viable
     * filepath to the resources root's directory called 'csv'. By using this method throughout the
     * FileManager class this directory is the only manipulable directory for the FileManager
     */
    public static String constructFilePath(String fileName){ //TODO: change back to private
        if(fileName.endsWith(".csv")){
            return String.format("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/%s", fileName);
        }else{
            return String.format("src/main/resources/edu.ntnu.idatt2001.magnulal/csv/%s.csv", fileName);
        }
    }
    //TODO: ADD METHOD TO EXTRACT FILE NAME

    /**
     * TODO: comment
     * @return or default
     */
    public static String constructHomePath(){
        return System.getProperty("user.home").replace("\\", "/");
    }


    /*
       Exception handling private methods
     */

    /** TODO: test and update
     * Checks that a file of the given fileName String parameter already exists.
     * This method utilizes the {@link File#exists()} method to check file existence.
     * If this file does not exist, a FileNotFoundException is thrown.
     * @param fileName, is a string to a given file name
     * @throws FileNotFoundException if the file at the constructed file path does not exist
     */
    private static void checkIfFileExists(String fileName) throws FileNotFoundException{
        if(!new File(constructFilePath(fileName)).exists()) throw new FileNotFoundException(constructFilePath(fileName)
                + " Was the path. Could not find a file with a corresponding file path, please try again.");
    }

    /**
     * Checks that a constructed file path is valid. This method uses the {@link #isPathValid(String)} method
     * to exercise the check. If {@link #isPathValid(String)} returns 'false', an InvalidPathException is thrown.
     * @param fileName is a string referring to the file name
     * @throws InvalidPathException if the help method {@link #isPathValid(String)} returns false
     */
    private static void checkValidityOfPath(String fileName) throws InvalidPathException {
        if(!isPathValid(fileName)) throw new InvalidPathException(constructFilePath(fileName), "The given file path " +
                "contained forbidden characters. The application could not utilize it, please try again.");
    }

    /**
     * Checks if the path that is created from the inputted file name can serve as a valid path
     * This method returns a boolean value representing whether the path is valid.
     * The path is invalid if there are forbidden characters in the file path. For example if characters such as '?'
     * are entered into the fileName string as : 'file?Name'. The method also checks if there are '\', '/', or '.' when
     * the name does not end with '.csv'.
     * @param fileName, is a string to a given file name
     * @return false if the path is invalid according to 'InvalidPathException' or the if-check, otherwise true
     */
    private static boolean isPathValid(String fileName){
        if(fileName.contains("\\") || fileName.contains("/") ||
                fileName.contains(".") && !(fileName.endsWith(".csv"))) return false;
        try{
            Paths.get(constructFilePath(fileName));
        }catch(InvalidPathException e){
            return false;
        }
        return true;
    }

    /**
     * Writes a given army to a file. The method will create a file with the fileName in
     * the 'resources' root of this project if no file with the String parameter fileName already
     * exists
     * @param fileName, is a String given as a file name
     * @param army, is an army
     * @throws InvalidPathException if the constructed file path is invalid. Is caused by special characters
     * such as '?' in the fileName
     */
    public static void writeArmyToFileWFileName(String fileName, Army army) throws InvalidPathException{
        checkValidityOfPath(fileName);
        writeArmyToFileWFile(new File(constructFilePath(fileName)), army);
    }

    /**
     * Writes an army to a given file. Utilizes the static help method 'writeArmyToFile' to
     * write the specified parameter Army
     * @param file, is a File object that is already created
     * @param army, is an army
     */
    public static void writeArmyToFileWFile(File file, Army army) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            printWriter.println(army.getName());
            army.getAllUnits()
                    .stream()
                    .map(u -> u.getClass().getSimpleName() + "," + u.getName() + "," + u.getHealth())
                    .forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read an army from an already existing file. The method uses a Scanner to
     * interpret each line in the target .csv document by using the 'readArmyFromExistingFile' method.
     * It also checks the validity of the path constructed from the String fileName parameter and makes
     * sure that the target file for reading actually exists
     * @param fileName is a string for the targeted file name in the directory at the root:
                      'src/main/resources/edu.ntnu.idatt2001.magnulal/csv' of this project
     * @return an army from the information in the target file
     * @throws InvalidPathException if the constructed file path is invalid or if no
     * file with the path does exist
     * @throws NullPointerException if the file at the target file path does not exist
     * The method catches IOException and prints the stack trace of it, if this occurs during reading
     */ //TODO: delete
    public static Army readArmyFromFile(String fileName) throws InvalidPathException, NullPointerException,
            FileNotFoundException {
        checkValidityOfPath(fileName);
        checkIfFileExists(fileName);
        return readArmyFromExistingFile(new File(constructFilePath(fileName)));
    }

    /**
     * TODO: COMMENT, revise CLASS, add ex handl
     * @param filePath
     * @return
     * @throws InvalidPathException
     * @throws NullPointerException
     * @throws FileNotFoundException
     */
    public static Army readArmyFromFullFilePath(String filePath) throws InvalidPathException, NullPointerException,
            FileNotFoundException { //TODO: move, redo FileHandler
        return readArmyFromExistingFile(new File(filePath));
    }

    /**
     * Read an army from an already existing file. The method uses a Scanner to
     * interpret each line in the parameter file .csv document.
     * @param file is an existing file
     * @return an army created from the information in the file
     */
    public static Army readArmyFromExistingFile(File file) throws NullPointerException{
        //TODO. test nullPointer
        Army readArmy = null;
        try (Scanner scanner = new Scanner(file)){
            String line = scanner.nextLine();
            readArmy = new Army(line.trim());
            while((scanner.hasNext())) {
                String[] lineValues = scanner.nextLine().split(",");
                readArmy.add(readUnit(lineValues));
            }
        } catch (IOException e) {
            e.printStackTrace(); //TODO: handle better / throw???
        }
        return readArmy;
    }

    /**
     * Reads a specific line of a string array, and constructs a unit type corresponding with the first string in
     * the array. Utilizes the other indexes of the string array to create the unit. TODO update
     * This method uses {@link UnitTypes} enum to form the logic behind the enhanced switch statement.
     * @param readLineValues is a string array constructed from a line in a csv document
     * @return a unit created from the string values of the readLineValues parameter array
     * @throws NullPointerException if the line could not be matched with any unit type
     */
    private static Unit readUnit(String[] readLineValues) throws NullPointerException{
        try{
            return UnitFactory.createUnit(Objects.requireNonNull(UnitTypes.getValueMatching(
                            readLineValues[0].trim())),
                    readLineValues[1].trim(),
                    Integer.parseInt(readLineValues[2]));
        }catch (NullPointerException n){
            throw new NullPointerException("The requested unit type could not be found.");
        }

        //TODO: LAG EN SOM reader hele andre constructøren fra unit
    }
    /**
     * Deletes a given file at the file path constructed from the specified file name if a file of that name exists
     * @param fileName is a String of a file name that specifies which file in the
     *                 resource's directory called 'csv'. This way the delete-method
     *                 can only delete from that directory
     * @throws InvalidPathException if the constructed path contains forbidden characters
     */
    public static void deleteAFile(String fileName) throws InvalidPathException{ //TODO: more exceptions?
        checkValidityOfPath(fileName);
        try{
            Files.deleteIfExists(Path.of(constructFilePath(fileName)));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: handle better
            //TODO: implement finally?
        }
    }
}
