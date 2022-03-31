package edu.ntnu.idatt2001.magnulal.filehandling;

import edu.ntnu.idatt2001.magnulal.simulatorclasses.Army;
import java.io.*;
import java.nio.file.*;

/**
 * FileManager class to deal with all file handling in the project 'Wargames'
 * Has various methods to write or read an army to/from a .csv (comma separated values)
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
     * root
     */
    private static String constructFilePath(String fileName){
        return String.format("src/main/resources/edu.ntnu.idatt2001.magnulal/%s.csv", fileName);
    }

    /**
     * Help method that checks if a file of the given fileName String parameter
     * already has been created in the resources root
     * @param fileName, is a string to a given file name
     * @return true if the file already exists in the resources root, and false if it
     * does not exist
     */
    private static boolean checkIfFileExists(String fileName){
        return new File(constructFilePath(fileName)).isFile();
    }

    /**
     * Help method to check if the path that is created from the inputted
     * String fileName parameter is invalid. This can happen if there are forbidden
     * characters in the file path. For example if characters like '?' are entered into the
     * fileName string as : 'file?Name'
     * @param fileName, is a string to a given file name
     * @return true if the path is invalid according to 'InvalidPathException' and false if the
     * path is valid
     */
    private static boolean isPathInvalid(String fileName){
        try{
            Paths.get(constructFilePath(fileName));
        }catch(InvalidPathException e){
            return true;
        }
        return false;
    }

    /**
     * Method to write a given army to a file. The method will create a file with the fileName in
     * the 'resources' root of this project if no file with the String parameter fileName already
     * exists
     * @param fileName, is a String given as a file name
     * @param army, is an army
     * @throws InvalidPathException if the constructed file path is invalid. Is caused by special characters
     * like '?' in the fileName //TODO: test
     */
    public static void writeArmyToFileWPath(String fileName, Army army) throws InvalidPathException{
        String filePath = constructFilePath(fileName);
        if(isPathInvalid(filePath)) throw new InvalidPathException(filePath, "The given file path " +
                "contained forbidden characters. The application could not utilize it, please try again.");
        File file = new File(filePath);
        writeArmyToFile(file, army);
    }

    /**
     * Method to write an army to a given file. Utilizes the static help method 'writeArmyToFile' to
     * write the specified parameter Army
     * @param file, is a File object that is already created
     * @param army, is an army
     */
    public static void writeArmyToFileWFile(File file, Army army) {
        writeArmyToFile(file, army);
    }

    /**
     * Help method to write the given army parameter to the file parameter using a
     * PrintWriter. Uses a .forEach iterable to print each Unit in the army list
     * to the specified file
     * @param file is created file
     * @param army is an army
     */
    private static void writeArmyToFile(File file, Army army) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)))) { //TODO: update to fileWriter
            printWriter.println(army.getName());
            army.getAllUnits()
                    .forEach(u -> printWriter.
                            println(u.getClass().getSimpleName() + "," + u.getName() + "," + u.getHealth()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read an army from an already existing file. The method uses a StringBuilder to
     * append each line read from the file to the StringBuilder. It also checks the validity of the
     * path constructed from the String fileName parameter and makes sure that the target file for reading
     * actually exists
     * @param fileName is a String
     * @return a StringBuilder containing information about the army
     * @throws IllegalArgumentException if the constructed file path is invalid or if no file with the
     * path does exist
     * The method catches IOException and prints the stack trace of it, if this occurs during reading
     */
    public static StringBuilder readArmyFromFile(String fileName) throws IllegalArgumentException {
        String filePath = constructFilePath(fileName);
        if(isPathInvalid(filePath)) throw new InvalidPathException(filePath, "The given file path had forbidden " +
                "characters, and the application could not utilize it. Please try again.");
        if(!checkIfFileExists(filePath)) throw new IllegalArgumentException("Could not find a file with a " +
                "corresponding filePath, please try again.");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(filePath))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
