package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.executable;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.gui.GUI;

public class ModifyLauncherFile {
    public static void modifyFile() {
        Path launcherFilePath = Path.of(GUI.appFolder + "/contents/MacOS/launcher");
        String launcherFilePathString = launcherFilePath.toString();

        String jarFilePathString = "application.jar";

        String folderName = GUI.inputAppName.getText();

        modifyLauncherFile(launcherFilePathString, jarFilePathString, folderName);
    }

    private static void modifyLauncherFile(String filePath, String appJar, String appName) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("APP_JAR=\"sample.jar\"")) {
                    line = line.replace("APP_JAR=\"sample.jar\"", "APP_JAR=\"" + appJar + "\"");
                }
                if (line.contains("APP_NAME=\"Sample\"")) {
                    line = line.replace("APP_NAME=\"Sample\"", "APP_NAME=\"" + appName + "\"");
                }
                fileContent.append(line).append("\n");
            }
            scanner.close();

            FileWriter writer = new FileWriter(file);
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("Launcher file modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error modifying launcher file: " + e.getMessage());
        }
    }
}