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
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.2
 */
public class FileManager { //TODO: check charset also

    /**
     * Private empty constructor to specify to the compiler that objects of
     * this class are not possible to instantiate
     */
    private FileManager(){}

    /**
     * Help method to create a string that is a correctly formatted path to this
     * project's 'resources' root
     * @param fileName, is the file name specified
     * @return formatted string with 'src/main/resources' added to the start of the inputted
     * file name, and '.csv' to the end of it. This creates a viable filepath to the resources
     * root's directory called 'csvdocuments'. By using this method throughout the FileManager class
     * this directory is the only accessible directory for the FileManager in this project
     */
    private static String constructFilePath(String fileName){
        if(fileName.endsWith(".csv")){
            return String.format("src/main/resources/edu.ntnu.idatt2001.magnulal/csvdocuments/%s", fileName);
        }else{
            return String.format("src/main/resources/edu.ntnu.idatt2001.magnulal/csvdocuments/%s.csv", fileName);
        }
    }

    /** TODO: test and update
     * Help method that checks if a file of the given fileName String parameter
     * already has been created in the resources root
     * @param fileName, is a string to a given file name
     * @throws FileNotFoundException if the file at the constructed file path does not exist
     */
    private static void checkIfFileExists(String fileName) throws FileNotFoundException{
        if(!new File(constructFilePath(fileName)).exists()) throw new FileNotFoundException(constructFilePath(fileName)
                + " Was the path. Could not find a file with a corresponding file path, please try again.");
    }

    //TODO: comment
    private static void checkValidityOfPath(String fileName) throws InvalidPathException {
        if(!isPathValid(fileName)) throw new InvalidPathException(constructFilePath(fileName), "The given file path " +
                "contained forbidden characters. The application could not utilize it, please try again.");
    }

    /**
     * Help method to check if the path that is created from the inputted
     * String fileName parameter is valid. The path is invalid if there are forbidden
     * characters in the file path. For example if characters such as '?' are entered into the
     * fileName string as : 'file?Name'
     * @param fileName, is a string to a given file name
     * @return false if the path is invalid according to 'InvalidPathException' and the if-check,
     * and true if the path is valid
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
     * Method to write a given army to a file. The method will create a file with the fileName in
     * the 'resources' root of this project if no file with the String parameter fileName already
     * exists
     * @param fileName, is a String given as a file name
     * @param army, is an army
     * @throws InvalidPathException if the constructed file path is invalid. Is caused by special characters
     * like '?' in the fileName
     */
    public static void writeArmyToFileWFileName(String fileName, Army army) throws InvalidPathException{
        checkValidityOfPath(fileName);
        writeArmyToFileWFile(new File(constructFilePath(fileName)), army);
    }

    /**
     * Method to write an army to a given file. Utilizes the static help method 'writeArmyToFile' to
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
     * Method to read an army from an already existing file. The method uses a Scanner to
     * interpret each line in the target .csv document by using the 'readArmyFromExistingFile' method.
     * It also checks the validity of the path constructed from the String fileName parameter and makes
     * sure that the target file for reading actually exists
     * @param fileName is a string for the targeted file name in the directory at the root:
                      'src/main/resources/edu.ntnu.idatt2001.magnulal/csvdocuments' of this project
     * @return an army from the information in the target file
     * @throws InvalidPathException if the constructed file path is invalid or if no
     * file with the path does exist
     * @throws NullPointerException if the file at the target file path does not exist
     * The method catches IOException and prints the stack trace of it, if this occurs during reading
     */
    public static Army readArmyFromFile(String fileName) throws InvalidPathException, NullPointerException,
            FileNotFoundException {
        checkValidityOfPath(fileName);
        checkIfFileExists(fileName);
        return readArmyFromExistingFile(new File(constructFilePath(fileName)));
    }

    /**
     * Method to read an army from an already existing file.  The method uses a Scanner to
     * interpret each line in the parameter file .csv document.
     * @param file is an existing
     * @return an army created from the information in the file
     */
    public static Army readArmyFromExistingFile(File file) throws NullPointerException{//TODO: take reading into separate method
        //TODO. handle exceptions for null return of readline
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
            e.printStackTrace(); //TODO: handle better
        }
        return readArmy;
    }

    private static Unit readUnit(String[] readLineValues) throws NullPointerException{
        switch (Objects.requireNonNull(UnitTypes.getValueMatching(readLineValues[0].trim()))) {
            case INFANTRY -> {
                return new InfantryUnit(readLineValues[1].trim(), Integer.parseInt(readLineValues[2]));
            }
            case RANGED -> {
                return new RangedUnit(readLineValues[1].trim(), Integer.parseInt(readLineValues[2]));
            }
            case CAVALRY -> {
                return new CavalryUnit(readLineValues[1].trim(), Integer.parseInt(readLineValues[2]));
            }
            case COMMANDER -> {
                return new CommanderUnit(readLineValues[1].trim(), Integer.parseInt(readLineValues[2]));
            }
            default -> {
                throw new NullPointerException("A line in the CSV document did not " +
                        "match the required comma separation to create a unit from the line.");
            }
        }
    }
    /**
     * Method to delete a given file at the file path constructed from the
     * specified file name
     * @param fileName is a String of a file name that specifies which file in the
     *                 resource's directory called 'csvdocuments'. This way the delete-method
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
