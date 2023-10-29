package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.gui;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.executable.CreateLauncher;
import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.gui.GUI;

public class CopyElements {
    public static void copyNeededFiles(Path macosPath, Path resourcesPath, String jarPath, String icnsPath, String libPath) {
        try {
            Files.copy(Paths.get(jarPath), Paths.get(macosPath.toString(), "application.jar"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Paths.get(icnsPath), Paths.get(resourcesPath.toString(), "application.icns"), StandardCopyOption.REPLACE_EXISTING);
            if (GUI.isLibFolderChosen.get()) {
                Path targetLibPath = Paths.get(macosPath.toString(), "lib");
                copyFolder(Paths.get(libPath), targetLibPath);
            }
            System.out.println("files have been copied successfully"); // log
        } catch (IOException ignored) {
        }

        // Launcher wird in MacOS gebaut
        CreateLauncher.buildLauncher(macosPath);
    }

    public static void copyFolder(Path source, Path target) {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        try {
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
        } catch (IOException ignored) {
        }
    }
}

