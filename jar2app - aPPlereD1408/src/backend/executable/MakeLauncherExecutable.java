package backend.executable;

import java.io.File;
import java.nio.file.Path;

import static gui.GUI.appFolder;

public class MakeLauncherExecutable {
    public static void execute() {
        Path launcherFilePath = Path.of(appFolder + "/contents/MacOS/launcher");
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

