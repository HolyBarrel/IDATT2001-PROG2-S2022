package edu.ntnu.idatt2001.magnulal.filehandling;

import edu.ntnu.idatt2001.magnulal.simulatorclasses.Army;
import edu.ntnu.idatt2001.magnulal.unitclasses.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * FileManager class to deal with all file handling in the project 'Wargames'
 * Has various methods to write or read an army to or from a .csv (comma separated values)
 * file
 * The constructor is private to ensure that other classes cannot instantiate objects of
 * the FileManager
 * This is because this class is only used for its static methods
 */
public class FileManager {

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

    /**
     * Help method that checks if a file of the given fileName String parameter
     * already has been created in the resources root
     * @param fileName, is a string to a given file name
     * @return true if the file already exists in the resources root, and false if it
     * does not exist
     */
    private static boolean checkIfFileExists(String fileName) throws IllegalArgumentException{
        return new File(constructFilePath(fileName)).exists();
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
        String filePath = constructFilePath(fileName);
        if(!isPathValid(fileName)) throw new InvalidPathException(filePath, "The given file path " +
                "contained forbidden characters. The application could not utilize it, please try again.");
        File file = new File(filePath);
        writeArmyToFileWFile(file, army);
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
     * interpret each line in the target .csv document. It also checks the validity of the
     * path constructed from the String fileName parameter and makes sure that the target file for reading
     * actually exists
     * @param fileName is a string for the targeted file name in the directory at the root:
                      'src/main/resources/edu.ntnu.idatt2001.magnulal/csvdocuments' of this project
     * @return a StringBuilder containing information about the army
     * @throws IllegalArgumentException (invalidPathException) if the constructed file path is invalid or if no
     * file with the path does exist
     * @throws NullPointerException if the file at the target file path does not exist
     * The method catches IOException and prints the stack trace of it, if this occurs during reading
     */
    public static Army readArmyFromFile(String fileName) throws InvalidPathException, NullPointerException {
        if(!isPathValid(fileName)) throw new InvalidPathException(constructFilePath(fileName), "The given file path " +
                "had forbidden characters, and the application could not utilize it. Please try again.");
        if(!checkIfFileExists(fileName)) throw new NullPointerException(constructFilePath(fileName) + " Was the " +
                "path. Could not find a file with a corresponding file path, please try again.");
        return readArmyFromExistingFile(new File(constructFilePath(fileName)));
    }

    /**
     * TODO: comment
     * TODO: test
     * @param file
     * @return
     */
    public static Army readArmyFromExistingFile(File file){
        Army readArmy = null;
        try (Scanner scanner = new Scanner(file)){
            String line = scanner.nextLine();
            readArmy = new Army(line.trim());
            while((scanner.hasNext())) {
                String[] lineValues = scanner.nextLine().split(",");
                switch (Objects.requireNonNull(UnitTypes.getValueMatching(lineValues[0].trim()))) {
                    case INFANTRY -> {
                        readArmy.add(new InfantryUnit(lineValues[1].trim(), Integer.parseInt(lineValues[2])));
                    }
                    case RANGED -> {
                        readArmy.add(new RangedUnit(lineValues[1].trim(), Integer.parseInt(lineValues[2])));
                    }
                    case CAVALRY -> {
                        readArmy.add(new CavalryUnit(lineValues[1].trim(), Integer.parseInt(lineValues[2])));
                    }
                    case COMMANDER -> {
                        readArmy.add(new CommanderUnit(lineValues[1].trim(), Integer.parseInt(lineValues[2])));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readArmy;
    }
    /**
     * Method to delete a given file at the file path constructed from the
     * specified file name
     * @param fileName is a String of a file name that specifies which file in the
     *                 resource's directory called 'csvdocuments'. This way the delete-method
     *                 can only delete from that directory
     * @throws InvalidPathException if the constructed path contains forbidden characters
     */
    public static void deleteAFile(String fileName) throws InvalidPathException{
        if(!isPathValid(fileName)) throw new InvalidPathException(constructFilePath(fileName),
                "The given file path contained forbidden " +
                        "characters. The application could not utilize it, please try again.");
        String filePath = constructFilePath(fileName);
        try{
            Files.deleteIfExists(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** TODO: maybe implement charset check
    public static boolean confirmCharset(File file){
        try(FileReader fileReader = new FileReader(file)){
            int c;
            ArrayList<Byte> b = new ArrayList<>();
            while((c = fileReader.read()) != -1){
                b.add((byte) c);
            }
            return b.stream().allMatch(bt -> ))
        } catch (IOException e) {
            return false;
        }
    }*/
}
