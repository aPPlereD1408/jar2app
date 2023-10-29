package backend;

import backend.executable.CreateLauncher;
import gui.GUI;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static gui.GUI.frame;

public class CopyElements {
    public static void copyNeededFiles(Path macosPath, Path resourcesPath, String jarPath, String icnsPath, String libPath) {
        try {
            Files.copy(Paths.get(jarPath), Paths.get(macosPath.toString(), "application.jar"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Paths.get(icnsPath), Paths.get(resourcesPath.toString(), "application.icns"), StandardCopyOption.REPLACE_EXISTING);
            if (GUI.isLibFolderChosen.get()) {
                Path targetLibPath = Paths.get(macosPath.toString(), "lib");
                copyFolder(Paths.get(libPath), targetLibPath);
            }
            System.out.println("files has been copied successfully"); // log
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Fehler beim Kopieren von Dateien.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        // Launcher wird in MacOS gebaut
        CreateLauncher.buildLauncher(macosPath);
    }

    public static void copyFolder(Path source, Path target) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        Files.walkFileTree(source, options, Integer.MAX_VALUE, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = target.resolve(source.relativize(dir));
                Files.createDirectories(targetDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
