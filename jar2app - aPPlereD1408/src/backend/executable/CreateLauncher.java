package backend.executable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CreateLauncher {
    public static void buildLauncher(Path macosPath) {
        try {
            // Constants
            String launcherScript = """
                    #!/bin/sh
                    # Author: Gereon Felix Krümmling

                    # Constants
                    JAVA_MAJOR=1
                    JAVA_MINOR=7
                    APP_JAR="sample.jar"
                    APP_NAME="Sample"
                    VM_ARGS=""

                    # Set the working directory
                    DIR=$(cd "$(dirname "$0")"; pwd)

                    # Error message for NO JAVA dialog
                    ERROR_TITLE="Cannot launch $APP_NAME"
                    ERROR_MSG="$APP_NAME requires Java version $JAVA_MAJOR.$JAVA_MINOR or later to run."
                    DOWNLOAD_URL="http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html"

                    # Is Java installed?
                    if type -p java; then
                        _java="java"
                    elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
                        _java="$JAVA_HOME/bin/java"
                    else
                        osascript \\
                        -e "set question to display dialog \\"$ERROR_MSG\\" with title \\"$ERROR_TITLE\\" buttons {\\"Cancel\\", \\"Download\\"} default button 2" \\
                        -e "if button returned of question is equal to \\"Download\\" then open location \\"$DOWNLOAD_URL\\""
                        echo "$ERROR_TITLE"
                        echo "$ERROR_MSG"
                        exit 1
                    fi

                    # Java version check
                    if [[ "$_java" ]]; then
                        version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
                        if [[ "$version" < "$JAVA_MAJOR.$JAVA_MINOR" ]]; then
                            osascript \\
                            -e "set question to display dialog \\"$ERROR_MSG\\" with title \\"$ERROR_TITLE\\" buttons {\\"Cancel\\", \\"Download\\"} default button 2" \\
                            -e "if button returned of question is equal to \\"Download\\" then open location \\"$DOWNLOAD_URL\\""
                            echo "$ERROR_TITLE"
                            echo "$ERROR_MSG"
                            exit 1
                        fi
                    fi

                    # Run the application
                    exec $_java $VM_ARGS -Dapple.laf.useScreenMenuBar=true -Dcom.apple.macos.use-file-dialog-packages=true -Xdock:name="$APP_NAME" -Xdock:icon="$DIR/../Resources/application.icns" -cp ".;$DIR;" -jar "$DIR/$APP_JAR\"""";

            // Specify the file path where the launcher script will be saved
            String launcherFilePath = macosPath.toString() + "/launcher"; // Ersetzen Sie durch den gewünschten Dateipfad

            // Create the launcher file
            File launcherFile = new File(launcherFilePath);

            // Write the script to the launcher file
            FileWriter fileWriter = new FileWriter(launcherFile);
            fileWriter.write(launcherScript);
            fileWriter.close();

            System.out.println("Launcher script has been created at: " + launcherFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}