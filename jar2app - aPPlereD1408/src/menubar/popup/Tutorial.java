package menubar.popup;

import javax.swing.*;
import java.awt.*;

import static gui.GUI.bgColor;

public class Tutorial {
    public Tutorial() {
        // Setup
        JFrame frame = new JFrame();
        frame.setTitle(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(350, 350);

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
                "So you need your .jar file (Java Archive) and your " +
                ".icns file (Apple Icon File). Also you " +
                "need a destination folder for your application. " +
                "Then just follow the instructions on screen." +
                "<br><br>" +
                "It is so simple :) " +
                "</body></html>";

        label.setText(htmlText);
        label.setOpaque(true);

        panel.add(label, BorderLayout.NORTH);
        panel.setBackground(bgColor);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
