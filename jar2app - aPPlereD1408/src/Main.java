import gui.GUI;
import menubar.popup.AboutDialog;
import menubar.popup.Preferences;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.Desktop.Action.APP_PREFERENCES;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        // Apple Look and Feel
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // Menubar
        System.setProperty("apple.awt.application.name", "jar2app"); // App-Name
        UIManager.getSystemLookAndFeelClassName(); // L&F

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.APP_ABOUT)) {
            desktop.setAboutHandler(e -> {
                try {
                    new AboutDialog();
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        if (desktop.isSupported(APP_PREFERENCES)) {
            desktop.setPreferencesHandler(e -> Preferences.settings());
        }

        if (desktop.isSupported(Desktop.Action.APP_QUIT_HANDLER)) {
            desktop.setQuitHandler((e, response) -> {
                boolean canQuit = true;
                if (canQuit) {
                    response.performQuit();
                } else {
                    response.cancelQuit();
                }
            });
        }

        new GUI();
    }
}