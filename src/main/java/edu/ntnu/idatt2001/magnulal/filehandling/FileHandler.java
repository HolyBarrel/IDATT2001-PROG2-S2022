package edu.ntnu.idatt2001.magnulal.filehandling;

import edu.ntnu.idatt2001.magnulal.simulatorclasses.Army;
import edu.ntnu.idatt2001.magnulal.unitclasses.Unit;

import java.io.*;

public class FileHandler {
    public static void writeAnArmyToFile(String filePath, Army army){
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
}
