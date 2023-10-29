package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.executable;

import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.gui.GUI;

import java.io.File;
import java.nio.file.Path;

public class MakeLauncherExecutable {
    public static void execute() {
        Path launcherFilePath = Path.of(GUI.appFolder + "/contents/MacOS/launcher");
        String launcherFilePathString = launcherFilePath.toString();

        makeExecutable(launcherFilePathString);
    }

    private static void makeExecutable(String filePath) {
        File launcherFile = new File(filePath);

        if (launcherFile.exists()) {
            boolean success = launcherFile.setExecutable(true);

            if (success) {
                System.out.println("Launcher file is now executable.");
            } else {
                System.err.println("Failed to make the launcher file executable.");
            }
        } else {
            System.err.println("Launcher file does not exist.");
        }
    }
}
