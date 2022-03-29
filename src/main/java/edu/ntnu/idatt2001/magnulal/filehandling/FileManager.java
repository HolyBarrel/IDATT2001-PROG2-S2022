package edu.ntnu.idatt2001.magnulal.filehandling;

import edu.ntnu.idatt2001.magnulal.simulatorclasses.Army;
import edu.ntnu.idatt2001.magnulal.unitclasses.Unit;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    private FileManager(){ //TODO: update

    }

    private static boolean checkIfFileExists(String filePath){ //TODO: MAYBE USE in write too?
        return new File(filePath).isFile();
    }
    public static void writeArmyToFile(String filePath, Army army){
        File file = new File(filePath);
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            printWriter.println(army.getName());
            army.getAllUnits()
                    .forEach(u -> printWriter.
                            println(u.getClass().getSimpleName() + "," + u.getName() + "," + u.getHealth()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO: improve ex handling
    public static StringBuilder readArmyFromFile(String filePath) throws IllegalArgumentException{
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
