package backend.plist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static gui.GUI.*;

public class CreateInfoPlistXML {
    public static void createFile() {
        String plistContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\">\n" +
                "<dict>\n" +
                "    <key>CFBundleDisplayName</key>\n" +
                "    <string>" + inputAppName.getText() + "</string><!-- from input -->\n" +
                "\n" +
                "    <!-- Automatically generated -->\n" +
                "    <key>CFBundleIconFile</key>\n" +
                "    <string></string>\n" +
                "    <key>CFBundleName</key>\n" +
                "    <string>" + inputAppName.getText() + "</string>\n" +
                "\n" +
                "    <key>CFBundleDevelopmentRegion</key>\n" +
                "    <string>en</string>\n" +
                "\n" +
                "    <!-- from inputs -->\n" +
                "    <key>CFBundleVersion</key>\n" +
                "    <string>" + bundleIdentifier.getText() + "</string>\n" +
                "    <key>CFBundleShortVersionString</key>\n" +
                "    <string>" + bundleVersionShortString.getText() + "</string>\n" +
                "    <key>CFBundleDevelopmentRegion</key>\n" +
                "    <string>" + nativeDevelopmentRegion.getText() + "</string>\n" +
                "    <key>CFBundleVersion</key>\n" +
                "    <string>" + bundleVersion.getText() + "</string>\n" +
                "    <key>LSApplicationCategoryType</key>\n" +
                "    <string>" + applicationCategoryType.getSelectedItem() + "</string>\n" +
                "    <key>NSHumanReadableCopyright</key>\n" +
                "    <string>" + humanReadableCopyright.getText() + "</string>\n" +
                "\n" +
                "    <!-- Do not touch -->\n" +
                "    <key>CFBundleExecutable</key>\n" +
                "    <string>launcher</string>\n" +
                "    <key>CFBundleInfoDictionaryVersion</key>\n" +
                "    <string>6.0</string>\n" +
                "    <key>CFBundlePackageType</key>\n" +
                "    <string>APPL</string>\n" +
                "    <key>CFBundleSignature</key>\n" +
                "    <string>xmmd</string>\n" +
                "    <key>NSAppleScriptEnabled</key>\n" +
                "    <string>NO</string>\n" +
                "</dict>\n" +
                "</plist>";

        // Speichern Sie die Info.plist-Datei im "contents"-Verzeichnis
        File contentsDirectory = new File(appFolder + "/contents");
        contentsDirectory.mkdir();
        File infoPlistFile = new File(contentsDirectory, "Info.plist");

        try {
            FileWriter fileWriter = new FileWriter(infoPlistFile);
            fileWriter.write(plistContent);
            fileWriter.close();
            System.out.println("Info.plist file created successfully in the 'contents' directory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}