package menubar.popup;

import javax.swing.*;
import java.awt.*;

import static gui.GUI.bgColor;

public class Function {
    public Function() {
        // Setup
        JFrame frame = new JFrame();
        frame.setTitle(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);

        frame.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
        frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
        frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);

        JPanel panel = new JPanel(new BorderLayout());

        // Haupt-Text
        JLabel label = new JLabel();
        String htmlText = "<html><head><style>" +
                "@font-face {" +
                "  font-family: 'Inter';" +
                "  src: url('/Users/gereonkrummling/Documents/Sonstiges/jar2appProgramm/font/Inter-Regular.ttf');" +
                "}" +
                "body { font-family: 'Inter'; font-size: 14px; color: 'white'; background-color: rgb(41, 49, 56); }" +
                "a { color: #2b7fed; text-decoration: underline; }" +
                "</style></head>" +
                "<body style='margin: 30px 20px 0px 20px;'> " + // Erhöhen Sie die obere Marge hier
                "After you select a destination folder and also specify a name, a .app folder is created " +
                "in the destination folder in the background.<br>" +
                "In addition, the \"contents\", \"MacOS\" and \"Resources\" folders are created. " +
                "These are needed for the structure of the app.<br><br>" +
                "After that, you need to select the .jar and .icns files. Optionally also " +
                "the lib folder. The files and the folder are then copied to the right places " +
                "in the .app folder and renamed. The .jar and lib folders are copied " +
                "to the \"MacOS\" folder, while the .icns file is copied to the \"Resources\" folder. <br><br>" +
                "In order to be able to use the app, the Info.plist - Property List - must " +
                "be created. In this file all important and needed values are stored, " +
                "like app name and version number. The most important values are entered " +
                "automatically, the user-specific values must be entered in the window. <br><br>" +
                "During the completion a launcher file will be created in \"MacOS\", " +
                "which will be the executing file. <br><br>" +
                "Now the app is ready and will be opened automatically by the tool." +
                "</body></html>";

        label.setText(htmlText);
        label.setOpaque(true);

        panel.add(label, BorderLayout.NORTH);
        panel.setBackground(bgColor);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}